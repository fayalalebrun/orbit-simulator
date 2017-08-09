package com.orbit.data.nBodyAlgorithms;

import com.badlogic.gdx.Gdx;
import com.orbit.data.GameScreen;
import com.orbit.data.dataAnalysis.DataAnalysis;
import com.orbit.data.dataAnalysis.OrbitalPeriodAnalysis;
import com.orbit.data.entities.Planet;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Fran on 8/2/2017.
 */
public abstract class NBodyAlgorithm implements Runnable {

    protected Vector<Planet> planets;
    protected GameScreen gameScreen;

    public boolean terminate;

    private double lastTime, lastDelta;

    DataAnalysis analysis;

    public NBodyAlgorithm(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.planets = gameScreen.getPlanetArrayList();

        this.analysis = new OrbitalPeriodAnalysis(planets);
        DataAnalysis.ANALYSISACTIVE = true; //Whether the analysis should be run
    }

    @Override
    public void run() {
        while (!terminate){
            analysis.run(lastDelta);

            runAlgorithm();
        }
    }

    protected abstract void runAlgorithm();

    protected double getDelta(){
        double currTime = (double)System.nanoTime()/1000000000.0;
        if(lastTime==0){
            lastTime = currTime;
        }
        double temp = currTime - lastTime;
        lastTime = currTime;
        lastDelta = temp;
        return temp;
    }


}
