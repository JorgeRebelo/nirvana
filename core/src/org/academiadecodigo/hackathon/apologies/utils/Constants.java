package org.academiadecodigo.hackathon.apologies.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.mail.vandrake.VLib;
import com.mail.vandrake.control.GameDefs;
import com.mail.vandrake.scene2d.VWindow;
import com.mail.vandrake.scene2d.WindowFactory;

/**
 * Created by codecadet on 23/11/17.
 */
public class Constants {

    public static final int PORT = 1337;
    public static final String HOST = "192.168.1.13";
    public static final int MAX_PLAYERS = 50;
    public static final float GRAVITY = -25f;
    public static final float GAME_HEIGHT = 4 * ((GameDefs.PC_HEIGHT - 3) * Constants.CAMERA_SCALE);
    public static final float ANIMATION_FRAME_TIME = 0.25f;
    public static final String BUTTON_BACK = "Back";
    public static final String HIGHSCORE = "High Scores";
    public static final String HIGHSCORE_ID = "highscore.ttf";

    public static BitmapFont guiFont;

    public static final String BUTTON_REGISTER = "Register";
    public static final String DEFAULT_FONT = "ui_font.ttf";
    public static final String LOGIN_BKG_IMAGE = "logo.jpg";
    public static final String BUTTON_QUIT = "Quit";
    public static final String BUTTON_LOGIN = "Login";
    public static final String TEXT_FIELD_USERNAME = "Username";
    public static final String TEXT_FIELD_PASSWORD = "Password";
    public static final String INVALID_LOGIN_FORM = "Invalid Login Form";
    public static final String INVALID_REGISTER_FORM = "Invalid Register Form";
    public static final float CAMERA_SCALE = 0.032f;

    public static Window.WindowStyle defaultWindowStyle() {

        return WindowFactory.windowStyle(VLib.guiSkin, GameDefs.WINDOW, Color.WHITE, guiFont);
    }
}