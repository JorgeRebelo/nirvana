package org.academiadecodigo.hackathon.apologies.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mail.vandrake.control.VUtils;
import com.mail.vandrake.scene2d.VImage;
import com.mail.vandrake.scene2d.VScreen;
import javafx.application.Platform;
import org.academiadecodigo.hackathon.apologies.Constants;
import org.academiadecodigo.hackathon.apologies.game.objects.*;

/**
 * Created by codecadet on 23/11/17.
 */
public class GameScreen extends VScreen {

    private GameCamera gameCamera;
    private Stage gameStage;
    private Player player;
    private PlatformLvl1 platform;
    private ShapeRenderer shapeRenderer;
    private World world;
    private Image bkgImage;
    private Texture platformTextureLvl1;
    private Texture platformTextureLvl2;
    private Texture platformTextureLvl3;
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    @Override
    public void show() {

        super.show();
        platformTextureLvl1 = new Texture("platform_black.png");
        platformTextureLvl2 = new Texture("platform_red.png");
        platformTextureLvl3 = new Texture("platform_green.png");
        world = new World(new Vector2(0, Constants.GRAVITY), true);

        shapeRenderer = new ShapeRenderer();

        gameStage = new Stage();
        gameCamera = new GameCamera(gameStage.getCamera());

        setup();

        gameStage.addActor(bkgImage = VImage.fromFile(Gdx.files.internal("background1.jpg")));
        bkgImage.setScale(Constants.CAMERA_SCALE);
        bkgImage.setY(8.5f);

        PlatformLvl1[] platforms1 = new PlatformLvl1[]{
                new PlatformLvl1(25, 12, world, new TextureRegion(platformTextureLvl1)),
                new PlatformLvl1(19, 15, world, new TextureRegion(platformTextureLvl1)),
                new PlatformLvl1(11, 17, world, new TextureRegion(platformTextureLvl1)),
                new PlatformLvl1(0, 19, world, new TextureRegion(platformTextureLvl1)),
                new PlatformLvl1(9, 23, world, new TextureRegion(platformTextureLvl1)),
                new PlatformLvl1(0, 27, world, new TextureRegion(platformTextureLvl1))
        };

        PlatformLvl2[] platforms2 = new PlatformLvl2[]{
                new PlatformLvl2(0,31,world,new TextureRegion(platformTextureLvl2)),
                new PlatformLvl2(9,35,world,new TextureRegion(platformTextureLvl2)),
                new PlatformLvl2(0,39,world,new TextureRegion(platformTextureLvl2)),
                new PlatformLvl2(16,35,world,new TextureRegion(platformTextureLvl2)),
                new PlatformLvl2(22,39,world,new TextureRegion(platformTextureLvl2)),
                new PlatformLvl2(26,43,world,new TextureRegion(platformTextureLvl2))
        };

        PlatformLvl3[] platforms3 = new PlatformLvl3[]{
                new PlatformLvl3(29,47,world,new TextureRegion(platformTextureLvl3)),
                new PlatformLvl3(19,51,world,new TextureRegion(platformTextureLvl3)),
                new PlatformLvl3(15,54,world,new TextureRegion(platformTextureLvl3)),
                new PlatformLvl3(24,57,world,new TextureRegion(platformTextureLvl3)),
                new PlatformLvl3(16,60,world,new TextureRegion(platformTextureLvl3)),
                new PlatformLvl3(5,63,world,new TextureRegion(platformTextureLvl3)),
        };

        gameStage.addActor(player = new Player(8, 10, world, new TextureRegion(new Texture("player_red_L.png"))));

        for (PlatformLvl1 p1 : platforms1) {
            gameStage.addActor(p1);
        }
        for (PlatformLvl2 p2 : platforms2) {
            gameStage.addActor(p2);
        }
        for (PlatformLvl3 p3 : platforms3) {
            gameStage.addActor(p3);
        }
    }

    @Override
    public void render(float delta) {

        world.step(1 / 60f, 6, 2);
        debugRenderer.render(world, gameCamera.getCamera().combined);

        gameStage.act();

        gameCamera.act(player.getX(), player.getY());

        gameStage.draw();

        super.render(delta);

        /*
        if (!GameDefs.DEBUG) {

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

            shapeRenderer.rect(player.getBounds().getX(), player.getBounds().getY(), player.getBounds().getWidth(), player.getBounds().getHeight());
            shapeRenderer.rect(platform.getBounds().getX(), platform.getBounds().getY(), platform.getBounds().getWidth(), platform.getBounds().getHeight());

            shapeRenderer.end();
        }
         */
    }

    @Override
    public void dispose() {

        super.dispose();
        VUtils.disposeItem(gameStage);
    }

    private void setup() {

        BodyFactory.ground(world, gameStage.getCamera());
        BodyFactory.wall(world, -10, 0, 10, Constants.GAME_HEIGHT + 20, 0);
        BodyFactory.wall(world, 40.5f, 0, 10, Constants.GAME_HEIGHT + 20, 0);
        BodyFactory.wall(world, 0, (int) (Constants.GAME_HEIGHT + 20) - 1, 20, 10, 0);
        //BodyFactory.polygonShape(world, 10, 10, 1, 1);
    }
}