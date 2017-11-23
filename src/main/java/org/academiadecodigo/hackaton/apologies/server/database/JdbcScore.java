package org.academiadecodigo.hackaton.apologies.server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
     * @param username
     * @return the points of the user
     */
    public int getPoints(String username){

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
     * @param username
     * @param score
     */
    public void updatePoints(String username,int score){

        String query = "UPDATE scores SET score = ? WHERE username = ? ;";

        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = dbConnection.prepareStatement(query);

            preparedStatement.setInt(1,score);
            preparedStatement.setString(2,username);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
