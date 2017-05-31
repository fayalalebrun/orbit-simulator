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

/**
 * Created by fraayala19 on 5/31/17.
 */
public class SaveFileManager {

    public static void loadGame(GameScreen gameScreen, FileHandle file){
        String name;
        double radius;
        double mass;
        double speed;
        double angle;
        Color c;
        double x;
        double y;

        gameScreen.removePlanets(gameScreen.getPlanetArrayList());

        String str = file.readString();
        String part[] = str.replaceAll("\\r", "").split("\\n");
        int i=0;
        System.out.println(part.length);
        while(i<part.length-2){
            System.out.println(part[i].substring(0,4));
            if(part[i].substring(0,4).equals("name")){
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
                c = new Color(Integer.parseInt(part[i].substring(7)));
                c = Color.YELLOW.cpy();
                i++;
                x = Double.parseDouble(part[i].substring(3));
                i++;
                y = Double.parseDouble(part[i].substring(3));
                i++;

                Planet p = new Planet(name, radius, mass, speed, angle, c, x, y, gameScreen.getPlanetArrayList());

                gameScreen.addPlanet(p);
            } else{
                i++;
            }
        }
    }

    public static void saveGame(GameScreen gameScreen, FileHandle file){
        file.delete();

        Writer writer = file.writer(false);
        ArrayList<Planet> planetList = gameScreen.getPlanetArrayList();

        for(Planet p: planetList){
            try {
                writer.write("name: "+p.getName()+
                        "\nradius: "+p.getOrigRadius()+
                        "\nmass: "+p.getOrigMass()+
                        "\nspeed: "+p.getSpeed()+
                        "\nangle: "+p.getAngle()+
                        "\ncolor: "+p.getCurrColor().toIntBits()+
                        "\nx: "+p.getxPos()+
                        "\ny: "+p.getyPos()+"\n\n");
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
