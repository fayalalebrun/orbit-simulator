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

    boolean moveInProgress = false;
    float moveLastX, moveLastY;

    OptionsWindow optionsWindow;

    public GameListener(Stage stage) {
        this.stage = stage;
        placementDisable = false;
        camera = (OrthographicCamera) stage.getCamera();
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        switch (GameScreen.currentTool){
            case POINTER:
                if(placePlanet(x,y)){
                    return true;
                }

                break;
            case MOVE:
                moveInProgress = true;
                moveLastX = x;
                moveLastY = y;
                return true;
            case ZOOM_IN:
                zoom(-10);
                return true;
            case ZOOM_OUT:
                zoom(10);
                return true;
        }


        return false;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        if(moveInProgress){
            float changeInX = x-moveLastX;
            float changeInY = y-moveLastY;
            changeInX*=-1;
            changeInY*=-1;
            moveLastX=x;
            moveLastY=y;
            camera.translate(changeInX,changeInY);
        }

    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        moveInProgress = false;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {

        switch(keycode){
            case Input.Keys.A:
                System.out.println(camera.zoom);
                return  true;
        }

        return false;
    }

    @Override
    public boolean scrolled(InputEvent event, float x, float y, int amount) {
        return zoom(amount);
    }

    private boolean zoom(int amount){
        if(camera.zoom+amount*(camera.zoom/100)>0.00000000001) {
            camera.zoom += amount * (camera.zoom / 100);
            return true;
        }

        return false;
    }
    
    public boolean placePlanet(float x, float y){
        Actor actor = stage.hit(x,y,true);
        if(actor==null&&!placementDisable &&optionsWindow!=null){
            stage.addActor(new Planet(getRadius(),getMass(),getSpeed(),getAngle(),getColor(),
                    new Vector2(x,y),0.0));
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
