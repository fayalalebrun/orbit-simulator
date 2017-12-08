package com.orbit.data.helpers;

/**
 * Created by Fran on 8/3/2017.
 */
public class Units {
    public static final double GRAV = 1;
            //6.67408 * Math.pow(10,-11);

    public static double mToAU(double meters){
        return meters/149597870691.0;
    }

    public static double AUToM(double au){
        return au*149597870691.0;
    }
}
