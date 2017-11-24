package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.mail.vandrake.control.VSound;
import com.sun.tools.internal.jxc.ap.Const;
import javafx.concurrent.Task;
import org.academiadecodigo.hackathon.apologies.SoundManager;
import org.academiadecodigo.hackathon.apologies.game.screens.GameScreen;
import org.academiadecodigo.hackathon.apologies.utils.Constants;

import java.sql.Time;
import java.util.Arrays;
import java.util.TimerTask;

import static org.academiadecodigo.hackathon.apologies.game.objects.AnimationFactory.*;

public class Player extends GameObject {

    private float moveForceX = 10f;
    private float moveForceY = 17f;
    private long lastPlayed = 0;
    private int seconds = 20;
    private TextureRegion playerImage;
    private GameScreen gameScreen;
    private Animation<TextureRegion> walkRight[] = new Animation[5];
    private Animation<TextureRegion> walkLeft[] = new Animation[5];
    private String userName;

    //Constructor
    public Player(float x, float y, World world, String userName, GameScreen gameScreen) {

        super(x, y, textureRegion("player_1_L1.png"));
        playerImage = textureRegion("player_4_R1.png");
        body = BodyFactory.polygonShape(world, (int) x, (int) y, 0.65f, 1f, BodyDef.BodyType.DynamicBody, 3);
        body.setFixedRotation(true);
        this.gameScreen = gameScreen;
        for (int level = 1; level < 5; level++) {

            String playerLevel = "player_";
            walkRight[level] = new Animation<TextureRegion>(Constants.ANIMATION_FRAME_TIME, textureRegion(playerLevel + level + "_L1.png"),
                    textureRegion(playerLevel + level + "_L2.png"));
            walkRight[level].setPlayMode(Animation.PlayMode.LOOP);

            walkLeft[level] = new Animation<TextureRegion>(Constants.ANIMATION_FRAME_TIME, textureRegion(playerLevel + level + "_R1.png"),
                    textureRegion(playerLevel + level + "_R2.png"));
            walkLeft[level].setPlayMode(Animation.PlayMode.LOOP);
        }

        this.userName = userName;
    }

    private boolean isWalkingRight;
    private int level = 1;
    private float curTime;
    private long frozenKeys;

    private long lastMessageSent;
    private int messageId;

    public void upLevel() {

        level++;
        moveForceY += 2.5f;
        if (level == 4) {

            gameScreen.showBoss();

            frozenKeys = System.currentTimeMillis();
        }
    }

    private String[] messages = new String[]{
            "Who are you?", "Who should I be?", "All apologies..",
            "You can start being one among others", "I wish I was like you",
            "Everything is my fault", "You don't have to", "Let's try it together",
            "No more apologies!"
    };

    @Override
    public void act(float delta) {

        super.act(delta);

        curTime += delta;
        if (body.getLinearVelocity().x == 0) {

            curTime = 0;
        }

        if (System.currentTimeMillis() - frozenKeys < seconds * 1000) {

            if (System.currentTimeMillis() - lastMessageSent >= 2000) {

                //send message
                //0, 3, 6, 7, 8
                float x = 19 / Constants.CAMERA_SCALE;
                int y = Gdx.graphics.getWidth() / 3;
                if (messageId == 0 || messageId == 3 || messageId >= 6) {

                    x = 8;
                }

                if (messageId >= messages.length) {

                    gameScreen.loadHighscore();
                    return;
                }

                XToast.spawnToast(x, y, messages[messageId++]);
                lastMessageSent = System.currentTimeMillis();
            }

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

        boolean isJetPack = Gdx.input.isKeyPressed(Input.Keys.J);
        if (((Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.UP) ||
                Gdx.input.isKeyJustPressed(Input.Keys.W)) && body.getLinearVelocity().y == 0) || isJetPack) {

            body.setLinearVelocity(0, moveForceY);
        }
    }

    private void step() {

        if (System.currentTimeMillis() - lastPlayed >= 700) {

            //TODO is airborne
            //VSound.playSound(SoundManager.stepSound, 50f);
            lastPlayed = System.currentTimeMillis();
        }
    }

    public TextureRegion getPlayerImage() {
        return playerImage;
    }

    public String getUserName() {
        return userName;
    }
}