package com.orbit.data.dataAnalysis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.orbit.data.GameScreen;
import com.orbit.data.entities.Planet;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Vector;

/**
 * Created by Fran on 8/8/2017.
 */
public class OrbitalPeriodAnalysis extends DataAnalysis {

    private static final String TARGET = "Venus"; //The name of the target body for analysis
    private static final String ORBITING = "Sun"; //The name of the body which the target will orbit
    private static final char COORD = 'y'; // Can be x,y,z, what coordinate will be used as reference to keep track of amount of orbital cycles
    private static final String SAVEPATH = "VenusPeriod.txt"; //Name of where results will be saved
    private Planet target, orbiting;
    private boolean analysisStarted;
    private double lastCoord, completionCoord, minVel = Double.MAX_VALUE, maxVel = Double.MIN_VALUE, minDist, maxDist;
    private double[] minOrbitingCoord = new double[3];
    private double[] maxOrbitingCoord = new double[3];
    private double[] minTargetCoord = new double[3];
    private double[] maxTargetCoord = new double[3];
    private BigDecimal totalTime;
    private int coordCrossings = 0;

    public OrbitalPeriodAnalysis(GameScreen gameScreen) {
        super(gameScreen);

        this.totalTime = new BigDecimal(0);
    }

    @Override
    public void run(double delta) {

        if(ANALYSISACTIVE&&!analysisStarted) {
            setupAnalysis();
        } else if(analysisStarted){
            runAnalysis(delta);
        }
    }

    private void setupAnalysis(){
        synchronized (planetList){
            if(planetList.size()>0){
                for(Planet p : planetList){
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
            lastCoord = completionCoord;
        }
    }

    private void runAnalysis(double delta){
        totalTime = totalTime.add(new BigDecimal(delta));
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

        if((localLast>completionCoord&&currCoord<completionCoord)||(localLast<completionCoord&&currCoord>completionCoord)){
            coordCrossings++;

            if (coordCrossings%2==0){
                System.out.println("Orbital Cycle Complete");
                Writer writer = Gdx.files.local(SAVEPATH).writer(true);

                try{
                    writer.write("Orbit Cycle "+coordCrossings/2+
                            "\nTotal time: "+totalTime+
                            "\nMin velocity: "+minVel+" Min distance: "+minDist+" TargetX: "+minTargetCoord[0]+" TargetY: "+minTargetCoord[1]+" TargetZ: "+minTargetCoord[2]+" OrbitingX: "+minOrbitingCoord[0]+" OrbitingY: "+minOrbitingCoord[1]+" OrbitingZ: "+minOrbitingCoord[2]+
                            "\nMax velocity: "+maxVel+" Max distance: "+maxDist+" TargetX: "+maxTargetCoord[0]+" TargetY: "+maxTargetCoord[1]+" TargetZ: "+maxTargetCoord[2]+" OrbitingX: "+maxOrbitingCoord[0]+" OrbitingY: "+maxOrbitingCoord[1]+" OrbitingZ: "+maxOrbitingCoord[2]+
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

            minOrbitingCoord[0] = orbiting.xPos; minOrbitingCoord[1] = orbiting.yPos; minOrbitingCoord[2] = orbiting.zPos;
            minTargetCoord[0] = target.xPos; minTargetCoord[1] = target.yPos; minTargetCoord[2] = target.zPos;
        }

        if(currVel > maxVel){
            maxVel = currVel;
            maxDist = currDist;

            maxOrbitingCoord[0] = orbiting.xPos; maxOrbitingCoord[1] = orbiting.yPos; maxOrbitingCoord[2] = orbiting.zPos;
            maxTargetCoord[0] = target.xPos; maxTargetCoord[1] = target.yPos; maxTargetCoord[2] = target.zPos;
        }

    }
}
