package org.academiadecodigo.hackathon.apologies.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import org.academiadecodigo.hackathon.apologies.game.objects.Platform.Ground;
import org.academiadecodigo.hackathon.apologies.utils.Constants;
import org.academiadecodigo.hackathon.apologies.game.objects.Platform.PlatformLvl1;
import org.academiadecodigo.hackathon.apologies.game.objects.Platform.PlatformLvl2;
import org.academiadecodigo.hackathon.apologies.game.objects.Platform.PlatformLvl3;

/**
 * Created by codecadet on 23/11/17.
 */
public class GameScreen extends VScreen {

    private GameCamera gameCamera;
    private Stage gameStage;
    private Player player;
    private Buff buff;
    private Image[] backgroundImages = new Image[4];
    private Label timeLabel;
    private static String timeFormat = "%h:%m:%s";
    private float time;

    private ShapeRenderer shapeRenderer;
    private World world;
    private Image bkgImage;
    private Ground ground;
    private Texture platformTextureLvl1;
    private Texture platformTextureLvl2;
    private Texture platformTextureLvl3;
    private Texture platformTextureLvl4;
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

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
        timeLabel.setPosition(Gdx.graphics.getWidth() - 60, Gdx.graphics.getHeight() - 50);
        timeLabel.setText(timeFormat);
        getGuiStage().addActor(timeLabel);

        PlatformLvl1[] platforms1 = new PlatformLvl1[]{
                new PlatformLvl1(25, 13, world, new TextureRegion(platformTextureLvl1)),
                new PlatformLvl1(19, 17, world, new TextureRegion(platformTextureLvl1)),
                new PlatformLvl1(11, 19, world, new TextureRegion(platformTextureLvl1)),
                new PlatformLvl1(0, 21, world, new TextureRegion(platformTextureLvl1)),
                new PlatformLvl1(9, 25, world, new TextureRegion(platformTextureLvl1)),
                new PlatformLvl1(0, 29, world, new TextureRegion(platformTextureLvl1))
        };

        PlatformLvl2[] platforms2 = new PlatformLvl2[]{
                new PlatformLvl2(0, 34, world, new TextureRegion(platformTextureLvl2)),
                new PlatformLvl2(9, 37, world, new TextureRegion(platformTextureLvl2)),
                new PlatformLvl2(0, 40, world, new TextureRegion(platformTextureLvl2)),
                new PlatformLvl2(16, 35, world, new TextureRegion(platformTextureLvl2)),
                new PlatformLvl2(22, 39, world, new TextureRegion(platformTextureLvl2)),
                new PlatformLvl2(1, 51, world, new TextureRegion(platformTextureLvl2)),
                new PlatformLvl2(26, 43, world, new TextureRegion(platformTextureLvl2))
        };

        PlatformLvl3[] platforms3 = new PlatformLvl3[]{
                new PlatformLvl3(29, 47, world, new TextureRegion(platformTextureLvl3)),
                new PlatformLvl3(19, 51, world, new TextureRegion(platformTextureLvl3)),
                new PlatformLvl3(15, 54, world, new TextureRegion(platformTextureLvl3)),
                new PlatformLvl3(24, 57, world, new TextureRegion(platformTextureLvl3)),
                new PlatformLvl3(16, 60, world, new TextureRegion(platformTextureLvl3)),
                new PlatformLvl3(5, 63, world, new TextureRegion(platformTextureLvl3)),
        };

        PlatformLvl1[] platforms4 = new PlatformLvl1[]{
                new PlatformLvl1(28,74,world,new TextureRegion(platformTextureLvl4)),
                new PlatformLvl1(20,67,world,new TextureRegion(platformTextureLvl4)),
                new PlatformLvl1(8,67,world,new TextureRegion(platformTextureLvl4))
        };

        gameStage.addActor(player = new Player(5, 10, world, new TextureRegion(new Texture("player_lvl1_R.png"))));

        for (PlatformLvl1 p1 : platforms1) {
            gameStage.addActor(p1);
        }

        for (PlatformLvl2 p2 : platforms2) {
            gameStage.addActor(p2);
        }
        for (PlatformLvl3 p3 : platforms3) {
            gameStage.addActor(p3);
        }
        for (PlatformLvl1 p4 : platforms4) {
            gameStage.addActor(p4);
        }
        gameStage.addActor(buff = new Buff(1,42,world,BuffMessage.GRATITUDE,new TextureRegion(new Texture("orb_red.png")),player));
        gameStage.addActor(buff = new Buff(1,52,world,BuffMessage.EMPATHY,new TextureRegion(new Texture("orb_green.png")),player));
        gameStage.addActor(buff = new Buff(28,76,world,BuffMessage.SELF_WORTH,new TextureRegion(new Texture("orb_blue.png")),player));

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

        /*
        if (!GameDefs.DEBUG) {

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

            shapeRenderer.rect(player.getBounds().getX(), player.getBounds().getY(), player.getBounds().getWidth(), player.getBounds().getHeight());
            shapeRenderer.rect(platform.getBounds().getX(), platform.getBounds().getY(), platform.getBounds().getWidth(), platform.getBounds().getHeight());

            shapeRenderer.end();
        }
         */
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