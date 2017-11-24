package org.academiadecodigo.hackathon.apologies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mail.vandrake.control.Handler;
import com.mail.vandrake.control.VUtils;
import com.mail.vandrake.draw.font.ParameterGenerator;
import org.academiadecodigo.hackathon.apologies.utils.Constants;

/**
 * Created by codecadet on 24/11/17.
 */
public class FontHandler implements Handler {

    public static BitmapFont highscoreFont;

    public static void handleFonts() {

        ParameterGenerator parameterGenerator = new ParameterGenerator(20);
        parameterGenerator.addBorderToParameters(parameterGenerator.getParameters(), 2, Color.BLACK);
        Constants.guiFont = generateFont(Constants.HIGHSCORE_ID, parameterGenerator.getParameters());

        highscoreFont = generateFont(Constants.HIGHSCORE_ID, 40);
    }

    private static BitmapFont generateFont(String fontPath, int size) {
        ParameterGenerator parameterGenerator = new ParameterGenerator(size);
        return generateFont(fontPath, parameterGenerator.getParameters());
    }

    public static BitmapFont generateFont(String fontPath, FreeTypeFontGenerator.FreeTypeFontParameter parameters) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
        BitmapFont generatedFont = generator.generateFont(parameters);
        generator.dispose();
        return generatedFont;
    }

    @Override
    public void create() {

    }

    @Override
    public void start() {

        handleFonts();
    }

    @Override
    public void dispose() {

        VUtils.disposeItem(highscoreFont);
    }
}