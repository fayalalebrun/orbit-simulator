package com.orbit.data.UI;

import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisWindow;

/**
 * Created by Fran on 5/26/2017.
 */
public class AngleAdjustmentWindow extends VisWindow{

    OptionsWindow optionsWindow;

    public AngleAdjustmentWindow(OptionsWindow optionsWindow) {
        super("Angle Adjustment");
        this.optionsWindow = optionsWindow;

        TableUtils.setSpacingDefaults(this);
        addWidgets();
        pack();
    }

    private void addWidgets(){

    }

}
