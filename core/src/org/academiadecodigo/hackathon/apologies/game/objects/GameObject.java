package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mail.vandrake.draw.Draw;
import com.mail.vandrake.scene2d.VActor;
import org.academiadecodigo.hackathon.apologies.Constants;

import java.util.Iterator;

public class GameObject extends VActor {

    protected Body body;

    public GameObject(float x, float y, TextureRegion sprite) {
        super(x, y, sprite);

        setScale(-320f);
        setSize(sprite.getRegionWidth(), sprite.getRegionHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if (this.getSprite() != null) {

            batch.setColor(this.getColor());
            batch.draw(getSprite(), bodyX(), bodyY(), getWidth() * Constants.CAMERA_SCALE, getHeight() * Constants.CAMERA_SCALE);
            batch.setColor(Color.WHITE);
        }
    }

    private float bodyX() {

        return body.getPosition().x - (getWidth() * Constants.CAMERA_SCALE) / 2;
    }

    private float bodyY() {

        return body.getPosition().y - (getHeight() * Constants.CAMERA_SCALE) / 2;
    }

    public void destroy(Iterator iterator) {

        remove();
        iterator.remove();
    }
}