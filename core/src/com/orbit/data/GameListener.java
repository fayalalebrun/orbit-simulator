package com.orbit.data;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.orbit.data.UI.OptionsWindow;
import com.orbit.data.entities.Planet;

/**
 * Created by fraayala19 on 5/23/17.
 */
public class GameListener extends InputListener implements Disableable{
    Stage stage;

    OrthographicCamera camera;

    boolean placementDisable;

    OptionsWindow optionsWindow;

    public GameListener(Stage stage) {
        this.stage = stage;
        placementDisable = false;
        camera = (OrthographicCamera) stage.getCamera();
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Actor actor = stage.hit(x,y,true);
        if(actor==null&&!placementDisable &&optionsWindow!=null){
            stage.addActor(new Planet(getRadius(),getMass(),getSpeed(),getAngle(),getColor(),
                    new Vector2(x-getRadius(),y-getRadius())));
            return true;
        }
        return false;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {

        switch(keycode){
            case Input.Keys.A:
                camera.zoom+=0.25f;
                System.out.println(camera.zoom);
                return true;
            case Input.Keys.Q:
                if(camera.zoom<0.5f){
                    return false;
                }
                camera.zoom-=0.25f;
                return true;
        }

        return false;
    }

    @Override
    public boolean scrolled(InputEvent event, float x, float y, int amount) {
        if(camera.zoom+amount*(camera.zoom/100)>0.001) {
            camera.zoom += amount * (camera.zoom / 100);
            return true;
        }

        return false;
    }

    @Override
    public void setDisabled(boolean isDisabled) {
        placementDisable = isDisabled;
    }

    @Override
    public boolean isDisabled() {
        return placementDisable;
    }

    public void setOptionsWindow(OptionsWindow optionsWindow){
        this.optionsWindow = optionsWindow;
    }

    private float getRadius(){
        return Float.parseFloat(optionsWindow.getRadiusField().getText());
    }

    private float getMass(){
        return Float.parseFloat(optionsWindow.getMassField().getText());
    }

    private float getSpeed(){
        return Float.parseFloat(optionsWindow.getSpeedField().getText());
    }

    private float getAngle(){
        return Float.parseFloat(optionsWindow.getSpeedField().getText());
    }

    private Color getColor(){
        return optionsWindow.getPickerImage().getColor().cpy();
    }
}
