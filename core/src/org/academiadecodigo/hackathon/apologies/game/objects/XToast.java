package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mail.vandrake.draw.Draw;
import com.mail.vandrake.scene2d.VActor;
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
    private String message;

    public XToast(int x, int y, String msg, int timeToLive, Image image, Image secondImage) {

        super(x, y);

        this.message = msg;
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
            if (image != null) {

                getStage().addActor(image);
            }
            if (secondImage != null) {

                getStage().addActor(secondImage);
            }
        }

        moveBy(0, 60 * delta);
        if (image != null) {

            image.setPosition(getX(), getY() + 60);
            if (secondImage != null) {
                secondImage.setPosition(image.getX() - (secondImage.getWidth() - image.getWidth()) / 2, image.getY());
            }
        }

        if (System.currentTimeMillis() - spawnTime >= timeToLive * 1000) {

            remove();
            if (image != null) {

                image.remove();
            }
            if (secondImage != null) {

                secondImage.remove();
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        Draw.text.textWrappedShadowed(batch, Constants.guiFont, getX(), getY(), Gdx.graphics.getWidth() / 3, message);
    }

    private void toggleImages(Image image, Image secondImage) {

        if (image != null) {

            image.setVisible(false);
        }
        if (secondImage != null) {

            secondImage.setVisible(true);
        }
    }

    public static void spawnToast(float xUnderThirty, float yUnderTwenty, boolean convert, String message) {

        spawnToast(xUnderThirty / (convert ? Constants.CAMERA_SCALE : 1), yUnderTwenty / (convert ? Constants.CAMERA_SCALE : 1), message);
    }

    public static void spawnToast(float x, float y, String message) {

        spawnToast(x, y, message, null, null);
    }

    public static void spawnToast(float x, float y, String message, Image image, Image secondImage) {

        XToast toast = new XToast((int) x, (int) y, message, 3, image, secondImage);

        ((GameScreen) AllApologies.getInstance().getScreen()).getGuiStage().addActor(toast);
    }
}