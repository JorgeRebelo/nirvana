package org.academiadecodigo.hackathon.apologies.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mail.vandrake.VLib;
import com.mail.vandrake.control.VUtils;
import com.mail.vandrake.scene2d.VScreen;
import org.academiadecodigo.hackathon.apologies.game.objects.GameObject;
import org.academiadecodigo.hackathon.apologies.game.objects.Platform;

/**
 * Created by codecadet on 23/11/17.
 */
public class GameScreen extends VScreen {

    private Stage gameStage;
    private GameObject player;

    @Override
    public void show() {

        super.show();

        gameStage = new Stage();

        getGuiStage().addActor(new Platform(200, 100, VLib.guiSkin.getRegion("neko_logo")));

        gameStage.addActor(player = new Platform(100, 100, VLib.guiSkin.getRegion("neko_logo")));
    }

    @Override
    public void render(float delta) {

        gameStage.act();

        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){

            player.moveBy(30, 0);
        }

        gameStage.getCamera().position.set(player.getX(), player.getY(), 0);

        gameStage.draw();

        super.render(delta);
    }


    @Override
    public void dispose() {

        super.dispose();
        VUtils.disposeItem(gameStage);
    }
}