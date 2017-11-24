package org.academiadecodigo.hackathon.apologies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mail.vandrake.control.VUtils;

/**
 * Created by codecadet on 24/11/17.
 */
public class SoundManager {

    public static Music bkgMusic;

    public SoundManager() {

        init();
    }

    private void init() {

        bkgMusic = Gdx.audio.newMusic(Gdx.files.internal("bkgMusic.mp3"));
    }

    public void dispose() {

        VUtils.disposeItem(bkgMusic);
    }
}