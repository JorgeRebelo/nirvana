package org.academiadecodigo.hackathon.apologies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mail.vandrake.control.VUtils;

/**
 * Created by codecadet on 24/11/17.
 */
public class SoundManager {

    public static Music bkgMusic;
    public static Sound stepSound;

    public SoundManager() {

        init();
    }

    private void init() {

        bkgMusic = Gdx.audio.newMusic(Gdx.files.internal("bkgMusic.mp3"));
        stepSound = Gdx.audio.newSound(Gdx.files.internal("footstep.ogg"));
    }

    public void dispose() {

        VUtils.disposeItem(bkgMusic);
        VUtils.disposeItem(stepSound);
    }
}