package org.academiadecodigo.hackathon.apologies;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mail.vandrake.control.VSound;

public class ButtonClickHandler extends ClickListener {
    private static ButtonClickHandler instance;

    private ButtonClickHandler() {
        instance = this;
    }

    public static ButtonClickHandler getDefault() {
        if (instance == null) {

            instance = new ButtonClickHandler();
        }
        return instance;
    }

    public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        VSound.playSound(VSound.defaultClickSound, 100.0F);
    }
}