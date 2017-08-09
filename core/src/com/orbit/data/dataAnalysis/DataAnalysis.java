package com.orbit.data.dataAnalysis;

import com.orbit.data.GameScreen;
import com.orbit.data.entities.Planet;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Fran on 8/8/2017.
 */
public abstract class DataAnalysis {

    protected Vector<Planet> planetList;
    protected GameScreen gameScreen;
    public static boolean ANALYSISACTIVE;

    public DataAnalysis(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.planetList = gameScreen.getPlanetArrayList();
    }

    public abstract void run(double delta);
}
