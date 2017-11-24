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

    //powerUp
    private float moveForce = 10f;

    //Constructor
    public Player(float x, float y, World world, TextureRegion sprite) {

        super(x, y, sprite);

        body = BodyFactory.polygonShape(world, (int) x, (int) y, 0.65f, 1f, BodyDef.BodyType.DynamicBody);
        body.setFixedRotation(true);
    }


    @Override
    public void act(float delta) {

        super.act(delta);

        setPosition(body.getPosition().x, body.getPosition().y);

        //TODO JUMPING BUG
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

            body.setLinearVelocity(0, moveForce / 1.5f);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {

            body.setLinearVelocity(moveForce, body.getLinearVelocity().y);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {

            body.setLinearVelocity(-moveForce, body.getLinearVelocity().y);
        }
    }
}