package com.orbit.data.nBodyAlgorithms;

import com.orbit.data.GameScreen;
import com.orbit.data.entities.Planet;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Fran on 8/2/2017.
 */
public abstract class NBodyAlgorithm implements Runnable {

    protected Vector<Planet> planets;
    protected GameScreen gameScreen;

    public boolean terminate;

    private double lastTime;

    public NBodyAlgorithm(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.planets = gameScreen.getPlanetArrayList();
    }

    protected double getDelta(){
        double currTime = (double)System.nanoTime()/1000000000.0;
        if(lastTime==0){
            lastTime = currTime;
        }
        double temp = currTime - lastTime;
        lastTime = currTime;
        return temp;
    }
}
