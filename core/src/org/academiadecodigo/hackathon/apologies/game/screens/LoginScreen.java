package org.academiadecodigo.hackathon.apologies.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mail.vandrake.VLib;
import com.mail.vandrake.control.VUtils;
import com.mail.vandrake.scene2d.*;
import org.academiadecodigo.hackathon.apologies.AllApologies;
import org.academiadecodigo.hackathon.apologies.ButtonFactory;
import org.academiadecodigo.hackathon.apologies.servercomunication.Connection;
import org.academiadecodigo.hackathon.apologies.servercomunication.ServerParser;
import org.academiadecodigo.hackathon.apologies.utils.Constants;

import java.net.ServerSocket;

/**
 * Created by codecadet on 23/11/17.
 */
public class LoginScreen extends VScreen {

    private Image logoImage;
    private TextField userNameTextField;
    private TextField passwordTextField;

    @Override
    public void show() {

        super.show();

        AllApologies.inputMultiplexer.addProcessor(getGuiStage());

        setupLogoImage();

        setupQuitButton();

        setupUserButtons(Constants.BUTTON_LOGIN);

        setupTextFields();
    }

    private void setupTextFields() {

        userNameTextField = addTextField(Constants.TEXT_FIELD_USERNAME, Constants.guiFont);
        VUtils.centerX(userNameTextField);
        userNameTextField.setY(logoImage.getY() - 100 - userNameTextField.getHeight() - 16);

        passwordTextField = addTextField(Constants.TEXT_FIELD_PASSWORD, Constants.guiFont);
        passwordTextField.setPosition(userNameTextField.getX(), userNameTextField.getY() - passwordTextField.getHeight() - 16);
        passwordTextField.setPasswordMode(true);
        passwordTextField.setPasswordCharacter('*');
    }

    private TextField addTextField(String defaultText, BitmapFont font) {

        TextField textField = VTextField.txtField(VLib.guiSkin, "", defaultText, font, Color.WHITE);
        getGuiStage().addActor(textField);
        return textField;
    }

    private void setupUserButtons(String text) {

        TextButton userButton = addButton(text, 0, 60);
        userButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {

                super.clicked(event, x, y);

                clickedLogin();
            }
        });
        VUtils.centerX(userButton);
    }

    private void clickedLogin() {

        boolean validForm = validateForm(Constants.INVALID_LOGIN_FORM);

        if (!validForm) {

            return;
        }

        boolean success = ServerParser.sendLogin(userNameTextField.getText(), passwordTextField.getText());

        if (success){

            VScreen.setScreen(AllApologies.getInstance(), new GameScreen(userNameTextField.getText()));
        }
    }

    private boolean validateForm(String messageToShow) {

        boolean validForm = LoginFormParser.validForm(userNameTextField.getText(), passwordTextField.getText());

        if (!validForm) {

            setErrorMessageTitle(messageToShow);
        }

        return validForm;
    }

    private void setErrorMessageTitle(String messageToShow) {

        Dialog dialog = VDialog.openDialog(getGuiStage(), Constants.defaultWindowStyle(), Color.WHITE, messageToShow);
        dialog.setHeight(Gdx.graphics.getHeight() / 9);
        dialog.button(ButtonFactory.textButton("OK", Constants.guiFont));
    }

    private void setupLogoImage() {

        logoImage = VImage.fromFile(Gdx.files.internal(Constants.LOGIN_BKG_IMAGE));
        VUtils.centerX(logoImage);
        logoImage.setY(Gdx.graphics.getHeight() - logoImage.getHeight() * logoImage.getScaleY() + 100);
        getGuiStage().addActor(logoImage);
        logoImage.addAction(Actions.parallel(Actions.alpha(0.0f), Actions.moveBy(0, -100, 1f), Actions.alpha(1.0f, 1f)));
    }

    private void setupQuitButton() {

        TextButton quitButton = addButton(Constants.BUTTON_QUIT, 10, 10);
        quitButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                Gdx.app.exit();
            }
        });
    }

    private TextButton addButton(String text, float x, float y) {

        TextButton button = ButtonFactory.textButton(text, Constants.guiFont);
        button.setPosition(x, y);
        getGuiStage().addActor(button);
        return button;
    }
}