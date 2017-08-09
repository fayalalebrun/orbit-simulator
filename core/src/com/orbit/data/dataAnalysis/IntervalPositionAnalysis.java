package com.orbit.data.dataAnalysis;

import com.badlogic.gdx.Gdx;
import com.orbit.data.GameScreen;
import com.orbit.data.entities.Planet;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Vector;

/**
 * Created by Fran on 8/8/2017.
 */
public class IntervalPositionAnalysis extends DataAnalysis{
    private static final double TIMESTEP = 10000000; //The timestep that will be used while running the simulation
    private static final BigDecimal INTERVAL = new BigDecimal(100000000); //How often the positions of each planet will be saved in simulation seconds
    private static final BigDecimal DURATION = new BigDecimal(100000000); //How long the analysis will last in simulation seconds
    private static final String CHECK = "Sun"; //When this object is found in the simulation, the analysis will start
    private static final String PATH = "Analysis.txt"; //Location where analysis file will be saved

    private BigDecimal totalTime;
    private BigDecimal runningTimer;

    private boolean analysisStarted;
    private boolean analysisComplete;

    private int completedIntervals;

    public IntervalPositionAnalysis(GameScreen gameScreen) {
        super(gameScreen);

        totalTime = new BigDecimal(0);
        runningTimer = new BigDecimal(0);
    }

    @Override
    public void run(double delta) {
        if(!analysisStarted&&ANALYSISACTIVE&&!analysisComplete){
            setupAnalysis();
        } else if(analysisStarted){
            runAnalysis(delta);
        }
    }

    private void setupAnalysis(){
        synchronized (planetList) {
            for (Planet p : planetList) {
                if (p.getName().equals(CHECK)) {
                    analysisStarted = true;
                    GameScreen.simSpeed = TIMESTEP;
                    System.out.println("Starting analysis");
                }
            }
        }
    }

    private void runAnalysis(double delta){
        totalTime = totalTime.add(new BigDecimal(delta));
        runningTimer = runningTimer.add(new BigDecimal(delta));

        if(runningTimer.compareTo(INTERVAL)==1){
            completedIntervals++;
            System.out.println("Interval completed");
            runningTimer = new BigDecimal(0);
            Writer writer = Gdx.files.local(PATH).writer(true);

            try {
                writer.write("Interval Completion "+completedIntervals+"\n");
                writer.write("Total Elapsed Time: " + totalTime + "\n");
                for(Planet p : this.planetList){
                    writer.write("Name: "+p.getName()+" x: "+p.xPos+" y: "+p.yPos+" z: "+p.zPos+"\n");
                }
                writer.write("\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(totalTime.compareTo(DURATION)==1){
            analysisStarted=false;
            analysisComplete=true;
            System.out.println("Analysis complete!");
        }

    }
}
