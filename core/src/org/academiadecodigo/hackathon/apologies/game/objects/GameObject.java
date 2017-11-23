package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mail.vandrake.scene2d.VActor;

/**
 * Created by codecadet on 23/11/17.
 */
public abstract class GameObject extends VActor {

    public GameObject(float x, float y, TextureRegion sprite) {

        super(x, y, sprite);
    }
}