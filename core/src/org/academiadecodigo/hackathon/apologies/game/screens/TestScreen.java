package org.academiadecodigo.hackathon.apologies.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mail.vandrake.VLib;
import com.mail.vandrake.scene2d.VLabel;
import com.mail.vandrake.scene2d.VScreen;
import org.academiadecodigo.hackathon.apologies.AllApologies;
import org.academiadecodigo.hackathon.apologies.game.objects.Platform;
import org.academiadecodigo.hackathon.apologies.game.objects.Player;

public class TestScreen extends VScreen {

    @Override
    public void show() {
        super.show();

        AllApologies.inputMultiplexer.addProcessor(getGuiStage());

        //Player player = new Player(100, 100, VLib.guiSkin.getRegion("neko_logo"));
        //Platform platform = new Platform(300, 300, VLib.guiSkin.getRegion("window"));

        //getGuiStage().addActor(player);
        //getGuiStage().addActor(platform);

    }
}
