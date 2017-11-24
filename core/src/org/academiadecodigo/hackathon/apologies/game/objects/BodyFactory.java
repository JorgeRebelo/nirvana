package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by codecadet on 23/11/17.
 */
public class BodyFactory {

    public static Body polygonShape(World world, int x, int y, float width, float height, BodyDef.BodyType bodyType) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(x, y);


        Body body = world.createBody(bodyDef);
        PolygonShape square = new PolygonShape();
        square.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = square;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 5f;
        body.createFixture(fixtureDef);
        fixtureDef.isSensor = true;

        square.dispose();

        return body;
    }

    public static PolygonShape ground(World world, Camera camera) {

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(0, 0);
        groundBodyDef.type = BodyDef.BodyType.StaticBody;

        Body groundBody = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(camera.viewportWidth, 10.0f);
        groundBody.createFixture(groundBox, 0.0f);

        groundBox.dispose();

        return groundBox;
    }
}
