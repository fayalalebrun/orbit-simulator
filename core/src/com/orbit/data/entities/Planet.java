package com.orbit.data.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Vector;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.actor;


/**
 * Created by Fran on 5/22/2017.
 */
public class Planet extends Actor {
    double radius, mass, speed, angle, xPos, yPos;
    Texture texture;
    Color color;

    public Planet(float radius, float mass, float speed, float velocityAngle, Color color, Vector2 position) {
        this.radius = radius; //km
        this.radius*=1000;//Convert to m

        this.mass = mass; //Earth masses
        this.mass*=5.9723 * Math.pow(10,24); //Convert to kg

        this.speed = speed; //km/s
        this.speed *= 1000;//convert to m/s

        this.angle = velocityAngle; //degrees
        this.color = color.cpy();

        xPos = position.x - mToAU(radius);
        yPos = position.y - mToAU(radius);
        setPosition((float)xPos, (float)yPos);

        double diameter = mToAU(radius) * 2;

        this.setWidth((float)diameter);
        this.setHeight((float)diameter);

        Pixmap pixmap = new Pixmap(512, 512, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE.cpy());
        pixmap.fillCircle(256,256,256);
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(color.cpy());
        batch.draw(texture, (float)xPos, (float)yPos, getWidth(),getHeight());
    }

    public double mToAU(double meters){
        return meters * 0.0000000000067;
    }
}
