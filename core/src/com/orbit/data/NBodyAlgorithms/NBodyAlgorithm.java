package com.orbit.data.NBodyAlgorithms;

import com.orbit.data.entities.Planet;

import java.util.ArrayList;

/**
 * Created by Fran on 8/2/2017.
 */
public abstract class NBodyAlgorithm implements Runnable {

    private ArrayList<Planet> planets;

    public NBodyAlgorithm(ArrayList<Planet> planets) {
        this.planets = planets;
    }
}
