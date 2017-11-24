package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends GameObject {

    private float moveForce = 10f;
    private Body[] slipperySides = new Body[2];

    //Constructor
    public Player(float x, float y, World world, TextureRegion sprite) {

        super(x, y, sprite);

        for (int i = 0; i < slipperySides.length; i++) {

            slipperySides[i] = BodyFactory.polygonShape(world, x - 0.01f, y, 0.01f, 1f, BodyDef.BodyType.DynamicBody);
            slipperySides[i].getFixtureList().get(0).setFriction(0);
        }
        body = BodyFactory.polygonShape(world, (int) x, (int) y, 0.65f, 1f, BodyDef.BodyType.DynamicBody);
        body.setFixedRotation(true);
    }


    @Override
    public void act(float delta) {

        super.act(delta);

        //TODO JUMPING BUG
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

            body.setLinearVelocity(0, moveForce * 2.5f);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {

            body.setLinearVelocity(moveForce, body.getLinearVelocity().y);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {

            body.setLinearVelocity(-moveForce, body.getLinearVelocity().y);
        }

        clampBodyPosition();

        setPosition(body.getPosition().x, body.getPosition().y);
    }

    private void clampBodyPosition() {

        if (body.getPosition().x < 0) {

            body.getPosition().x = 0;
        }
    }
}