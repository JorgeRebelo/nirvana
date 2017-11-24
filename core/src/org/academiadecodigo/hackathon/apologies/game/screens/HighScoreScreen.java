package org.academiadecodigo.hackathon.apologies.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mail.vandrake.control.VAssetManager;
import com.mail.vandrake.control.VUtils;
import com.mail.vandrake.draw.font.ParameterGenerator;
import com.mail.vandrake.draw.font.VFont;
import com.mail.vandrake.scene2d.VLabel;
import com.mail.vandrake.scene2d.VScreen;
import org.academiadecodigo.hackathon.apologies.AllApologies;
import org.academiadecodigo.hackathon.apologies.ButtonFactory;
import org.academiadecodigo.hackathon.apologies.FontHandler;
import org.academiadecodigo.hackathon.apologies.utils.Constants;

import java.util.Map;

/**
 * Created by codecadet on 24/11/17.
 */
public class HighScoreScreen extends VScreen {

    private TextButton backButton;
    private Label highscoreLabel;
    private Map<String, Integer> highScoreMap;
    private String username;
    private int score;

    public HighScoreScreen(Map<String, Integer> highScoreMap, String username, int score) {

        this.highScoreMap = highScoreMap;

        this.username = username;
        this.score = score;
    }

    @Override
    public void show() {
        super.show();

        VAssetManager.waitUntilAllAssetsLoaded();

        setupButtons();

        setupHighscoreLabel();

        int i = highScoreMap.keySet().size();
        for (String username : highScoreMap.keySet()) {

            int score = highScoreMap.get(username);
            String position = String.valueOf(highScoreMap.keySet().size() - i + 1);

            Label userScore = VLabel.createLabel(position + "| " + username + " |" + score, Constants.guiFont, Color.WHITE);
            userScore.setPosition(Gdx.graphics.getWidth() / 2 - 80, 200 + (i * 30));
            getGuiStage().addActor(userScore);
            i--;
        }
        Label userScore = VLabel.createLabel("You| " + username + " |" + score, Constants.guiFont, Color.GOLD);
        userScore.setPosition(Gdx.graphics.getWidth() / 2 - 80, 200 + (i * 30));
        getGuiStage().addActor(userScore);

        AllApologies.inputMultiplexer.addProcessor(getGuiStage());
    }

    private void setupHighscoreLabel() {

        highscoreLabel = VLabel.createLabel(Constants.HIGHSCORE, FontHandler.highscoreFont, Color.WHITE);
        VUtils.centerX(highscoreLabel);
        highscoreLabel.setY(Gdx.graphics.getHeight() - 120);
        getGuiStage().addActor(highscoreLabel);
    }

    private void setupButtons() {

        setupQuitButton();
    }

    private void setupQuitButton() {

        TextButton quitButton = addButton(Constants.BUTTON_BACK, 10, 10);
        quitButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                AllApologies.getInstance().setScreen(new LoginScreen());
            }
        });
    }

    private TextButton addButton(String text, float x, float y) {

        TextButton button = ButtonFactory.textButton(text, Constants.guiFont);
        button.setPosition(x, y);
        getGuiStage().addActor(button);
        return button;
    }


    @Override
    public void dispose() {
        super.dispose();

    }
}