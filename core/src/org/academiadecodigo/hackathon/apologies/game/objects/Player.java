package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject{

    //powerUp
    private Vector2 velocity;
    private Vector2 gravity;

    //Constructor
    public Player(float x, float y, TextureRegion sprite) {
        super(x,y,sprite);
        velocity = new Vector2(0,0);
        gravity = new Vector2(0,-90);
    }


    @Override
    public void act(float delta) {

        super.act(delta);

        if(getY() - velocity.y * delta < 0)
        //dont move
        moveBy(0, velocity.y * delta);

    }



}
