package com.orbit.data.entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

    public Planet(String name, double radius, double mass, double speed, double velocityAngle, Color color, double x, double y,
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

        this.vX = this.speed*Math.cos(Math.toRadians(this.angle));
        this.vY = this.speed*Math.sin(Math.toRadians(this.angle));
        // calculate x and y speeds

        xPos = x;
        yPos = y;
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
        delta *= GameScreen.simSpeed;

        double x = AUToM(getxPos());
        double y = AUToM(getyPos());

        double fX = 0;
        double fY = 0;
        for(Planet p: planetArrayList){
            if(!p.equalsP(this)){
                double thatX = AUToM(p.getxPos());
                double thatY = AUToM(p.getyPos());

                double dist = Math.sqrt(Math.pow(thatX-x, 2)+Math.pow(thatY-y,2));
                double ang = Math.atan2(thatY-y, thatX-x);

                double f = GameScreen.GRAV*((this.getMass()*p.getMass())/Math.pow(dist,2));

                fX+=f*Math.cos(ang);
                fY+=f*Math.sin(ang);
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

    public boolean equalsP(Planet p){
        if(xPos==p.getxPos()&&yPos==p.getyPos()){
            return true;
        }
        return  false;
    }

    public double getAngle(){
        double angle = Math.toDegrees(Math.atan2(vY, vX));

        if(angle<0){
            angle+=360;
        }

        return angle;
    }

    public double getSpeed(){
        return (Math.sqrt(Math.pow(vX, 2)+Math.pow(vY, 2)))/1000;
    }

    public double getOrigRadius(){
        return this.radius/1000;
    }

    public double getOrigMass(){
        return this.mass/= (5.9723 * Math.pow(10,24));
    }

    public Color getCurrColor(){
        return this.color;
    }

    public void zoomCamera(OrthographicCamera camera){
        camera.zoom = (float)(AURadius*2/camera.viewportHeight);
    }

    public void centerCamera(OrthographicCamera camera){
        camera.translate(-1*camera.position.x,-1*camera.position.y);
        camera.translate((float)xPos, (float)yPos);
    }

}
