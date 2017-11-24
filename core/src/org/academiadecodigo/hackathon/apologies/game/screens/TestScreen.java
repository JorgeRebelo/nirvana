package org.academiadecodigo.hackathon.apologies.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

        Platform[] platforms = new Platform[]{
             new Platform(850, 120, new TextureRegion(new Texture("black_stair.png"))),
             new Platform(650, 220, new TextureRegion(new Texture("platform_black.png"))),
             new Platform(400, 320, new TextureRegion(new Texture("platform_black.png"))),
             new Platform(250, 450, new TextureRegion(new Texture("platform_black.png")))
        };



        bkgImage = VImage.fromFile(Gdx.files.internal("background1.jpg"));

        getGuiStage().addActor(bkgImage);
        getGuiStage().addActor(player);
        for (Platform p: platforms) {
            getGuiStage().addActor(p);
        }

    }
}
