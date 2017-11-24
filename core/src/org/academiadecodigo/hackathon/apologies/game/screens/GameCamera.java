package org.academiadecodigo.hackathon.apologies.game.screens;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.mail.vandrake.control.GameDefs;
import org.academiadecodigo.hackathon.apologies.Constants;

/**
 * Created by codecadet on 23/11/17.
 */
public class GameCamera {

    private OrthographicCamera camera;

    public GameCamera(Camera camera) {

        this.camera = (OrthographicCamera) camera;
        this.camera.zoom = Constants.CAMERA_SCALE;
    }


    public void act(float x, float y) {

        x = MathUtils.clamp(x, realViewportWidth() / 2, realViewportWidth() / 2);

        System.out.println(y);
        y = MathUtils.clamp(y, realViewportHeight(), 4 * ((GameDefs.PC_HEIGHT - 3) * Constants.CAMERA_SCALE));

        camera.position.set(x, y, 0);
        camera.update();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    private float realViewportWidth() {

        return camera.viewportWidth * camera.zoom;
    }
    private float realViewportHeight() {

        return camera.viewportHeight * camera.zoom;
    }
}