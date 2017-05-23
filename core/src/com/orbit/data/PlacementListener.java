package com.orbit.data;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by fraayala19 on 5/23/17.
 */
public class PlacementListener extends InputListener{
    Stage stage;

    public PlacementListener(Stage stage) {
        this.stage = stage;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Actor actor = stage.hit(x,y,true);
        if(actor==null){
            System.out.println("yes");
        }
        return false;
    }
}
