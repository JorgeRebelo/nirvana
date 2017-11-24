package org.academiadecodigo.hackathon.apologies.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mail.vandrake.control.VAssetManager;
import com.mail.vandrake.control.VSound;
import com.mail.vandrake.control.VUtils;
import com.mail.vandrake.draw.Draw;
import com.mail.vandrake.scene2d.Toast;
import com.mail.vandrake.scene2d.VImage;
import com.mail.vandrake.scene2d.VLabel;
import com.mail.vandrake.scene2d.VScreen;
import org.academiadecodigo.hackathon.apologies.AllApologies;
import org.academiadecodigo.hackathon.apologies.SoundManager;
import org.academiadecodigo.hackathon.apologies.game.objects.*;
import org.academiadecodigo.hackathon.apologies.game.objects.Platform.*;
import org.academiadecodigo.hackathon.apologies.servercomunication.ServerParser;
import org.academiadecodigo.hackathon.apologies.utils.Constants;

import java.util.LinkedHashMap;

/**
 * Created by codecadet on 23/11/17.
 */
public class GameScreen extends VScreen {

    private GameCamera gameCamera;
    private Stage gameStage;
    private Player player;
    private Boss boss;
    private Buff buff;
    private Image[] backgroundImages = new Image[4];
    private Label timeLabel;
    private static String timeFormat = "%h:%m:%s";
    private float time;

    private ShapeRenderer shapeRenderer;
    private World world;
    private PlatformLvl1 platform;
    private Ground ground;
    private Texture platformTextureLvl1;
    private Texture platformTextureLvl2;
    private Texture platformTextureLvl3;
    private Texture platformTextureLvl4;
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    private String userName;

    public GameScreen(String userName) {

        this.userName = userName;
    }

    @Override
    public void show() {

        BuffMessage.loadAllAssets();

        VAssetManager.waitUntilAllAssetsLoaded();

        super.show();
        platformTextureLvl1 = new Texture("platform_black.png");
        platformTextureLvl2 = new Texture("platform_red.png");
        platformTextureLvl3 = new Texture("platform_green.png");
        platformTextureLvl4 = new Texture("platform_blue.png");
        world = new World(new Vector2(0, Constants.GRAVITY), true);
        world.setContactListener(new WorldCollider());

        shapeRenderer = new ShapeRenderer();

        gameStage = new Stage();
        gameCamera = new GameCamera(gameStage.getCamera());

        setup();

        setupImages();

        gameStage.addActor(ground = new Ground(15.5f, 9f, world, new TextureRegion(new Texture("ground.png"))));

        VSound.playMusic(SoundManager.bkgMusic, 100f);

        timeLabel = VLabel.createLabel(timeFormat, Constants.guiFont, Color.WHITE);
        timeLabel.setPosition(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50);
        timeLabel.setText(timeFormat);
        getGuiStage().addActor(timeLabel);

        PlatformFactory.addPlatforms(gameStage, world, platformTextureLvl1, platformTextureLvl2, platformTextureLvl3, platformTextureLvl4);

        gameStage.addActor(player = new Player(5, 10, world, userName,this));

        gameStage.addActor(buff = new Buff(1, 42, world, BuffMessage.GRATITUDE, new TextureRegion(new Texture("orb_red.png")), player));
        gameStage.addActor(buff = new Buff(1, 52, world, BuffMessage.EMPATHY, new TextureRegion(new Texture("orb_green.png")), player));
        gameStage.addActor(buff = new Buff(8, 69, world, BuffMessage.SELF_WORTH, new TextureRegion(new Texture("orb_blue.png")), player));
        gameStage.addActor(boss = new Boss(19, 72, world));
        gameStage.addActor(platform = new PlatformLvl1(19,68,world,new TextureRegion(new Texture("platform_blue.png"))));

        boss.setVisible(false);
        platform.setVisible(false);
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

        gameStage.act();

        gameCamera.act(player.getX(), player.getY());

        gameStage.draw();

        super.render(delta);

        updateHUD(delta);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {

            //XToast.spawnToast(25, 10, true, "Hello World");

            XToast.spawnToast(player.getX(), player.getY(), BuffMessage.EMPATHY.getMessage(), VImage.fromFile(Gdx.files.internal("gugu1.png")), VImage.fromFile(Gdx.files.internal("gugu.png")));
        }

        /*
        if (!GameDefs.DEBUG) {

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

            shapeRenderer.rect(player.getBounds().getX(), player.getBounds().getY(), player.getBounds().getWidth(), player.getBounds().getHeight());
            shapeRenderer.rect(platform.getBounds().getX(), platform.getBounds().getY(), platform.getBounds().getWidth(), platform.getBounds().getHeight());

            shapeRenderer.end();
        }
         */
    }

    public void loadHighscore() {

        AllApologies.getInstance().setScreen(new HighScoreScreen(ServerParser.topPlayers(), userName, (int) time));
    }

    public void swapBackgrounds() {

        id++;

        if (id >= backgroundImages.length) {

            id = 0;
        }

        player.upLevel();
        for (int i = 0; i < backgroundImages.length; i++) {

            backgroundImages[i].setVisible(id == i);
        }
    }
    public void showBoss() {
        boss.setVisible(true);
        platform.setVisible(true);
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