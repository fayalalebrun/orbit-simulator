package com.orbit.data;

import com.badlogic.gdx.Gdx;
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
import com.orbit.data.UI.PlanetListWindow;
import com.orbit.data.entities.Planet;

/**
 * Created by fraayala19 on 5/23/17.
 */
public class GameListener extends InputListener implements Disableable{
    Stage stage;

    GameScreen gameScreen;

    OrthographicCamera camera;

    boolean placementDisable;

    boolean moveInProgress = false;
    float moveLastX, moveLastY;

    OptionsWindow optionsWindow;

    PlanetListWindow planetListWindow;

    public GameListener(Stage stage, PlanetListWindow planetListWindow, GameScreen gameScreen) {
        this.stage = stage;
        this.planetListWindow = planetListWindow;
        placementDisable = false;
        camera = (OrthographicCamera) stage.getCamera();
        this.gameScreen = gameScreen;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        switch (GameScreen.currentTool){
            case POINTER:
                if(gameScreen.placePlanet(x,y)){
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
            x = Gdx.input.getDeltaX();
            y = Gdx.input.getDeltaY();
            camera.translate(-x*0.00005f*camera.zoom,y*0.00005f*camera.zoom);
        }

    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        moveInProgress = false;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {

        switch(keycode){
            case Input.Keys.LEFT:
                camera.translate(-0.002f*camera.zoom,0f);
                return  true;
            case Input.Keys.RIGHT:
                camera.translate(0.002f*camera.zoom,0f);
                return true;
            case Input.Keys.UP:
                camera.translate(0f, 0.002f*camera.zoom);
                return true;
            case Input.Keys.DOWN:
                camera.translate(0f, -0.002f*camera.zoom);
                return true;
            case Input.Keys.H:
                gameScreen.hideUI();
                return true;
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
        return gameScreen.placePlanet(x,y);
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

}
