package com.orbit.data.UI;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kotcrab.vis.ui.widget.VisImageButton;

/**
 * Created by Fran on 5/24/2017.
 */
public class ToolbarButton extends VisImageButton{

    Tool tool;

    Tool currentTool;

    public ToolbarButton(Drawable imageUp, String tooltipText, Tool tool, Tool currentTool) {
        super(imageUp, "");
        this.tool = tool;
        this.currentTool = currentTool;
        this.setSize(32f,32f);
    }



}
