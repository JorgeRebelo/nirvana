package org.academiadecodigo.hackathon.apologies.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.mail.vandrake.control.VAssetManager;

public enum BuffMessage {


    EMPATHY("           Respect other people's feelings. \nIt might mean nothing to you,but everything to them", "orb_red"),
    GRATITUDE("Be grateful for what you already \nhave while you pursue your goals", "orb_green"),
    SELF_WORTH("Accept yourself, love yourself, and keep moving forward.", "orb_blue");
    private String message;
    private String orbPath;

    BuffMessage(String string, String orbPath) {

        message = string;
        this.orbPath = orbPath + ".png";
    }

    public String getOrbPath() {
        return orbPath;
    }

    public static void loadAllAssets() {

        for (BuffMessage buffMessage : values()) {

            VAssetManager.loadAsset(buffMessage.getOrbPath(), Texture.class);
        }
    }

    public String getMessage() {
        return message;
    }
}
