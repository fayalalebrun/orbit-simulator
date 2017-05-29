package com.orbit.data.entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.orbit.data.GameScreen;

import java.util.ArrayList;
import java.util.Vector;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.actor;


/**
 * Created by Fran on 5/22/2017.
 */
public class Planet extends Actor {
    double radius, mass, speed, angle, xPos, yPos, vX, vY;
    double AURadius;
    Texture texture;
    Color color;
    String name;
    ArrayList<Planet> planetArrayList;

    boolean magnify = true;
    float magnificationAmount;

    public Planet(String name, float radius, float mass, float speed, float velocityAngle, Color color, Vector2 position,
                  ArrayList<Planet> planetArrayList) {
        this.planetArrayList = planetArrayList;

        this.name = name;

        this.radius = radius; //km
        this.radius*=1000;//Convert to m
        this.AURadius = mToAU(this.radius);

        this.mass = mass; //Earth masses
        this.mass*=5.9723 * Math.pow(10,24); //Convert to kg

        this.speed = speed; //km/s
        this.speed *= 1000;//convert to m/s

        this.angle = velocityAngle; //degrees
        this.color = color.cpy();

        this.vX = this.speed*Math.cos(this.angle);
        this.vY = this.speed*Math.sin(this.angle);
        // calculate x and y speeds

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
    public void act(float delta) {
        double x = AUToM(getxPos());
        double y = AUToM(getyPos());

        double fX = 0;
        double fY = 0;
        for(Planet p: planetArrayList){
            if(!p.equals(this)){
                double thatX = AUToM(p.getxPos());
                double thatY = AUToM(p.getyPos());

                double distX = thatX-x;
                double fXn = GameScreen.GRAV*((this.mass*p.getMass())/Math.pow(distX,2));
                if(distX<0){
                    fXn*=-1;
                }
                fX+=fXn;

                double distY = thatY-y;
                double fYn = GameScreen.GRAV*((this.mass*p.getMass())/Math.pow(distY, 2));
                if(distY<0){
                    fYn*=-1;
                }
                fY+=fYn;
            }
        }

        x = 0.5*(fX/this.mass)*Math.pow(delta,2)+vX*delta+x;

        y = 0.5*(fY/this.mass)*Math.pow(delta,2)+vY*delta+y;

        xPos = mToAU(x);
        yPos = mToAU(y);

        setPosition((float)(xPos - AURadius), (float)(yPos - AURadius));

        vX = (fX * delta + this.mass * vX)/this.mass;
        vY = (fY * delta + this.mass * vY)/this.mass;
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

    public double AUToM(double au){
        return au*149597870691.0;
    }

    private double getMultiplier(){
        return 0.01*GameScreen.sizeMultVar * magnificationAmount;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setMagnificationAmount(float magnificationAmount) {
        this.magnificationAmount = magnificationAmount;
    }

    public double getMass() {
        return mass;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }
}
