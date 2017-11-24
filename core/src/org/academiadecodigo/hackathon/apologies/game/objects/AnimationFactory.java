package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by codecadet on 24/11/17.
 */
public class AnimationFactory {

    public static TextureRegion textureRegion(String path) {

        return new TextureRegion(new Texture(Gdx.files.internal(path)));
    }
}