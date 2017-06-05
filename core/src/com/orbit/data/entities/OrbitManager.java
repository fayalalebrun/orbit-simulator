package com.orbit.data.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Fran on 6/3/2017.
 */
public class OrbitManager extends Actor{
    ArrayList<Orbit> orbits;
    Texture lineTexture;
    int maxPoints = 60;
    float interval = 1f;

    public OrbitManager(){
        orbits = new ArrayList<Orbit>();
        Pixmap pixmap1 = new Pixmap(1,1, Pixmap.Format.RGBA8888);
        pixmap1.setColor(Color.WHITE.cpy());
        pixmap1.fill();
        lineTexture = new Texture(pixmap1);
        pixmap1.dispose();
    }

    @Override
    public void act(float delta) {
        removeInactive();
        for(Orbit o : this.orbits){
            o.act(delta);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureAtlas.AtlasRegion lineAtlas = new TextureAtlas.AtlasRegion(lineTexture, 0, 0, 1, 1);
        for(Orbit o : this.orbits){
            if(o.getPoints().size()>=2){
                batch.setColor(o.getColor());
                for(int i = 0; i < o.getPoints().size()-1; i++){
                    drawLine(batch, o.getPoints().get(i).x,o.getPoints().get(i).y,
                            o.getPoints().get(i+1).x, o.getPoints().get(i+1).y,0.000065f*getCamera().zoom, lineAtlas);
                }
            }
        }
    }

    private void removeInactive(){
        ArrayList<Orbit> toBeRemoved = new ArrayList<Orbit>();

        for(Orbit o : this.orbits){
            if(!o.isActive()){
                toBeRemoved.add(o);
            }
        }

        orbits.removeAll(toBeRemoved);
    }

    public void clearAll(){
        for (Orbit o : this.orbits){
            o.clear();
        }
    }

    public static void drawLine(Batch batch, float x1, float y1, float x2, float y2, float lineWidth, TextureAtlas.AtlasRegion lineTexture) {
        float xdif = x2-x1;
        float ydif = y2-y1;
        float l2 = xdif*xdif+ydif*ydif;
        float invl = (float)(1/Math.sqrt(l2));
        //dif is vector with length linewidth from first to second vertex
        xdif*=invl*lineWidth;
        ydif*=invl*lineWidth;

        float floatBits = batch.getColor().toFloatBits();
        //draw quad with width of linewidth*2 through (x1, y1) and (x2, y2)
        float[] verts = new float[]{x1+ydif, y1-xdif, floatBits, lineTexture.getU(), lineTexture.getV(),
                x1-ydif, y1+xdif, floatBits, lineTexture.getU2(), lineTexture.getV(),
                x2-ydif, y2+xdif, floatBits, lineTexture.getU2(), lineTexture.getV2(),
                x2+ydif, y2-xdif, floatBits, lineTexture.getU(), lineTexture.getV2()};
        batch.draw(lineTexture.getTexture(), verts, 0, 20);
    }

    public void addOrbit(Planet p){
        orbits.add(new Orbit(p,this));
    }

    private OrthographicCamera getCamera(){
        return (OrthographicCamera)getStage().getCamera();
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public float getInterval() {
        return interval;
    }

    public void setInterval(float interval) {
        this.interval = interval;
    }
}
