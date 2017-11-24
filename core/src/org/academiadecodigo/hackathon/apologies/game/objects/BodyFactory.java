package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by codecadet on 23/11/17.
 */
public class BodyFactory {

    public static Body polygonShape(World world, float x, float y, float width, float height, BodyDef.BodyType bodyType) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(x, y);


        Body body = world.createBody(bodyDef);
        PolygonShape square = new PolygonShape();
        square.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = square;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 1f;
        body.createFixture(fixtureDef);
        fixtureDef.isSensor = true;

        square.dispose();

        return body;
    }
    public static PolygonShape wall(World world, int x, int y, float width, float height, float friction) {

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(x, y);
        groundBodyDef.type = BodyDef.BodyType.StaticBody;

        Body groundBody = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(width, height);
        Fixture fixture = groundBody.createFixture(groundBox, 0.0f);
        fixture.setFriction(friction);

        groundBox.dispose();

        return groundBox;
    }

    public static PolygonShape ground(World world, Camera camera) {

        return wall(world, 0, -1, camera.viewportWidth, 10, 1);
    }
}
