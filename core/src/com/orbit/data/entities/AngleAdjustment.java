package com.orbit.data.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.orbit.data.UI.OptionsWindow;
import javafx.scene.shape.Line;

/**
 * Created by Fran on 5/26/2017.
 */
public class AngleAdjustment extends Actor{

    private OptionsWindow optionsWindow;
    Texture texture, lineTexture;

    float lineX, lineY, centerCoordX, centerCoordY;

    boolean firstAct = true;

    public AngleAdjustment(final OptionsWindow optionsWindow){
        this.optionsWindow = optionsWindow;

        Pixmap pixmap1 = new Pixmap(1,1, Pixmap.Format.RGBA8888);
        pixmap1.setColor(Color.GREEN.cpy());
        pixmap1.fill();
        lineTexture = new Texture(pixmap1);
        pixmap1.dispose();

        Pixmap pixmap = new Pixmap(512, 512, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK.cpy());
        pixmap.fillCircle(256,256,256);
        texture = new Texture(pixmap);
        setSize(96,96);
        pixmap.dispose();

        addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                lineX = x+getX();
                lineY = y+getY();
                optionsWindow.getAngleField().setText(getAngle(lineX,lineY)+"");
                optionsWindow.getSpeedField().setText((float)(Math.hypot(centerCoordX-lineX,centerCoordY-lineY)/
                        (getWidth()/2))*55+"");
            }
        });

    }

    @Override
    public void act(float delta) {
        if(firstAct){
            centerCoordX = getX()+getWidth()/2;
            centerCoordY = getY()+getHeight()/2;
            lineX = getX()+getWidth()/2;
            lineY = getY()+getHeight()/2;
            firstAct = false;
        }
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        if(!touchable){
            return null;
        }

        Circle c = new Circle(getWidth()/2,getWidth()/2,getWidth()/2);
        if(c.contains(x,y)){
            return this;
        }
        return null;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        drawLine(batch,getX()+getWidth()/2,getY()+getHeight()/2, lineX, lineY,1f, new TextureAtlas.AtlasRegion(lineTexture,0,0,1,1));
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

    private float getAngle(float x, float y){
        x-=centerCoordX;
        y-=centerCoordY;

        float angle;
        if(y==0&&x==0||y==0&&x>0){
            return 0;
        } else if(x==0&&y>0){
            return 90;
        } else if(y==0&&x<0){
            return 180;
        } else if(x==0&&y<0){
            return 270;
        }

        if(y>0&&x>0){
            return (float)Math.toDegrees(Math.atan2(y,x));
        } else if(y>0&&x<0){
            return (float)Math.toDegrees(Math.atan2(Math.abs(x),y))+90;
        } else if (y<0&&x<0){
            return (float)Math.toDegrees(Math.atan2(Math.abs(y),Math.abs(x)))+180;
        } else if(y<0&&x>0){
            return (float)Math.toDegrees(Math.atan2(Math.abs(x), Math.abs(y)))+270;
        }


        return 1337;
    }
}
