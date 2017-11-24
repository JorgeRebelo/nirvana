package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mail.vandrake.control.VSound;
import org.academiadecodigo.hackathon.apologies.SoundManager;
import org.academiadecodigo.hackathon.apologies.game.objects.BodyFactory;
import org.academiadecodigo.hackathon.apologies.game.objects.GameObject;
import org.academiadecodigo.hackathon.apologies.utils.Constants;

import static org.academiadecodigo.hackathon.apologies.game.objects.AnimationFactory.textureRegion;

public class Boss extends GameObject {

    private boolean isVisible;

    //Constructor
    public Boss(float x, float y, World world) {

        super(x, y, textureRegion("self_enemy_L.png"));
        isVisible = true;

        body = BodyFactory.polygonShape(world,(int) x, (int) y,0.65f,1f,BodyDef.BodyType.DynamicBody,3);
    }

    @Override
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    @Override
    public void destroy() {

        destroy();
    }
}
