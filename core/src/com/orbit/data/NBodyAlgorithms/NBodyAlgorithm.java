package com.orbit.data.nBodyAlgorithms;

import com.badlogic.gdx.Gdx;
import com.orbit.data.GameScreen;
import com.orbit.data.entities.Planet;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Fran on 8/2/2017.
 */
public abstract class NBodyAlgorithm implements Runnable {

    //Analysis variables
    private static final String TARGET = "Earth"; //The name of the target body for analysis
    private static final String ORBITING = "Sun"; //The name of the body which the target will orbit
    private static final char COORD = 'y'; // Can be x,y,z, what coordinate will be used as reference to keep track of amount of orbital cycles
    private static final String SAVEPATH = "analysis.txt"; //Name of where results will be saved
    private static final boolean ANALYSISACTIVE = false; //Whether analysis will be performed
    private Planet target, orbiting;
    private boolean analysisStarted;
    private double lastCoord, completionCoord, minVel = Double.MAX_VALUE, maxVel = Double.MIN_VALUE, minDist, maxDist;
    private BigDecimal totalTime;
    private int coordCrossings = -1;


    protected Vector<Planet> planets;
    protected GameScreen gameScreen;

    public boolean terminate;

    private double lastTime, lastDelta;



    public NBodyAlgorithm(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.planets = gameScreen.getPlanetArrayList();

        this.totalTime = new BigDecimal("0");
    }

    @Override
    public void run() {
        while (!terminate){
            totalTime = totalTime.add(new BigDecimal(lastDelta*GameScreen.simSpeed));

            if(ANALYSISACTIVE&&!analysisStarted){
                setupAnalysis();
            }
            runAlgorithm();

            if(analysisStarted){
                runAnalysis();
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

    private void setupAnalysis(){
        synchronized (planets){
            if(planets.size()>0){
                for(Planet p : planets){
                    if(p.getName().equals(TARGET)){
                        target = p;
                    } else if (p.getName().equals(ORBITING)){
                        orbiting = p;
                    }
                }
            }
        }

        if(target!=null&&orbiting!=null){
            System.out.println("Analysis started!");
            analysisStarted=true;
            switch (COORD){
                case 'x':
                    completionCoord = target.xPos;
                    break;
                case 'y':
                    completionCoord = target.yPos;
                    break;
                case 'z':
                    completionCoord = target.zPos;
                    break;
            }
        }
    }

    private void runAnalysis(){

        double currCoord = 0, localLast;

        double currVel = Math.sqrt(Math.pow(target.vX,2)+Math.pow(target.vY,2)+Math.pow(target.vZ,2));

        double currDist = Math.sqrt(Math.pow(orbiting.xPos-target.xPos,2)+Math.pow(orbiting.yPos-target.yPos,2)+Math.pow(orbiting.zPos-target.zPos,2));

        switch (COORD){
            case 'x':
                currCoord = target.xPos;
                break;
            case 'y':
                currCoord = target.yPos;
                break;
            case 'z':
                currCoord = target.zPos;
                break;
        }
        localLast = lastCoord;
        lastCoord = currCoord;

        if((localLast>completionCoord&&currCoord<completionCoord)||(localLast<completionCoord&&currCoord>=completionCoord)){
            coordCrossings++;

            if (coordCrossings%2==0){
                System.out.println("Orbital Cycle Complete");
                Writer writer = Gdx.files.local(SAVEPATH).writer(true);

                try{
                    writer.write("Orbit Cycle "+coordCrossings/2+
                            "\nTotal time: "+totalTime+
                            "\nMin velocity: "+minVel+" Min distance: "+minDist+
                            "\nMax velocity: "+maxVel+" Max distance: "+maxDist+
                            "\n\n"
                    );
                    writer.close();
                } catch (IOException e){
                    e.printStackTrace();
                }

                minVel = Double.MAX_VALUE;
                maxVel = Double.MIN_VALUE;
            }
        }

        if(currVel < minVel){
            minVel = currVel;
            minDist = currDist;
        }

        if(currVel > maxVel){
            maxVel = currVel;
            maxDist = currDist;
        }

    }
}
