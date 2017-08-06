package com.orbit.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.orbit.data.entities.Planet;
import net.dermetfan.utils.StringUtils;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by fraayala19 on 5/31/17.
 */
public class SaveFileManager {

    public static void loadGame(GameScreen gameScreen, FileHandle file){
        gameScreen.removePlanets(gameScreen.getPlanetArrayList());

        String str = file.readString();
        String part[] = str.replaceAll("\\r", "").split("\\n");

        if (part[0].equals("v1")) {
            String name;
            double radius;
            double mass;
            double speed;
            double angle;
            Color c;
            double x;
            double y;
            float r, g, b, a;


            int i = 2;

            while (i < part.length - 2) {

                if (part[i].contains("name")) {
                    name = part[i].substring(6);
                    i++;
                    radius = Double.parseDouble(part[i].substring(8));
                    i++;
                    mass = Double.parseDouble(part[i].substring(6));
                    i++;
                    speed = Double.parseDouble(part[i].substring(7));
                    i++;
                    angle = Double.parseDouble(part[i].substring(7));
                    i++;
                    r = Float.parseFloat(part[i].substring(10));
                    i++;
                    g = Float.parseFloat(part[i].substring(10));
                    i++;
                    b = Float.parseFloat(part[i].substring(10));
                    i++;
                    a = Float.parseFloat(part[i].substring(10));
                    i++;

                    c = new Color(r, g, b, a);

                    x = Double.parseDouble(part[i].substring(3));
                    i++;
                    y = Double.parseDouble(part[i].substring(3));
                    i++;

                    Planet p = new Planet(name, radius, mass, speed, angle, c, x, y);

                    gameScreen.addPlanet(p);
                } else {
                    i++;
                }
            }
        } else if (part[0].contains("v2")){
            String name;
            double radius;
            double mass;
            double vX;
            double vY;
            double vZ;
            float r,g,b,a;
            double x;
            double y;
            double z;

            int i = 2;

            while(i<part.length-2){
                if(part[i].contains("name")){
                    name = part[i].substring(part[i].indexOf(':')+2);
                    i++;
                    radius = Double.parseDouble(part[i].substring(part[i].indexOf(':')+2));
                    i++;
                    mass = Double.parseDouble(part[i].substring(part[i].indexOf(':')+2));
                    i++;
                    vX = Double.parseDouble(part[i].substring(part[i].indexOf(':')+2));
                    i++;
                    vY = Double.parseDouble(part[i].substring(part[i].indexOf(':')+2));
                    i++;
                    vZ = Double.parseDouble(part[i].substring(part[i].indexOf(':')+2));
                    i++;
                    r = Float.parseFloat(part[i].substring(part[i].indexOf(':')+2));
                    i++;
                    g = Float.parseFloat(part[i].substring(part[i].indexOf(':')+2));
                    i++;
                    b = Float.parseFloat(part[i].substring(part[i].indexOf(':')+2));
                    i++;
                    a = Float.parseFloat(part[i].substring(part[i].indexOf(':')+2));
                    i++;
                    x = Double.parseDouble(part[i].substring(part[i].indexOf(':')+2));
                    i++;
                    y = Double.parseDouble(part[i].substring(part[i].indexOf(':')+2));
                    i++;
                    z = Double.parseDouble(part[i].substring(part[i].indexOf(':')+2));
                    i++;

                    Color c = new Color(r,g,b,a);

                    Planet p = new Planet(name, radius, mass, vX, vY, vZ, c, x, y, z);

                    gameScreen.addPlanet(p);
                } else {
                    i++;
                }
            }
        }
    }

    public static void saveGame(GameScreen gameScreen, FileHandle file){
        file.delete();

        Writer writer = file.writer(false);
        Vector<Planet> planetList = gameScreen.getPlanetArrayList();

        try {
            writer.write("v2\n\n");
        } catch (IOException e){
            e.printStackTrace();
        }

        synchronized (planetList) {
            for (Planet p : planetList) {
                try {
                    writer.write("name: " + p.getName() +
                            "\nradius: " + p.getOrigRadius() +
                            "\nmass: " + p.getOrigMass() +
                            "\nvX: " + p.vX +
                            "\nvY: " + p.vY +
                            "\nvZ: " + p.vZ +
                            "\ncolor-r: " + p.getCurrColor().r +
                            "\ncolor-g: " + p.getCurrColor().g +
                            "\ncolor-b: " + p.getCurrColor().b +
                            "\ncolor-a: " + p.getCurrColor().a +
                            "\nx: " + p.getxPos() +
                            "\ny: " + p.getyPos() +
                            "\nz: " + p.zPos +
                            "\n\n"
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
