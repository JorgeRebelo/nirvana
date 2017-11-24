package org.academiadecodigo.hackathon.apologies;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mail.vandrake.VLib;
import com.mail.vandrake.control.screen.VClearScreen;
import com.mail.vandrake.scene2d.VScreen;
import org.academiadecodigo.hackathon.apologies.game.screens.GameScreen;
import org.academiadecodigo.hackathon.apologies.game.screens.HighScoreScreen;
import org.academiadecodigo.hackathon.apologies.game.screens.LoginScreen;
import org.academiadecodigo.hackathon.apologies.servercomunication.ServerParser;
import org.academiadecodigo.hackathon.apologies.utils.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

public final class AllApologies extends Game {

    private static AllApologies instance;

    VLib vLib;
    private SoundManager soundManager;

    public static InputMultiplexer inputMultiplexer;

    private AllApologies() {

    }

    public static synchronized AllApologies getInstance() {

        if (instance == null) {

            instance = new AllApologies();
        }

        return instance;
    }

    @Override
    public void create() {

        inputMultiplexer = new InputMultiplexer();

        vLib = new VLib();

        vLib.addHandler(new FontHandler());

        vLib.create();

        vLib.start();

        soundManager = new SoundManager();

        Gdx.input.setInputProcessor(inputMultiplexer);
        //VScreen.setScreen(this, new LoginScreen());

        VScreen.setScreen(this, new GameScreen("miro"));
    }

    @Override
    public void render() {

        VClearScreen.byGDXColor(Color.BLACK);

        super.render();
    }

    @Override
    public void dispose() {

        super.dispose();
        vLib.dispose();
        soundManager.dispose();
    }

    @Override
    public void setScreen(Screen screen) {

        inputMultiplexer.clear();
        super.setScreen(screen);
    }
}