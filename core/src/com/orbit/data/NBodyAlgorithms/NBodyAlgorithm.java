package com.orbit.data.nBodyAlgorithms;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.orbit.data.GameScreen;
import com.orbit.data.dataAnalysis.DataAnalysis;
import com.orbit.data.dataAnalysis.IntervalPositionAnalysis;
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

    private static final boolean PRINT_CALC_PER_SEC = true;

    protected Vector<Planet> planets;
    protected GameScreen gameScreen;

    public boolean terminate;

    private double lastTime, lastDelta;

    DataAnalysis analysis;

    private int calcSec;
    private double timeSinceCalcSec;

    public NBodyAlgorithm(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.planets = gameScreen.getPlanetArrayList();

        this.analysis = new IntervalPositionAnalysis(gameScreen);
        DataAnalysis.ANALYSISACTIVE = false; //Whether the analysis should be run
    }

    @Override
    public void run() {
        while (!terminate){
            analysis.run(lastDelta* GameScreen.simSpeed);

            runAlgorithm();

            if(PRINT_CALC_PER_SEC) {
                calcSec++;
                timeSinceCalcSec += lastDelta;
                if (timeSinceCalcSec >= 1) {
                    timeSinceCalcSec = 0;
                    System.out.println(calcSec);
                    calcSec = 0;
                }
            }

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

    protected double cb(double x){
        return x*x*x;
    }

    protected double sq(double x){
        return x*x;
    }

}
