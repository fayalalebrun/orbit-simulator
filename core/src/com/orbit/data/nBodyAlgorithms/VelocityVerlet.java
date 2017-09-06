package com.orbit.data.nBodyAlgorithms;

import com.badlogic.gdx.Game;
import com.orbit.data.GameScreen;
import com.orbit.data.entities.Planet;
import com.orbit.data.helpers.Units;

/**
 * Created by fraayala19 on 9/6/17.
 */
public class VelocityVerlet extends NBodyAlgorithm {

    private double temporary, ax, ay, az;

    public VelocityVerlet(GameScreen gameScreen) {
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

                double pX = Units.AUToM(p.xPos);
                double pY = Units.AUToM(p.yPos);
                double pZ = Units.AUToM(p.zPos);

                if (!p.accelInit) {
                    for (int i = 0; i < this.planets.indexOf(p); i++) {
                        parseAcceleration(planets.get(i), pX, pY, pZ);
                    }
                    for (int i = this.planets.indexOf(p) + 1; i < this.planets.size(); i++) {
                        parseAcceleration(planets.get(i), pX, pY, pZ);
                    }

                    ax *= Units.GRAV;
                    ay *= Units.GRAV;
                    az *= Units.GRAV;

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

                for (int i = 0; i < this.planets.indexOf(p); i++) {
                    parseAcceleration(planets.get(i), pX, pY, pZ);
                }
                for (int i = this.planets.indexOf(p) + 1; i < this.planets.size(); i++) {
                    parseAcceleration(planets.get(i), pX, pY, pZ);
                }
                ax *= Units.GRAV;
                ay *= Units.GRAV;
                az *= Units.GRAV;

                p.vX = p.vX + ((ax+p.lastAccelX)/2)*delta;
                p.vY = p.vY + ((ax+p.lastAccelY)/2)*delta;
                p.vZ = p.vZ + ((ax+p.lastAccelZ)/2)*delta;

                p.lastAccelX = ax;
                p.lastAccelY = ay;
                p.lastAccelZ = az;
            }
        }
    }

    private void parseAcceleration(Planet other, double pX, double pY, double pZ){
        double oX = Units.AUToM(other.xPos);
        double oY = Units.AUToM(other.yPos);
        double oZ = Units.AUToM(other.zPos);
        temporary = other.getMass() / cb(Math.sqrt(sq(oX-pX) + sq(oY-pY) + sq(oZ - pZ)));
        ax += (oX - pX) * temporary;
        ay += (oY - pY) * temporary;
        az += (oZ - pZ) * temporary;
    }
}
