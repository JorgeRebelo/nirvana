package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by codecadet on 23/11/17.
 */
public class Platform extends GameObject implements Collidable {

    public Platform(float x, float y, TextureRegion sprite) {

        super(x, y, sprite);
    }


    @Override
    public boolean collidesWith(GameObject gameObject) {

        return getBounds().contains(gameObject.getBounds());
    }
}