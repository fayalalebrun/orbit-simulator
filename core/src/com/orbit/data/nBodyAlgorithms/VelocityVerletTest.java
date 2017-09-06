package com.orbit.data.nBodyAlgorithms;

import com.orbit.data.GameScreen;
import com.orbit.data.entities.Planet;
import com.orbit.data.helpers.Units;

/**
 * Created by fraayala19 on 9/6/17.
 */
public class VelocityVerletTest extends NBodyAlgorithm{
    private double temporary, ax, ay, az;

    public VelocityVerletTest(GameScreen gameScreen) {
        super(gameScreen);
    }

    @Override
    protected void runAlgorithm() {
        synchronized (planets) {
            double delta = getDelta();
            delta *= GameScreen.simSpeed;
            for (Planet p : this.planets) {

                ax = 0;
                ay = 0;
                az = 0;
                double fX = 0, fY = 0, fZ = 0;

                double pX = Units.AUToM(p.xPos);
                double pY = Units.AUToM(p.yPos);
                double pZ = Units.AUToM(p.zPos);

                if (!p.accelInit) {
                    for (Planet p2: this.planets){
                        if(!p.equalsP(p2)){
                            double thatX = Units.AUToM(p2.xPos);
                            double thatY = Units.AUToM(p2.yPos);
                            double thatZ = Units.AUToM(p2.zPos);

                            double dist = Math.sqrt(Math.pow(thatX-pX,2)+Math.pow(thatY-pY,2)+Math.pow(thatZ-pZ,2));

                            double angA = Math.atan2(thatY-pY, thatX - pX);
                            double angB = Math.asin((thatZ-pZ)/dist);

                            double f = Units.GRAV * ((p.getMass() * p2.getMass()) / Math.pow(dist, 2));
                            double k = f * Math.cos(angB);

                            fX+=k*Math.cos(angA);
                            fY+=k*Math.sin(angA);
                            fZ+=f*Math.sin(angB);
                        }
                    }

                    ax = fX/p.getMass();
                    ay = fY/p.getMass();
                    az = fZ/p.getMass();


                    p.lastAccelX = ax;
                    p.lastAccelY = ay;
                    p.lastAccelZ = az;

                    p.accelInit = true;
                }

                ax = 0;
                ay = 0;
                az = 0;

                synchronized (p) {
                    pX = pX + p.vX * delta + 0.5 * p.lastAccelX * sq(delta);
                    pY = pY + p.vY * delta + 0.5 * p.lastAccelY * sq(delta);
                    pZ = pZ + p.vZ * delta + 0.5 * p.lastAccelZ * sq(delta);

                    p.xPos = Units.mToAU(pX);
                    p.yPos = Units.mToAU(pY);
                    p.zPos = Units.mToAU(pZ);
                }


                for (Planet p2: this.planets){
                    if(!p.equalsP(p2)){
                        double thatX = Units.AUToM(p2.xPos);
                        double thatY = Units.AUToM(p2.yPos);
                        double thatZ = Units.AUToM(p2.zPos);

                        double dist = Math.sqrt(Math.pow(thatX-pX,2)+Math.pow(thatY-pY,2)+Math.pow(thatZ-pZ,2));

                        double angA = Math.atan2(thatY-pY, thatX - pX);
                        double angB = Math.asin((thatZ-pZ)/dist);

                        double f = Units.GRAV * ((p.getMass() * p2.getMass()) / Math.pow(dist, 2));
                        double k = f * Math.cos(angB);

                        fX+=k*Math.cos(angA);
                        fY+=k*Math.sin(angA);
                        fZ+=f*Math.sin(angB);
                    }
                }

                ax = fX/p.getMass();
                ay = fY/p.getMass();
                az = fZ/p.getMass();

                p.vX = p.vX + ((ax+p.lastAccelX)/2)*delta;
                p.vY = p.vY + ((ax+p.lastAccelY)/2)*delta;
                p.vZ = p.vZ + ((ax+p.lastAccelZ)/2)*delta;

                p.lastAccelX = ax;
                p.lastAccelY = ay;
                p.lastAccelZ = az;
            }
        }
    }


}
