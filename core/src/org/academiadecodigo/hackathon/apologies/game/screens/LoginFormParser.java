package org.academiadecodigo.hackathon.apologies.game.screens;

/**
 * Created by codecadet on 23/11/17.
 */
public class LoginFormParser {

    public static boolean validForm(String text, String password) {

        if (formContainsComma(text, password)) {

            return false;
        }

        if (!formIsProperLength(text, password)) {

            return false;
        }

        return true;
    }

    private static boolean formIsProperLength(String text, String password) {

        return text.length() > 0 && password.length() > 0;
    }

    private static boolean formContainsComma(String text, String password) {

        if (text == null || password == null) {

            throw new IllegalArgumentException("Argument cannot null: " + text + ", " + password);
        }

        return text.contains(",") || password.contains(",");
    }
}