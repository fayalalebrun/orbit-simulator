package com.orbit.data;

import com.badlogic.gdx.graphics.Color;
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
public class PlacementListener extends InputListener implements Disableable{
    Stage stage;

    boolean disabled;

    OptionsWindow optionsWindow;

    public PlacementListener(Stage stage) {
        this.stage = stage;
        disabled = false;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Actor actor = stage.hit(x,y,true);
        if(actor==null&&!disabled&&optionsWindow!=null){
            stage.addActor(new Planet(getRadius(),getMass(),getSpeed(),getAngle(),getColor(),
                    new Vector2(x-getRadius(),y-getRadius())));
        }
        return false;
    }

    @Override
    public void setDisabled(boolean isDisabled) {
        disabled = isDisabled;
    }

    @Override
    public boolean isDisabled() {
        return disabled;
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
        return optionsWindow.getPicker().getColor();
    }
}
