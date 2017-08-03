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

    public NBodyAlgorithm(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.planets = gameScreen.getPlanetArrayList();
    }
}
