package com.orbit.data;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by fraayala19 on 5/24/17.
 */
public class UIListener extends InputListener{
    Stage stage;



    public UIListener(Stage stage){
        this.stage = stage;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Actor actor = stage.hit(x,y,true);

        if(actor!=null){
            return true;
        }

        return false;
    }
}
