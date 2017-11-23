package org.academiadecodigo.hackathon.apologies.game.screens;

import com.mail.vandrake.VLib;
import com.mail.vandrake.scene2d.VLabel;
import com.mail.vandrake.scene2d.VScreen;
import org.academiadecodigo.hackathon.apologies.AllApologies;
import org.academiadecodigo.hackathon.apologies.game.objects.Player;

public class TestScreen extends VScreen {

    @Override
    public void show() {
        super.show();

        AllApologies.inputMultiplexer.addProcessor(getGuiStage());

        getGuiStage().addActor(new Player(100, 100, VLib.guiSkin.getRegion("neko_logo")));
    }
}