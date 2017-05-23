package com.orbit.data;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.orbit.data.UI.OptionsWindow;

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
        if(actor==null){
            System.out.println("yes");
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
}
