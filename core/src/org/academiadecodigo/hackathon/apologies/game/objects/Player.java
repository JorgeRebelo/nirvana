package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mail.vandrake.control.VSound;
import org.academiadecodigo.hackathon.apologies.SoundManager;
import org.academiadecodigo.hackathon.apologies.utils.Constants;

import static org.academiadecodigo.hackathon.apologies.game.objects.AnimationFactory.*;

public class Player extends GameObject {

    private float moveForceX = 10f;
    private float moveForceY = 15f;
    private long lastPlayed = 0;
    private int lives = 3;
    private int seconds = 20;
    private TextureRegion playerImage;
    private Animation<TextureRegion> walkRight[] = new Animation[5];
    private Animation<TextureRegion> walkLeft[] = new Animation[5];

    //Constructor
    public Player(float x, float y, World world) {

        super(x, y, textureRegion("player_1_L1.png"));
        playerImage = textureRegion("player_4_R1.png");

        body = BodyFactory.polygonShape(world, (int) x, (int) y, 0.65f, 1f, BodyDef.BodyType.DynamicBody, 3);
        body.setFixedRotation(true);

        for (int level = 1; level < 5; level++) {

            String playerLevel = "player_";
            walkRight[level] = new Animation<TextureRegion>(Constants.ANIMATION_FRAME_TIME, textureRegion(playerLevel + level + "_L1.png"),
                    textureRegion(playerLevel + level + "_L2.png"));
            walkRight[level].setPlayMode(Animation.PlayMode.LOOP);

            walkLeft[level] = new Animation<TextureRegion>(Constants.ANIMATION_FRAME_TIME, textureRegion(playerLevel + level + "_R1.png"),
                    textureRegion(playerLevel + level + "_R2.png"));
            walkLeft[level].setPlayMode(Animation.PlayMode.LOOP);
        }
    }

    private boolean isWalkingRight;
    private int level = 1;
    private float curTime;
    private long frozenKeys;

    public void upLevel() {

        level++;
        moveForceY += 2.5f;
        if (level > 4) {

            level = 4;
        }

        frozenKeys = System.currentTimeMillis();
    }

    public int getLives() {
        return lives;
    }

    @Override
    public void act(float delta) {

        super.act(delta);

        curTime += delta;
        if (body.getLinearVelocity().x == 0) {

            curTime = 0;
        }

        if (System.currentTimeMillis() - frozenKeys < seconds * 1000) {

            //TODO
            return;
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

        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.A) ||
                Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

            step();
            boolean isRight = (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT));
            boolean isLeft = (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT));
            isWalkingRight = !isRight;
            if (isLeft == isRight) {
                return;
            }
            body.setLinearVelocity(isRight ? moveForceX : -moveForceX, body.getLinearVelocity().y);
        }

        if ((Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.UP) ||
                Gdx.input.isKeyJustPressed(Input.Keys.W)) && body.getLinearVelocity().y == 0) {

            body.setLinearVelocity(0, moveForceY);
        }
    }

    private void step() {

        if (System.currentTimeMillis() - lastPlayed >= 500) {

            //TODO is airborne
            VSound.playSound(SoundManager.stepSound, 50f);
            lastPlayed = System.currentTimeMillis();
        }
    }

    public TextureRegion getPlayerImage() {
        return playerImage;
    }

    @Override
    public void destroy() {

        lives--;
        if (lives <= 0) {

            destroy();
        }
    }
}