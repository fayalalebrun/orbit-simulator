package com.orbit.data.UI;

import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisWindow;

/**
 * Created by fraayala19 on 5/23/17.
 */
public class OptionsWindow extends VisWindow{
    public OptionsWindow() {
        super("Planetary options");
        TableUtils.setSpacingDefaults(this);
        addWidgets();
        pack();
    }

    private void addWidgets(){

    }

}
