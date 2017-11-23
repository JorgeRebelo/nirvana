package org.academiadecodigo.hackathon.apologies;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mail.vandrake.VLib;
import com.mail.vandrake.control.screen.VClearScreen;
import com.mail.vandrake.scene2d.VScreen;
import org.academiadecodigo.hackathon.apologies.game.screens.TestScreen;

public class AllApologies extends Game {
    VLib vLib;
    public static InputMultiplexer inputMultiplexer;

    @Override
    public void create() {
        vLib = new VLib();

        vLib.create();

        vLib.start();

        inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);

        VScreen.setScreen(this, new TestScreen());
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