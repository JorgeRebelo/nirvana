package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

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

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && velocity.y==0) {
            setVelocityY(500);
            return;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            moveBy(5,0);
            //gravity.x = 30;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            moveBy(-5,0);
        }

        if (getY() - velocity.y * delta > 5) {

            velocity.y += gravity.y;
            moveBy(0, velocity.y * delta);
        }

        if (getY() < 20) {
            gravity.y = 0;
            velocity.y = 0;
            return;
        }
        gravity.y = -20.8f;

    }

    public void setVelocityY(int y) {
        this.velocity.y = y;
    }
}
