package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject{

    //powerUp
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;


    public Player(float x, float y, TextureRegion sprite) {
        super(x, y, sprite);
        position = new Vector2(x, y);
        velocity = new Vector2(0,0);
        acceleration = new Vector2(0,0);

    }

    @Override
    public void act(float delta) {
        super.act(delta);


    }



}
