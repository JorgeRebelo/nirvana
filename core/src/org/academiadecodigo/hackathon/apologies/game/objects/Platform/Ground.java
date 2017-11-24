package org.academiadecodigo.hackathon.apologies.game.objects.Platform;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import org.academiadecodigo.hackathon.apologies.game.objects.BodyFactory;
import org.academiadecodigo.hackathon.apologies.game.objects.GameObject;

import java.util.Iterator;

/**
 * Created by codecadet on 23/11/17.
 */
public class Ground extends GameObject {

    public Ground(float x, float y, World world, TextureRegion sprite) {

        super(x, y, sprite);

        body = BodyFactory.polygonShape(world, (int) x, (int) y, 16f, 0.5f, BodyDef.BodyType.StaticBody, 3);
        body.setFixedRotation(true);
    }
}