package org.academiadecodigo.hackathon.apologies;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.mail.vandrake.VLib;
import com.mail.vandrake.control.VAssetManager;
import com.mail.vandrake.control.screen.VClearScreen;
import com.mail.vandrake.scene2d.VScreen;
import org.academiadecodigo.hackathon.apologies.game.screens.LoginScreen;

public class AllApologies extends Game {

    VLib vLib;

    public static InputMultiplexer inputMultiplexer;

    @Override
    public void create() {

        inputMultiplexer = new InputMultiplexer();

        vLib = new VLib();

        vLib.create();

        vLib.start();

        VScreen.setScreen(this, new LoginScreen());

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render() {

        VClearScreen.byGDXColor(Color.DARK_GRAY);

        super.render();
    }

    @Override
    public void dispose() {
        vLib.dispose();
    }
}