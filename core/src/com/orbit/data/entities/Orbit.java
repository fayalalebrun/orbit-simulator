package com.orbit.data.entities;

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

    public Orbit(Planet planet){
        this.planet = planet;
        this.points = new ArrayList<Vector2>();
    }

    public void act(float delta) {
        timePassed += delta;

        if(timePassed>1f) {
            points.add(new Vector2((float) planet.xPos, (float) planet.yPos));
            timePassed = 0;
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
}
