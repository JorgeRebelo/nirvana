package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mail.vandrake.control.VAssetManager;
import com.mail.vandrake.control.VSound;
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

    public Buff(float x, float y, World world, BuffMessage buffMessage, TextureRegion sprite, Player player) {

        super(x, y, sprite);
        this.player = player;
        this.buffMessage = buffMessage;

        body = BodyFactory.polygonShape(world, (int) x, (int) y, 0.5f, 0.5f, BodyDef.BodyType.KinematicBody, this);
        body.setFixedRotation(true);
    }


    @Override
    public void destroy() {

        if (!collided) {

            collided = true;
            ((GameScreen) AllApologies.getInstance().getScreen()).swapBackgrounds();
            VSound.playSound(SoundManager.buffSound, 50f);
            XToast.spawnToast(buffMessage.getMessage());
            player.upLevel();

        }

        super.destroy();
    }
}