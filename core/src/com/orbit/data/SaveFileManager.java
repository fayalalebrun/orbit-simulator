package com.orbit.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.orbit.data.entities.Planet;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by fraayala19 on 5/31/17.
 */
public class SaveFileManager {

    public static void loadGame(GameScreen gameScreen, FileHandle file){

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
                        "\ncolor: "+p.getCurrColor().toString()+
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
