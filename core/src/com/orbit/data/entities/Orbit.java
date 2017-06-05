package com.orbit.data.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kotcrab.vis.ui.util.adapter.ArrayListAdapter;

import java.util.ArrayList;

/**
 * Created by Fran on 6/3/2017.
 */
public class Orbit{
    private Planet planet;
    private ArrayList<Vector2> points;
    private float timePassed;
    OrbitManager orbitManager;

    public Orbit(Planet planet, OrbitManager orbitManager){
        this.planet = planet;
        this.orbitManager = orbitManager;
        this.points = new ArrayList<Vector2>();
    }

    public void act(float delta) {
        timePassed += delta;

        if(timePassed>orbitManager.getInterval()) {
            points.add(new Vector2((float) planet.xPos, (float) planet.yPos));
            timePassed = 0;
        }

        if(points.size()>orbitManager.getMaxPoints()){
            points.remove(0);
        }
    }

    public void clear(){
        this.points.clear();
    }

    public boolean isActive(){
        return planet.getStage()!=null;
    }

    public ArrayList<Vector2> getPoints() {
        return points;
    }

    public Color getColor(){
        return planet.getCurrColor().cpy();
    }
}
