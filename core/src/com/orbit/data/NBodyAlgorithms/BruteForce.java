package com.orbit.data.nBodyAlgorithms;

import com.orbit.data.GameScreen;
import com.orbit.data.entities.Planet;
import com.orbit.data.helpers.Units;

import java.util.ArrayList;

/**
 * Created by Fran on 8/3/2017.
 */
public class BruteForce extends NBodyAlgorithm{

    private double time;

    public BruteForce(GameScreen gameScreen) {
        super(gameScreen);
    }

    @Override
    public void run() {
        while(!terminate){

            synchronized (planets) {
                double delta = getDelta();
                delta *= GameScreen.simSpeed;
                    for (Planet p1 : this.planets) {

                        double x = Units.AUToM(p1.getxPos());
                        double y = Units.AUToM(p1.getyPos());

                        double fX = 0;
                        double fY = 0;
                        for (Planet p : planets) {
                            if (!p.equalsP(p1)) {
                                double thatX = Units.AUToM(p.getxPos());
                                double thatY = Units.AUToM(p.getyPos());

                                double dist = Math.sqrt(Math.pow(thatX - x, 2) + Math.pow(thatY - y, 2));
                                double ang = Math.atan2(thatY - y, thatX - x);

                                double f = Units.GRAV * ((p1.getMass() * p.getMass()) / Math.pow(dist, 2));

                                fX += f * Math.cos(ang);
                                fY += f * Math.sin(ang);
                            }
                        }

                        x = 0.5 * (fX / p1.getMass()) * Math.pow(delta, 2) + p1.vX * delta + x;

                        y = 0.5 * (fY / p1.getMass()) * Math.pow(delta, 2) + p1.vY * delta + y;

                        synchronized (p1) {
                            p1.xPos = Units.mToAU(x);
                            p1.yPos = Units.mToAU(y);
                        }

                        p1.vX = (fX * delta + p1.getMass() * p1.vX) / p1.getMass();
                        p1.vY = (fY * delta + p1.getMass() * p1.vY) / p1.getMass();
                }
            }
        }
    }
}
