package org.academiadecodigo.hackathon.apologies.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
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
import org.academiadecodigo.hackathon.apologies.Constants;
import org.academiadecodigo.hackathon.apologies.utils.EncodeDecode;
import org.academiadecodigo.hackathon.apologies.utils.Security;

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

        setupUserButtons(Constants.BUTTON_REGISTER);

        setupUserButtons(Constants.BUTTON_LOGIN);

        setupTextFields();
    }

    private void setupTextFields() {

        userNameTextField = addTextField(Constants.TEXT_FIELD_USERNAME, Constants.guiFont);
        VUtils.centerX(userNameTextField);
        userNameTextField.setY(logoImage.getY() - userNameTextField.getHeight() - 16);

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

        int space = 4;
        TextButton userButton = addButton(text, 0, 60);
        final boolean isRegister = text.equals(Constants.BUTTON_REGISTER);
        userButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {

                super.clicked(event, x, y);

                if (isRegister) {

                    clickedRegister();
                    return;
                }

                clickedLogin();
            }
        });
        userButton.setWidth(72);
        VUtils.centerX(userButton);

        float halfWidth = userButton.getWidth() / 2;
        float increment = -halfWidth - space;

        if (!isRegister) {

            increment = halfWidth + space;
        }

        userButton.moveBy(increment, 0);
    }

    private void clickedLogin() {

        boolean validForm = validateForm(Constants.INVALID_LOGIN_FORM);

        if (!validForm) {

            return;
        }
        //Create a TCP connection to server on IP?

        //send message to server
        //        EncodeDecode.LOGIN.encode("login," + Security.getHash("password"));

        //wait for server response + timeout
        VScreen.setScreen(AllApologies.getInstance(), new GameScreen());
    }

    private void clickedRegister() {

        boolean validForm = validateForm(Constants.INVALID_REGISTER_FORM);

        if (!validForm) {

            return;
        }
        //Create a TCP connection to server on IP?

        //send message to server
        //        EncodeDecode.LOGIN.encode("login," + Security.getHash("password"));

        //wait for server response + timeout
        //TODO auto-login
        VScreen.setScreen(AllApologies.getInstance(), new GameScreen());
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
        logoImage.scaleBy(-0.5f);
        VUtils.centerX(logoImage);
        logoImage.setY(Gdx.graphics.getHeight() - logoImage.getHeight() * logoImage.getScaleY());
        getGuiStage().addActor(logoImage);
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