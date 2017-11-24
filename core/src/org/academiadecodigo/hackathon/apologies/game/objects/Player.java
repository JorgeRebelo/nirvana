package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mail.vandrake.control.VSound;
import org.academiadecodigo.hackathon.apologies.SoundManager;
import org.academiadecodigo.hackathon.apologies.utils.Constants;

import static org.academiadecodigo.hackathon.apologies.game.objects.AnimationFactory.*;

public class Player extends GameObject {

    private float moveForce = 8f;
    private long lastPlayed = 0;

    private Animation<TextureRegion> walkRight[] = new Animation[2];
    private Animation<TextureRegion> walkLeft[] = new Animation[2];

    //Constructor
    public Player(float x, float y, World world, TextureRegion sprite) {

        super(x, y, sprite);

        body = BodyFactory.polygonShape(world, (int) x, (int) y, 0.65f, 1f, BodyDef.BodyType.DynamicBody);
        body.setFixedRotation(true);

        for (int i = 1; i < 2; i++) {

            //TODO replace this
            String playerLevel = "player_lvl";
            walkRight[i] = new Animation<TextureRegion>(Constants.ANIMATION_FRAME_TIME, textureRegion(playerLevel + i + "_L.png"));
            walkRight[i].setPlayMode(Animation.PlayMode.LOOP);

            walkLeft[i] = new Animation<TextureRegion>(Constants.ANIMATION_FRAME_TIME, textureRegion(playerLevel + i + "_R.png"));
            walkLeft[i].setPlayMode(Animation.PlayMode.LOOP);
        }
    }

    private boolean isWalkingRight;
    private int level = 1;
    private float curTime;

    public void upLevel() {

        level++;
        if (level > 4) {

            level = 4;
        }
    }

    @Override
    public void act(float delta) {

        super.act(delta);

        curTime += delta;

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || (Gdx.input.isKeyPressed(Input.Keys.W))) {

            body.setLinearVelocity(0, moveForce);
        }

        handleMove();

        setPosition(body.getPosition().x, body.getPosition().y);

        if (isWalkingRight) {

            setSprite(walkRight[level].getKeyFrame(curTime));
        } else {

            setSprite(walkLeft[level].getKeyFrame(curTime));
        }
    }

    private void handleMove() {

        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.A)) {

            step();
            boolean isRight = Gdx.input.isKeyPressed(Input.Keys.D);
            isWalkingRight = !isRight;
            body.setLinearVelocity(isRight ? moveForce : -moveForce, body.getLinearVelocity().y);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && body.getLinearVelocity().y == 0) {

            body.setLinearVelocity(0, moveForce * 1.5f);
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