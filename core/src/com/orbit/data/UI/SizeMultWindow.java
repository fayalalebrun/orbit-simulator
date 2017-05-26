package com.orbit.data.UI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisProgressBar;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.orbit.data.GameScreen;

/**
 * Created by Fran on 5/25/2017.
 */
public class SizeMultWindow extends VisWindow{

    public SizeMultWindow() {
        super("Viewable Multiplication");

        TableUtils.setSpacingDefaults(this);
        addWidgets();
        pack();
    }

    private void addWidgets(){
        final VisSlider slider = new VisSlider(0f, 1f, 0.01f, false);
        add(slider);

        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameScreen.sizeMultVar = slider.getValue();
            }
        });
    }
}
