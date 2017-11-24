package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mail.vandrake.control.VAssetManager;
import com.mail.vandrake.control.VSound;
import com.mail.vandrake.scene2d.VImage;
import org.academiadecodigo.hackathon.apologies.AllApologies;
import org.academiadecodigo.hackathon.apologies.SoundManager;
import org.academiadecodigo.hackathon.apologies.game.screens.GameScreen;

/**
 * Created by codecadet on 24/11/17.
 */
public class Buff extends GameObject {

    private BuffMessage buffMessage;
    private Player player;
    private boolean collided;
    private int counter;

    public Buff(float x, float y, World world, BuffMessage buffMessage, TextureRegion sprite, Player player) {

        super(x, y, sprite);
        counter = 0;
        this.player = player;
        this.buffMessage = buffMessage;

        body = BodyFactory.polygonShape(world, (int) x, (int) y, 0.5f, 0.5f, BodyDef.BodyType.KinematicBody, 3, this);
        body.setFixedRotation(true);
    }


    @Override
    public void destroy() {

        if (!collided) {

            counter++;

            if(counter == 3) {
                //((GameScreen) AllApologies.getInstance().getScreen()).showBoss();

            }

            XToast.spawnToast(getX(), getY(), buffMessage.getMessage(),VImage.fromFile(Gdx.files.internal("gugu1.png")), VImage.fromFile(Gdx.files.internal("gugu.png")));
            collided = true;
            ((GameScreen) AllApologies.getInstance().getScreen()).swapBackgrounds();
            VSound.playSound(SoundManager.buffSound, 50f);
        }

        super.destroy();
    }
}