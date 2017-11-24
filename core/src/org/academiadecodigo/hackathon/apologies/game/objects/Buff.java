package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by codecadet on 24/11/17.
 */
public class Buff extends GameObject {

    public Buff(float x, float y, World world, BuffMessage sprite) {

        super(x, y, sprite);

        body = BodyFactory.polygonShape(world, (int) x, (int) y, 0.5f, 0.5f, BodyDef.BodyType.KinematicBody);
        body.setFixedRotation(true);
    }
}