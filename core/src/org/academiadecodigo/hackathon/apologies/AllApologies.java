package org.academiadecodigo.hackathon.apologies;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mail.vandrake.VLib;

public class AllApologies extends Game {
    VLib vLib;

    @Override
    public void create() {
        vLib = new VLib();

        vLib.create();

        vLib.start();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }

    @Override
    public void dispose() {
        vLib.dispose();
    }
}