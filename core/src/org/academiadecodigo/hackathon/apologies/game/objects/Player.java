package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mail.vandrake.control.VSound;
import org.academiadecodigo.hackathon.apologies.SoundManager;

public class Player extends GameObject {

    private float moveForce = 8f;
    private long lastPlayed = 0;

    //Constructor
    public Player(float x, float y, World world, TextureRegion sprite) {

        super(x, y, sprite);

        body = BodyFactory.polygonShape(world, (int) x, (int) y, 0.65f, 1f, BodyDef.BodyType.DynamicBody);
        body.setFixedRotation(true);
    }


    @Override
    public void act(float delta) {

        super.act(delta);

        //TODO JUMPING BUG
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || (Gdx.input.isKeyPressed(Input.Keys.W))) {

            body.setLinearVelocity(0, moveForce);
        }

        handleMove();

        setPosition(body.getPosition().x, body.getPosition().y);
    }

    private void handleMove() {

        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.A) ||
                Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

            step();
            boolean isRight = (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT));
            boolean isLeft = (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT));
            if (isLeft == isRight){
                return;
            }
            body.setLinearVelocity(isRight ? moveForce : -moveForce, body.getLinearVelocity().y);
        }
    }

    private void step() {

        if (System.currentTimeMillis() - lastPlayed >= 500) {

            //TODO is airborne
            VSound.playSound(SoundManager.stepSound, 50f);
            lastPlayed = System.currentTimeMillis();
        }
    }
}