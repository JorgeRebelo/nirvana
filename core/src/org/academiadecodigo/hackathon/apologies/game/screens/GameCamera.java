package org.academiadecodigo.hackathon.apologies.game.screens;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by codecadet on 23/11/17.
 */
public class GameCamera {

    private OrthographicCamera camera;

    public GameCamera(Camera camera) {

        this.camera = (OrthographicCamera) camera;
        this.camera.zoom = 0.032f;
    }


    public void act(float x, float y) {

        x = MathUtils.clamp(x, camera.viewportWidth / 2, camera.viewportWidth / 2);

        camera.position.set(x, y, 0);

    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}