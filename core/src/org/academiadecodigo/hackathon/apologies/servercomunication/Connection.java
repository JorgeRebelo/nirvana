package org.academiadecodigo.hackathon.apologies.servercomunication;

import org.academiadecodigo.hackathon.apologies.utils.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Samuel Laço on 23/11/17.
 */
public class Connection {
    private static Connection ourInstance = null;
    private static Socket socket;
    private static PrintWriter writer;
    private static BufferedReader reader;

    public static Connection getInstance() {
        if (ourInstance == null){
            ourInstance = new Connection();
        }
        return ourInstance;
    }

    private Connection() {
        connect();
    }

    private static void connect() {
        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
            socket = new Socket(Constants.HOST, Constants.PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void encodeAndSend(EncodeDecode tag, String message) {
        if (ourInstance == null) {
            ourInstance = new Connection();
        }
        writer.println(tag.encode(message));
    }

    protected void shutdown() {
        if (ourInstance == null) {
            return;
        }

        writer.println((String) null);
        try {
            if (!socket.isClosed()) {
                socket.shutdownInput();
            }
            writer.println();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String readServer() {
        if (ourInstance == null) {
            ourInstance = new Connection();
        }
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
