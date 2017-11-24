package org.academiadecodigo.hackathon.apologies.server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by codecadet on 16/11/17.
 */
public class JdbcScore {

    private Connection dbConnection ;

    public JdbcScore(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    /**
     * Get tyhe points of the user
     * @param username to get the score
     * @return the points of the user
     */
    public int getScore(String username){

        String query = "SELECT * FROM scores WHERE name = ? ;";
        int score = 0;

        try {

            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1,username);
            ResultSet resultSet =  preparedStatement.executeQuery();
            if (resultSet.next()){
                score = resultSet.getInt("score");
            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;
    }

    /**
     * Update the score of the username
     * @param username the user to update the score
     * @param score score to update
     */
    public void updatePoints(String username,int score){

        String query = "UPDATE scores SET score = ? WHERE username = ? ;";

        PreparedStatement preparedStatement;
        try {

            preparedStatement = dbConnection.prepareStatement(query);

            preparedStatement.setInt(1,score);
            preparedStatement.setString(2,username);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Integer> getTopScores(){
        String query = "SELECT * FROM scores ORDER BY score DESC;";
        Map<String, Integer> map = new LinkedHashMap<>();

        PreparedStatement preparedStatement;

        try {
            preparedStatement = dbConnection.prepareStatement(query);
            ResultSet resultSet =  preparedStatement.executeQuery();
            while (resultSet.next()){
                map.put(resultSet.getString("name"), resultSet.getInt("score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
}
