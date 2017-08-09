package com.orbit.data.dataAnalysis;

import com.orbit.data.entities.Planet;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Fran on 8/8/2017.
 */
public abstract class DataAnalysis {

    protected Vector<Planet> planetList;
    public static boolean ANALYSISACTIVE;

    public DataAnalysis(Vector<Planet> planetList) {
        this.planetList = planetList;
    }

    public abstract void run(double delta);
}
