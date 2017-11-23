package org.academiadecodigo.hackathon.apologies.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mail.vandrake.control.GameDefs;
import org.academiadecodigo.hackathon.apologies.AllApologies;

public class DesktopLauncher {

    public static void main(String[] arg) {

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = GameDefs.PC_WIDTH;
        config.height = GameDefs.PC_HEIGHT;
        new LwjglApplication(new AllApologies(), config);
    }
}