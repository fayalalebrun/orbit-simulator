package com.orbit.data.UI;

import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisProgressBar;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.kotcrab.vis.ui.widget.VisWindow;

/**
 * Created by Fran on 5/25/2017.
 */
public class SizeMultWindow extends VisWindow{
    public SizeMultWindow() {
        super("Size Multiplication");
        TableUtils.setSpacingDefaults(this);
        addWidgets();
        pack();
    }

    private void addWidgets(){
        VisSlider slider = new VisSlider(0f, 10f, 0.1f, false);
        add(slider);
    }
}
