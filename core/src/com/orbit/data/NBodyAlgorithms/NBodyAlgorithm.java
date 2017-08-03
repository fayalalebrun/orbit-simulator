package com.orbit.data.nBodyAlgorithms;

import com.orbit.data.GameScreen;
import com.orbit.data.entities.Planet;

import java.util.ArrayList;

/**
 * Created by Fran on 8/2/2017.
 */
public abstract class NBodyAlgorithm implements Runnable {

    private ArrayList<Planet> planets;
    private GameScreen gameScreen;

    private double lastTime;

    public NBodyAlgorithm(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.planets = gameScreen.getPlanetArrayList();
    }

    public double getDelta(){
        double currTime = System.nanoTime()/1000000000;
        if(lastTime==0){
            lastTime = currTime;
        }
        return currTime - lastTime;
    }
}
