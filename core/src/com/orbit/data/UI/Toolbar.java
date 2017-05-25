package com.orbit.data.UI;

import com.badlogic.gdx.assets.AssetManager;
import com.kotcrab.vis.ui.widget.VisWindow;

/**
 * Created by Fran on 5/24/2017.
 */
public class Toolbar extends VisWindow{
    AssetManager manager;
    public Toolbar(AssetManager manager){
        super("Tools");
        this.manager = manager;
    }


}
