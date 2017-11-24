package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mail.vandrake.draw.Draw;
import com.mail.vandrake.scene2d.VActor;
import com.mail.vandrake.scene2d.VLabel;
import org.academiadecodigo.hackathon.apologies.AllApologies;
import org.academiadecodigo.hackathon.apologies.game.screens.GameScreen;
import org.academiadecodigo.hackathon.apologies.utils.Constants;

/**
 * Created by codecadet on 24/11/17.
 */
public class XToast extends VActor {

    private final int timeToLive;
    private Image image;
    private Image secondImage;
    private Label label;

    public XToast(int x, int y, String msg, int timeToLive, Image image, Image secondImage) {

        super(x, y);

        label = VLabel.createLabel(msg, Constants.guiFont, Color.WHITE);
        spawnTime = System.currentTimeMillis();
        this.timeToLive = timeToLive;

        if (image != null) {

            this.image = image;
        }
        if (secondImage != null) {

            this.secondImage = secondImage;
        }
    }

    private boolean added;
    private long spawnTime;
    private float curTime;

    @Override
    public void act(float delta) {

        super.act(delta);

        curTime += delta;
        if (curTime <= 1f) {

            toggleImages(image, secondImage);
        } else {

            toggleImages(secondImage, image);
            if (curTime > 2) {

                curTime = 0;
            }
        }

        if (!added) {

            added = true;
            if (label != null) {

                getStage().addActor(label);
            }
            if (image != null) {

                getStage().addActor(image);
            }
            if (secondImage != null) {

                getStage().addActor(secondImage);
            }
        }

        moveBy(0, 60 * delta);
        label.setPosition(getX(), getY());
        if (image != null) {

            image.setPosition(getX() + (label.getWidth() - image.getWidth()) / 2, getY() + 60);
            if (secondImage != null) {
                secondImage.setPosition(image.getX() - (secondImage.getWidth() - image.getWidth()) / 2, image.getY());
            }
        }

        if (System.currentTimeMillis() - spawnTime >= timeToLive * 1000) {

            label.remove();
            remove();
            if (image != null) {

                image.remove();
            }
            if (secondImage != null) {

                secondImage.remove();
            }
        }
    }

    private void toggleImages(Image image, Image secondImage) {

        if (image != null) {

            image.setVisible(false);
        }
        if (secondImage != null) {

            secondImage.setVisible(true);
        }
    }

    public static void spawnToast(String message) {

        spawnToast(message, null, null);
    }

    public static void spawnToast(String message, Image image, Image secondImage) {

        XToast toast = new XToast((int) ((Gdx.graphics.getWidth() - Draw.text.dimension.getWidth(message, Constants.guiFont)) / 2), 10, message, 3, image, secondImage);

        ((GameScreen) AllApologies.getInstance().getScreen()).getGuiStage().addActor(toast);
    }
}