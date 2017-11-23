package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mail.vandrake.scene2d.VActor;

public class GameObject extends VActor {

    protected Body body;

    public GameObject(float x, float y, TextureRegion sprite) {
        super(x, y, sprite);

        setSize(sprite.getRegionWidth(), sprite.getRegionHeight());
    }


    /*
    @Override
    public float getWidth() {
        return getWidth();
    }

    @Override
    public float getHeight() {
        return getHeight();
    }
    */
}