package com.orbit.data.UI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;

/**
 * Created by fraayala19 on 5/23/17.
 */
public class OptionsWindow extends VisWindow{
    public OptionsWindow() {
        super("Planetary options");
        TableUtils.setSpacingDefaults(this);
        columnDefaults(0).left();
        addWidgets();
        pack();

        setPosition(100,100);
    }

    private void addWidgets(){
    }
}
