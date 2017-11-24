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
    private Vector2 velocity;
    private Vector2 gravity;

    //Constructor
    public Player(float x, float y, TextureRegion sprite) {
        super(x, y, sprite);
        gravity = new Vector2(0, -20.8f);
        velocity = new Vector2(0, gravity.y);
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
            moveBy(-5,0);
        }

        if (getY() - velocity.y * delta > 5) {

            velocity.y += gravity.y;
            moveBy(0, velocity.y * delta);
        }

        if (getY() < 50) {
            gravity.y = 0;
            velocity.y = 0f;
            return;
        }
        gravity.y = -20.8f;

    }

    public void setVelocityY(int y) {
        this.velocity.y = y;
    }
}
