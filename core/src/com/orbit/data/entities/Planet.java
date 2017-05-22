package com.orbit.data.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Vector;


/**
 * Created by Fran on 5/22/2017.
 */
public class Planet extends Actor {
    float radius, mass, velocity, angle;
    Texture texture;

    public Planet(float radius, float mass, float velocity, float velocityAngle, Color color, Vector2 position) {
        this.radius = radius;
        this.mass = mass;
        this.velocity = velocity;
        this.angle = angle;
        this.setX(position.x);
        this.setY(position.y);
        this.setWidth(radius*2);
        this.setHeight(radius*2);

        Pixmap pixmap = new Pixmap(512, 512, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillCircle(256,256,256);
        texture = new Texture(pixmap);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(),getHeight());
    }
}
