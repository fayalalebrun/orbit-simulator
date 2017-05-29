package com.orbit.data.UI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.orbit.data.GameScreen;

/**
 * Created by fraayala19 on 5/29/17.
 */
public class SpeedWindow extends VisWindow{
    public SpeedWindow() {
        super("Simulation Speed");
        TableUtils.setSpacingDefaults(this);
        addWidgets();
        pack();
    }

    private void addWidgets(){
        final VisSlider slider = new VisSlider(0,100000, 10f,false);
        add(slider).width(150f);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameScreen.simSpeed = slider.getValue();
            }
        });
    }
}
