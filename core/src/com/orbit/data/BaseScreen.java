package com.orbit.data;

import com.badlogic.gdx.Screen;

/**
 * Created by Fran on 5/22/2017.
 */
public abstract class BaseScreen implements Screen {

    protected Boot boot;

    public BaseScreen(Boot boot){
        this.boot = boot;
    }

    protected Boot getBoot(){
        return this.boot;
    }

}
