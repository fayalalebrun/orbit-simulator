package com.orbit.data.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.orbit.data.GameScreen;

import java.util.Vector;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.actor;


/**
 * Created by Fran on 5/22/2017.
 */
public class Planet extends Actor {
    double radius, mass, speed, angle, xPos, yPos;
    double AURadius;
    Texture texture;
    Color color;

    boolean magnify = true;

    public Planet(float radius, float mass, float speed, float velocityAngle, Color color, Vector2 position) {
        this.radius = radius; //km
        this.radius*=1000;//Convert to m
        this.AURadius = mToAU(this.radius);

        this.mass = mass; //Earth masses
        this.mass*=5.9723 * Math.pow(10,24); //Convert to kg

        this.speed = speed; //km/s
        this.speed *= 1000;//convert to m/s

        this.angle = velocityAngle; //degrees
        this.color = color.cpy();

        xPos = position.x;
        yPos = position.y;
        setPosition((float)(xPos - AURadius), (float)(yPos - AURadius));

        double diameter = AURadius * 2;

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


        if(magnify){
            batch.draw(texture, (float)(xPos-AURadius-getMultiplier()*0.5), (float)(yPos - AURadius - getMultiplier()*0.5),
                    getWidth() +(float)getMultiplier() ,getHeight()+(float)getMultiplier());
            return;
        }


        batch.draw(texture, (float)(xPos-AURadius), (float)(yPos - AURadius), getWidth(),getHeight());
    }

    public double mToAU(double meters){
        return meters/149597870691.0;
    }

    private double getMultiplier(){
        return AURadius* GameScreen.sizeMultVar;
    }

}
