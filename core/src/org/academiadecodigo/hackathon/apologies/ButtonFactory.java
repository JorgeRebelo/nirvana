package org.academiadecodigo.hackathon.apologies;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mail.vandrake.VLib;
import com.mail.vandrake.control.GameDefs;
import com.mail.vandrake.scene2d.buttons.VTextButton;

/**
 * Created by codecadet on 23/11/17.
 */
public class ButtonFactory {

    public static TextButton textButton(String text, BitmapFont font) {

        TextButton textButton = new TextButton(text, VTextButton.createTextButtonStyle(VLib.guiSkin, GameDefs.BUTTON_UP, GameDefs.BUTTON_OVER,
                GameDefs.BUTTON_DOWN, GameDefs.BUTTON_DIS, font));
        textButton.addListener(ButtonClickHandler.getDefault());
        return textButton;
    }
}
