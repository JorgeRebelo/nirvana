package org.academiadecodigo.hackathon.apologies.game.objects.Platform;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by codecadet on 24/11/17.
 */
public class PlatformFactory {

    public static void addPlatforms(Stage gameStage, World world, Texture platformTextureLvl1, Texture platformTextureLvl2, Texture platformTextureLvl3, Texture platformTextureLvl4) {


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
    }
}
