package org.academiadecodigo.hackathon.apologies.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mail.vandrake.VLib;
import com.mail.vandrake.scene2d.VImage;
import com.mail.vandrake.scene2d.VLabel;
import com.mail.vandrake.scene2d.VScreen;
import org.academiadecodigo.hackathon.apologies.AllApologies;
import org.academiadecodigo.hackathon.apologies.game.objects.Platform;
import org.academiadecodigo.hackathon.apologies.game.objects.Player;

public class TestScreen extends VScreen {

    private Image bkgImage;

    @Override
    public void show() {
        super.show();

        AllApologies.inputMultiplexer.addProcessor(getGuiStage());

        Player player = new Player(100, 100, VLib.guiSkin.getRegion("neko_logo"));
        Platform platform = new Platform(300, 300, VLib.guiSkin.getRegion("window"));

        bkgImage = VImage.fromFile(Gdx.files.internal("background1.jpg"));

        getGuiStage().addActor(bkgImage);
        getGuiStage().addActor(player);
        getGuiStage().addActor(platform);

    }
}
