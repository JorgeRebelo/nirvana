package org.academiadecodigo.hackathon.apologies.game.screens;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mail.vandrake.VLib;
import com.mail.vandrake.control.VUtils;
import com.mail.vandrake.scene2d.VScreen;
import org.academiadecodigo.hackathon.apologies.Constants;
import org.academiadecodigo.hackathon.apologies.game.objects.*;

/**
 * Created by codecadet on 23/11/17.
 */
public class GameScreen extends VScreen {

    private GameCamera gameCamera;
    private Stage gameStage;
    private Player player;
    private Platform platform;
    private ShapeRenderer shapeRenderer;
    private World world;

    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    @Override
    public void show() {

        super.show();

        world = new World(new Vector2(0, Constants.GRAVITY), true);

        shapeRenderer = new ShapeRenderer();

        gameStage = new Stage();
        gameCamera = new GameCamera(gameStage.getCamera());

        setup();

        //getGuiStage().addActor(VImage.fromFile(Gdx.files.internal("background1.jpg")));
        gameStage.addActor(player = new Player(8, 10, world, VLib.guiSkin.getRegion("neko_logo")));
        gameStage.addActor(platform = new Platform(11, 10, world, VLib.guiSkin.getRegion("window")));
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

        //BodyFactory.polygonShape(world, 10, 10, 1, 1);
    }
}