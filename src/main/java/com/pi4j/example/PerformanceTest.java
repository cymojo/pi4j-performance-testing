package com.pi4j.example;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.pi4j.example.piMapping.PicadeGameApplication;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.almasb.fxgl.dsl.FXGL.*;

///**
// * <p>This example fully describes the base usage of Pi4J to create a game</p>
// */
//public class PerformanceTest extends PicadeGameApplication {
//
//    private final Factory factory = new Factory();
//
//    public enum EntityType {
//        BALL
//    }
//
//    private Entity player;
//
//    /**
//     * Main entry point where the application starts.
//     *
//     * @param args Start-up arguments
//     */
//    public static void main(String[] args) {
//        // Launch the FXGL game application
//        launch(args);
//    }
//
//    /**
//     * General game settings. For now only the title is set, but a longer list of options is available.
//     *
//     * @param settings The settings of the game which can be further extended here.
//     */
//    @Override
//    protected void initSettings(GameSettings settings) {
//        settings.setWidth(800);
//        settings.setHeight(600);
//        settings.setTitle("FXGL Performance Test");
//
////        settings.setProfilingEnabled(true);
//    }
//
//
//
//    /**
//     * Input configuration, here you configure all the input events like key presses, mouse clicks, etc.
//     */
//    @Override
//    protected void initInput() {
//
//    }
//
//    /**
//     * Initialization of the game by providing the {@link EntityFactory}.
//     */
//    @Override
//    protected void initGame() {
//        getGameWorld().addEntityFactory(factory);
//
//        spawn("ball");
//    }
//
//    @Override
//    protected void initPhysics() {
//        getPhysicsWorld().setGravity(0, 9.81);
//
//        getConsole().println("Init physics done");
//    }
//}


/**
 * Main class of the application
 */
public class PerformanceTest extends GameApplication {

    private int amtOfBalls = 0;


    public enum EntityType {
        BALL
    }

    private final Factory factory = new Factory();


    public static void main(String[] args) {
        launch(args);
    }

    /**
     * General settings. For now only the title is set, but a longer list of options is available.
     *
     * @param settings The settings of the game which can be further extended here.
     */
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Performance Test");
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setProfilingEnabled(true);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(this.factory);
        getGameWorld().addEntity(factory.makeScreenBounds(10));
    }

    @Override
    protected void initInput() {
        onKey(KeyCode.F, () -> {
            spawn("ball");
            amtOfBalls++;
            System.out.println(amtOfBalls);
        });
    }
}