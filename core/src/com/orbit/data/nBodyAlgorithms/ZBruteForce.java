package com.orbit.data.nBodyAlgorithms;

import com.orbit.data.GameScreen;
import com.orbit.data.entities.Planet;
import com.orbit.data.helpers.Units;

/**
 * Created by Fran on 8/5/2017.
 */
public class ZBruteForce extends NBodyAlgorithm{

    public ZBruteForce(GameScreen gameScreen) {
        super(gameScreen);
    }

    @Override
    protected void runAlgorithm() {
        synchronized (planets){
            double delta = getDelta();
            delta *= gameScreen.simSpeed;
            for (Planet p1 : this.planets){
                double x = Units.AUToM(p1.xPos);
                double y = Units.AUToM(p1.yPos);
                double z = Units.AUToM(p1.zPos);

                double fX = 0;
                double fY = 0;
                double fZ = 0;
                for (Planet p2: this.planets){
                    if(!p1.equalsP(p2)){
                        double thatX = Units.AUToM(p2.xPos);
                        double thatY = Units.AUToM(p2.yPos);
                        double thatZ = Units.AUToM(p2.zPos);

                        double dist = Math.sqrt(Math.pow(thatX-x,2)+Math.pow(thatY-y,2)+Math.pow(thatZ-z,2));

                        double angA = Math.atan2(thatY-y, thatX - x);
                        double angB = Math.asin((thatZ-z)/dist);

                        double f = Units.GRAV * ((p1.getMass() * p2.getMass()) / Math.pow(dist, 2));
                        double k = f * Math.cos(angB);

                        fX+=k*Math.cos(angA);
                        fY+=k*Math.sin(angA);
                        fZ+=f*Math.sin(angB);
                    }
                }

                x = 0.5 * (fX / p1.getMass()) * Math.pow(delta,2) + p1.vX * delta + x;
                y = 0.5 * (fY / p1.getMass()) * Math.pow(delta,2) + p1.vY * delta + y;
                z = 0.5 * (fZ / p1.getMass()) * Math.pow(delta,2) + p1.vZ * delta + z;

                synchronized (p1){
                    p1.xPos = Units.mToAU(x);
                    p1.yPos = Units.mToAU(y);
                    p1.zPos = Units.mToAU(z);
                }

                p1.vX = (fX * delta + p1.getMass() * p1.vX) / p1.getMass();
                p1.vY = (fY * delta + p1.getMass() * p1.vY) / p1.getMass();
                p1.vZ = (fZ * delta + p1.getMass() * p1.vZ) / p1.getMass();
            }
        }
    }
}
