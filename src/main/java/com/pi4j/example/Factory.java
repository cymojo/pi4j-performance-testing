package com.pi4j.example;

//import com.almasb.fxgl.dsl.components.RandomMoveComponent;
//import com.almasb.fxgl.entity.Entity;
//import com.almasb.fxgl.entity.EntityFactory;
//import com.almasb.fxgl.entity.SpawnData;
//import com.almasb.fxgl.entity.Spawns;
//import javafx.geometry.Rectangle2D;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;

import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.pi4j.example.PerformanceTest.EntityType;
import com.almasb.fxgl.dsl.components.RandomMoveComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

//
//import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
//
///**
// * The factory which defines how each entity looks like
// */
//public class Factory implements EntityFactory {
//
//    @Spawns("ball")
//    public Entity newBall(SpawnData data) {
//        Circle circle = new Circle(20, 20, 20, Color.BLUE);
//
//        return entityBuilder()
//                .from(data)
//                .type(PerformanceTest.EntityType.BALL)
//                .viewWithBBox(circle)
//                .collidable()
////                .with(new RandomMoveComponent(new Rectangle2D(0, 0, getAppWidth(), getAppHeight()), 75))
//                .build();
//    }
//}

public class Factory implements EntityFactory {

    @Spawns("ball")
    public Entity newBall(SpawnData data) {
        Circle circle = new Circle(20, 20, 20, Color.BLUE);

        FixtureDef fd = new FixtureDef();
        fd.restitution(0.8f);

        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.DYNAMIC);
        physicsComponent.setFixtureDef(fd);

        return entityBuilder()
                .from(data)
                .type(EntityType.BALL)
                .viewWithBBox(circle)
                .with(physicsComponent)
                .build();
    }

    public Entity makeScreenBounds(double thickness) {
        double w = getAppWidth();
        double h = getAppHeight();
        return entityBuilder()
                .bbox(new HitBox("LEFT",  new Point2D(-thickness, 0), BoundingShape.box(thickness, h)))
                .bbox(new HitBox("RIGHT", new Point2D(w, 0), BoundingShape.box(thickness, h)))
                .bbox(new HitBox("TOP",   new Point2D(0, -thickness), BoundingShape.box(w, thickness)))
                .bbox(new HitBox("BOT",   new Point2D(0, h), BoundingShape.box(w, thickness)))
                .with(new PhysicsComponent())
                .build();
    }

    @Spawns("item")
    public Entity newItem(SpawnData data) {
        Circle circle = new Circle(20, 20, 20, Color.RED);
        circle.setStroke(Color.BROWN);
        circle.setStrokeWidth(2.0);

        return entityBuilder()
                .from(data)
                .type(EntityType.ITEM)
                .viewWithBBox(circle)
                .collidable()
                .with(new RandomMoveComponent(new Rectangle2D(0, 0, getAppWidth(), getAppHeight()), 75))
                .build();
    }
}

