package org.academiadecodigo.hackathon.apologies.game.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mail.vandrake.control.VUtils;
import com.mail.vandrake.draw.Draw;
import com.mail.vandrake.draw.font.VFont;
import com.mail.vandrake.scene2d.VActor;
import com.mail.vandrake.scene2d.VDialog;
import com.mail.vandrake.scene2d.VLabel;
import org.academiadecodigo.hackathon.apologies.AllApologies;
import org.academiadecodigo.hackathon.apologies.game.screens.GameScreen;
import org.academiadecodigo.hackathon.apologies.utils.Constants;

/**
 * Created by codecadet on 24/11/17.
 */
public class XToast extends VActor {

    private final int timeToLive;
    private Label label;

    public XToast(int x, int y, String msg, int timeToLive) {

        super(x, y);

        label = VLabel.createLabel(msg, Constants.guiFont, Color.WHITE);
        spawnTime = System.currentTimeMillis();
        this.timeToLive = timeToLive;
    }

    private boolean added;
    private long spawnTime;

    @Override
    public void act(float delta) {

        super.act(delta);

        if (!added) {

            added = true;
            getStage().addActor(label);
        }

        moveBy(0, 60 * delta);
        label.setPosition(getX(), getY());

        if (System.currentTimeMillis() - spawnTime >= timeToLive * 1000) {

            label.remove();
            remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);

    }

    public static void spawnToast(String message) {

        XToast toast = new XToast((int) ((Gdx.graphics.getWidth() - Draw.text.dimension.getWidth(message, Constants.guiFont)) / 2), 10, message, 3);

        ((GameScreen) AllApologies.getInstance().getScreen()).getGuiStage().addActor(toast);

    }
}