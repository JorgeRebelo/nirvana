package org.academiadecodigo.hackathon.apologies.servercomunication;

import org.academiadecodigo.hackathon.apologies.utils.Security;

import java.util.*;

/**
 * Created by Samuel Laço on 23/11/17.
 */
public class ServerParser {
    private static final Connection connection = Connection.getInstance();

    /**
     * Do the login of the player on the server
     * @param username of the player
     * @param pwd password of the player
     * @return success... or not!
     */
    public static boolean sendLogin(String username, String pwd) {
        if (connection == null){
            return false;
        }
        connection.encodeAndSend(EncodeDecode.LOGIN, username + "," + Security.getHash(pwd));
        String message = connection.readServer();
        if (EncodeDecode.NICKOK.decode(message) == null){
            return false;
        }

        return Boolean.parseBoolean(EncodeDecode.NICKOK.decode(message));
    }

    /**
     * read something from the server
     * @param tag to wait the answer from the server
     * @return the string with the answer
     */
    public static String readServer(EncodeDecode tag) {
        if (connection == null){
            return "";
        }
        String message = connection.readServer();
        if (EncodeDecode.getEnum(EncodeDecode.getStartTag(message)) != tag) {
            return "";
        }
        return tag.decode(message);
    }

    /**
     * ask for the current score of the player
     * @return the current score
     */
    public static int askScore() {
        if (connection == null){
            return 0;
        }
        connection.encodeAndSend(EncodeDecode.GETSCORE, "");
        String message = "";

        while (EncodeDecode.getStartTag(message = connection.readServer()) != EncodeDecode.GETSCORE.getStart()){

        }
        return Integer.parseInt(EncodeDecode.GETSCORE.decode(message));
    }

    /**
     * The top of the players currently registered
     * @return LinkedHashMap<String,Integer> <Name, Score>
     */
    public static Map<String, Integer> topPlayers(){
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        if (connection == null){
            return map;
        }
        connection.encodeAndSend(EncodeDecode.GETTOPSCORE, "");
        String message = "";

        while (EncodeDecode.getEnum(EncodeDecode.getStartTag(message = connection.readServer())) != EncodeDecode.GETTOPSCORE){

        }

        String[] splitted = message.split(",");

        for (String toSplit : splitted) {
            String[] userAndScore = toSplit.split("§");
            map.put(userAndScore[0], Integer.parseInt(userAndScore[1]));
        }

        return map;
    }
}
