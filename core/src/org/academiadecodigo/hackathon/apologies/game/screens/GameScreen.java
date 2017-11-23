package org.academiadecodigo.hackathon.apologies.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mail.vandrake.VLib;
import com.mail.vandrake.control.VUtils;
import com.mail.vandrake.scene2d.VScreen;
import org.academiadecodigo.hackathon.apologies.game.objects.GameObject;
import org.academiadecodigo.hackathon.apologies.game.objects.Platform;
import org.academiadecodigo.hackathon.apologies.game.objects.Player;

/**
 * Created by codecadet on 23/11/17.
 */
public class GameScreen extends VScreen {

    private Stage gameStage;
    private GameObject player;
    private GameObject platform;

    @Override
    public void show() {

        super.show();

        gameStage = new Stage();

       // getGuiStage().addActor(new Player(200, 100, VLib.guiSkin.getRegion("neko_logo")));
        gameStage.addActor(platform = new Platform(300,300, VLib.guiSkin.getRegion("window")));

        gameStage.addActor(player = new Player(100, 100, VLib.guiSkin.getRegion("neko_logo")));
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