package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject{

    //powerUp
    private Vector2 velocity;
    private Vector2 gravity;

    //Constructor
    public Player(float x, float y, TextureRegion sprite) {
        super(x,y,sprite);
        gravity = new Vector2(0,-20.8f);
        velocity = new Vector2(0, gravity.y);
    }


    @Override
    public void act(float delta) {

        super.act(delta);

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {

            setVelocityY(500);
            return;
        }

        /*
        if(getY() - velocity.y * delta < 0) {

            if(getY()>5) {

                delta = 0;
            }
        }

        //dont move
*/


        if(getY() - velocity.y * delta > 5){

            velocity.y += gravity.y;
            //TODO clamp no gameobject setposition < 0
            moveBy(0, velocity.y * delta);
        }




    }

    public void setVelocityY(int y) {
        this.velocity.y = y;
    }
}
