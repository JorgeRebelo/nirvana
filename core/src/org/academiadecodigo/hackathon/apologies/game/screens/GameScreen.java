package org.academiadecodigo.hackathon.apologies.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mail.vandrake.VLib;
import com.mail.vandrake.control.VAssetManager;
import com.mail.vandrake.control.VSound;
import com.mail.vandrake.control.VUtils;
import com.mail.vandrake.scene2d.VImage;
import com.mail.vandrake.scene2d.VLabel;
import com.mail.vandrake.scene2d.VScreen;
import org.academiadecodigo.hackathon.apologies.SoundManager;
import org.academiadecodigo.hackathon.apologies.game.objects.*;
import org.academiadecodigo.hackathon.apologies.utils.Constants;

/**
 * Created by codecadet on 23/11/17.
 */
public class GameScreen extends VScreen {

    private GameCamera gameCamera;
    private Stage gameStage;
    private Player player;
    private Buff platform;
    private ShapeRenderer shapeRenderer;
    private World world;
    private Image bkgImage;
    private Image[] backgroundImages = new Image[4];
    private Label timeLabel;
    private static String timeFormat = "%h:%m:%s";
    private float time;

    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    @Override
    public void show() {

        BuffMessage.loadAllAssets();

        VAssetManager.waitUntilAllAssetsLoaded();

        super.show();

        world = new World(new Vector2(0, Constants.GRAVITY), true);
        world.setContactListener(new WorldCollider());

        shapeRenderer = new ShapeRenderer();

        gameStage = new Stage();
        gameCamera = new GameCamera(gameStage.getCamera());

        setup();

        setupImages();

        gameStage.addActor(player = new Player(8, 10, world, VLib.guiSkin.getRegion("neko_logo")));
        gameStage.addActor(platform = new Buff(11, 10, world, BuffMessage.EMPATHY));

        VSound.playMusic(SoundManager.bkgMusic, 100f);

        timeLabel = VLabel.createLabel(timeFormat, Constants.guiFont, Color.WHITE);
        timeLabel.setPosition(Gdx.graphics.getWidth() - 60, Gdx.graphics.getHeight() - 50);
        timeLabel.setText(timeFormat);
        getGuiStage().addActor(timeLabel);
    }

    private void setupImages() {

        for (int i = 0; i < backgroundImages.length; i++) {

            backgroundImages[i] = VImage.fromFile(Gdx.files.internal("bkg" + (i + 1) + ".png"));
            backgroundImages[i].setScale(Constants.CAMERA_SCALE);
            backgroundImages[i].setY(8.5f);
            gameStage.addActor(backgroundImages[i]);
            if (i > 0) {

                backgroundImages[i].setVisible(false);
            }
        }
    }

    private int id;

    @Override
    public void render(float delta) {

        world.step(1 / 60f, 6, 2);
        debugRenderer.render(world, gameCamera.getCamera().combined);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {

            swapBackgrounds();
        }

        gameStage.act();

        gameCamera.act(player.getX(), player.getY());

        gameStage.draw();

        super.render(delta);

        updateHUD(delta);

        /*
        if (!GameDefs.DEBUG) {

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

            shapeRenderer.rect(player.getBounds().getX(), player.getBounds().getY(), player.getBounds().getWidth(), player.getBounds().getHeight());
            shapeRenderer.rect(platform.getBounds().getX(), platform.getBounds().getY(), platform.getBounds().getWidth(), platform.getBounds().getHeight());

            shapeRenderer.end();
        }
         */
    }

    private void swapBackgrounds() {

        id++;

        if (id >= backgroundImages.length) {

            id = 0;
        }

        for (int i = 0; i < backgroundImages.length; i++) {

            backgroundImages[i].setVisible(id == i);
        }
    }

    private void updateHUD(float delta) {

        time += delta;
        timeLabel.setText(timeFormat.replace("%s", ((int) time % 60) + ""));
        timeLabel.setText(timeLabel.getText().replace("%m", (((int) time / 60) % 60) + ""));
        timeLabel.setText(timeLabel.getText().replace("%h", ((int) time / 3600) + ""));
    }

    @Override
    public void dispose() {

        super.dispose();
        VUtils.disposeItem(gameStage);
    }

    private void setup() {

        BodyFactory.ground(world, gameStage.getCamera());
        BodyFactory.wall(world, -10, 0, 10, Constants.GAME_HEIGHT + 20, 0);
        BodyFactory.wall(world, 40, 0, 10, Constants.GAME_HEIGHT + 20, 0);
        BodyFactory.wall(world, 0, (int) (Constants.GAME_HEIGHT + 20) - 1, 30, 10, 0);
    }
}