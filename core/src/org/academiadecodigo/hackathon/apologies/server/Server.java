package org.academiadecodigo.hackathon.apologies.server;

import org.academiadecodigo.hackathon.apologies.server.database.ConnectionManager;
import org.academiadecodigo.hackathon.apologies.server.database.JdbcLogin;
import org.academiadecodigo.hackathon.apologies.server.database.JdbcScore;
import org.academiadecodigo.hackathon.apologies.utils.Constants;
import org.academiadecodigo.hackathon.apologies.utils.EncodeDecode;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Samuel Laço on 23/11/17.
 */
public class Server {
    private ServerSocket server;
    private ExecutorService executorService;
    private Map<String, String> hostsMap;
    private JdbcLogin jdbcLogin;
    private JdbcScore jdbcScore;
    private List<ServerWorker> serverWorkers;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public Server() {
        executorService = Executors.newFixedThreadPool(Constants.MAX_PLAYERS);
        hostsMap = new LinkedHashMap<String, String>();
        Connection connection = ConnectionManager.getConnection();
        jdbcLogin = new JdbcLogin(connection);
        jdbcScore = new JdbcScore(connection);
        serverWorkers = new LinkedList<ServerWorker>();
    }

    private void closeServer() {
        if (server.isClosed()) {
            return;
        }

        shutdownAllWorkers();
        executorService.shutdown();
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void shutdownAllWorkers() {
        for (ServerWorker worker : serverWorkers) {
            worker.shutdown();
        }
    }

    private void start() {
        // open socket
        // while to acceptConnections

        try {
            server = new ServerSocket(Constants.PORT);

            // type anything in server to cleanly exit
            new Thread(){
                @Override
                public void run() {
                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();
                    closeServer();
                }
            }.start();

            System.out.println("listening to new connections");
            while (true) {
                Socket client = server.accept();
                System.out.println("New Connection was accepted. Socket number: " + client.getPort());
                acceptConnection(client);
            }

        } catch (IOException e) {
            System.out.println("Closed server socket");
        } finally {
            closeServer();
        }
    }

    private void acceptConnection(Socket client) {
        ServerWorker newPlayer;
        try {
            newPlayer = new ServerWorker(client);
            newPlayer.init();
            serverWorkers.add(newPlayer);
            executorService.submit(newPlayer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class ServerWorker implements Runnable {
        private Socket clientSocket;
        private String name = "";

        private BufferedReader in;
        private PrintWriter out;

        ServerWorker(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        private void init() throws IOException {
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
        }

        private void sendMessage(String message) {
            out.println(message);
        }

        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {

                    EncodeDecode parsedEncoding = EncodeDecode.getEnum(EncodeDecode.getStartTag(message));

                    if (parsedEncoding == null) {
                        // ignore unencoded message
                        continue;
                    }

                    switch (parsedEncoding) {
                        case LOGIN:
                            doLogin(message);
                            break;
                        case GETSCORE:
                            sendScore();
                            break;
                        case SETSCORE:
                            setScore(message);
                        default:
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                out.println((String) null);
                System.err.println("Player disconnected.");
                disconnectPlayer();
            }
        }

        private void setScore(String message) {
            if(jdbcScore == null) {
                return;
            }
            //TODO
            //jdbcScore.updatePoints(name, );
        }

        private void sendScore(){
            if (jdbcScore == null){
                sendMessage(EncodeDecode.GETSCORE.encode("0"));
                return;
            }
            int score = jdbcScore.getPoints(name);
            sendMessage(EncodeDecode.GETSCORE.encode(Integer.toString(score)));
        }

        private void doLogin(String message) {
            String nickAndPass = EncodeDecode.LOGIN.decode(message);

            if (nickAndPass == null || nickAndPass.split(",").length != 2) {
                return;
            }

            String[] splitUserPass = nickAndPass.split(",");

            if (ConnectionManager.getConnection() == null) {
                System.out.println("Database is down");
                sendMessage(EncodeDecode.DBDOWN.encode(""));
                return;
            }

            if (jdbcLogin.authenticate(splitUserPass[0], splitUserPass[1])) {
                this.name = splitUserPass[0];
                sendMessage(EncodeDecode.NICKOK.encode("true"));
                return;
            }

            if (jdbcLogin.userExists(splitUserPass[0])) {
                sendMessage(EncodeDecode.PWDERROR.encode("true"));
                return;
            }

            if (jdbcLogin.addUser(splitUserPass[0], splitUserPass[1])) {
                sendMessage(EncodeDecode.NICKOK.encode("true"));
            }
        }
        private void disconnectPlayer() {
            if (clientSocket.isClosed()) {
                return;
            }
            shutdown();
        }

        private void shutdown() {
            if (clientSocket == null) {
                return;
            }
            try {
                clientSocket.shutdownInput();
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Client socket closed");
            }
        }
    }
}
