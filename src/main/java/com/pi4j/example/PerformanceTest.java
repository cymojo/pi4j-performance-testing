package com.pi4j.example;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.spawn;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.pi4j.example.piMapping.PicadeGameApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Main class of the application
 */
public class PerformanceTest extends PicadeGameApplication {

    private int frames = 0;
    private long lastNow = -1L;

    private static int numberOfItems = 1000;

    /**
     * Types of objects we are going to use in this application.
     */
    public enum EntityType {
        ITEM,
        BALL
    }

    /**
     * Reference to the factory which will defines how all the types must be created.
     */
    private final Factory factory = new Factory();

    /**
     * Player object we are going to use to provide to the factory so it can start a bullet from the player center.
     */
    private Entity player;

    /**
     * Main entry point where the application starts.
     *
     * @param args Start-up arguments
     */
    public static void main(String[] args) {
        if (args != null && args.length > 0) {
            try {
                numberOfItems = Integer.valueOf(args[0]);
            } catch (Exception ex) {
                System.err.println("Incorrect argument given: " + ex.getMessage());
            }
        }
        launch(args);
    }

    /**
     * General settings. For now only the title is set, but a longer list of options is available.
     *
     * @param settings The settings of the game which can be further extended here.
     */
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("JavaFX performance test - " + numberOfItems + " items - ");
        settings.setHeight(600);
        settings.setWidth(900);
        settings.setProfilingEnabled(true);
    }

    /**
     * Initialization of the game by providing the {@link EntityFactory}.
     */
    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(this.factory);

        for (int i = 0; i < numberOfItems; i++) {
            spawn("item");
        }
    }

    @Override
    protected void onUpdate(double tpf) {
        if (this.lastNow == -1L) {
            this.lastNow = System.nanoTime();
            return;
        }

        this.frames++;

        var now = System.nanoTime();
        if (now - this.lastNow >= 3_000_000_000L) {
            System.out.println(new SimpleDateFormat("HH:mm:ss.sss").format(new Date())
                    + " FPS: " + this.frames / 3.0);
            this.lastNow = now;
            this.frames = 0;
        }
    }

    /**
     * Initialization of the physics to detect e.g. collisions.
     */
    @Override
    protected void initPhysics() {
        /*onCollisionBegin(EntityType.BULLET, EntityType.ENEMY, (bullet, enemy) -> {
            bullet.removeFromWorld();
            enemy.removeFromWorld();
        });

        onCollisionBegin(EntityType.ENEMY, EntityType.PLAYER, (enemy, player) -> {
            showMessage("You Died!", () -> {
                getGameController().startNewGame();
            });
        });*/
    }
}


//
///**
// * Main class of the application
// */
//public class PerformanceTest extends GameApplication {
//
//    private int amtOfBalls = 0;
//
//
//    public enum EntityType {
//        BALL
//    }
//
//    private final Factory factory = new Factory();
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    /**
//     * General settings. For now only the title is set, but a longer list of options is available.
//     *
//     * @param settings The settings of the game which can be further extended here.
//     */
//    @Override
//    protected void initSettings(GameSettings settings) {
//        settings.setTitle("Performance Test");
//        settings.setWidth(800);
//        settings.setHeight(600);
//        settings.setProfilingEnabled(true);
//    }
//
//    @Override
//    protected void initGame() {
//        getGameWorld().addEntityFactory(this.factory);
//        getGameWorld().addEntity(factory.makeScreenBounds(10));
//    }
//
//    @Override
//    protected void initInput() {
//        onKey(KeyCode.F, () -> {
//            spawn("ball");
//            amtOfBalls++;
//            System.out.println(amtOfBalls);
//        });
//    }
//}