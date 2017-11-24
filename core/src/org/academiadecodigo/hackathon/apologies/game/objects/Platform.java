package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Iterator;

/**
 * Created by codecadet on 23/11/17.
 */
public class Platform extends GameObject implements Collidable {

    public Platform(float x, float y, World world, TextureRegion sprite) {

        super(x, y, sprite);

        body = BodyFactory.polygonShape(world, (int) x, (int) y, 0.5f, 0.5f, BodyDef.BodyType.StaticBody);
        body.setFixedRotation(true);
    }

    @Override
    public boolean collidesWith(GameObject gameObject) {

        return getBounds().overlaps(gameObject.getBounds());
    }

    @Override
    public void destroy(Iterator iterator) {

        remove();
        iterator.remove();
    }
}