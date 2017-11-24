package org.academiadecodigo.hackathon.apologies;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mail.vandrake.VLib;
import com.mail.vandrake.control.screen.VClearScreen;
import com.mail.vandrake.scene2d.VScreen;
import org.academiadecodigo.hackathon.apologies.game.screens.GameScreen;


public final class AllApologies extends Game {

    private static AllApologies instance;

    VLib vLib;
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

        vLib.create();

        vLib.start();

        Constants.guiFont = new BitmapFont();

        Gdx.input.setInputProcessor(inputMultiplexer);

        VScreen.setScreen(this, new GameScreen());
    }

    @Override
    public void render() {

        VClearScreen.byGDXColor(Color.DARK_GRAY);

        super.render();
    }

    @Override
    public void dispose() {

        super.dispose();
        vLib.dispose();
    }
}