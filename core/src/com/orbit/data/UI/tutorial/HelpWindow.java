package com.orbit.data.UI.tutorial;/*
* =====================================================================
* ==      Created by davrockenzahn19        ==    Date: 1/25/18   ==
* =====================================================================
* ==      Project: Orbit Simulator    ==
* =====================================================================

*/

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;

public class HelpWindow extends VisWindow{


    public HelpWindow() {
        super("Help");

        setVisible(false);
        TableUtils.setSpacingDefaults(this);


        Table table = new Table();

        VisLabel label = new VisLabel("Hotkeys: \n " +
                "   [F] - Toggle fullscreen \n" +
                "   [H] - Hide and show windows");

        label.setWrap(true);


        table.add(label).width(300);

        VisTextButton button = new VisTextButton("Close");
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setVisible(false);
            }
        });

        table.row();
        table.add(button);

        super.add(table);
        pack();

    }

}
