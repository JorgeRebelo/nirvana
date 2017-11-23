package org.academiadecodigo.hackathon.apologies.game.screens;

import com.mail.vandrake.scene2d.VScreen;
import org.academiadecodigo.hackathon.apologies.game.objects.GameObject;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by codecadet on 23/11/17.
 */
public class GameScreen extends VScreen {

    private Map<Integer, GameObject> gameObjects = new Hashtable<Integer, GameObject>();

    @Override
    public void show() {

        super.show();
    }
}