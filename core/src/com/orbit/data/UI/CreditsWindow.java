package com.orbit.data.UI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;

/**
 * Created by fraayala19 on 8/24/17.
 */
public class CreditsWindow extends VisWindow{
    public CreditsWindow(){
        super("Credits");

        TableUtils.setSpacingDefaults(this);
        addWidgets();
        pack();

        setVisible(false);
    }

    private void addWidgets(){
        VisTable table1 = new VisTable();
        VisTable table2 = new VisTable();
        VisLabel label = new VisLabel(""+
                "Textures -\n"+
                "\n"+
                "\n"+
                "Programming -");

        VisLabel label2 = new VisLabel("\nwww.solarsystemscope.com\n"+
                "John van Vliet\n"+
                "TonnyBGood\n"+
                "Francisco Ayala\n"+
                "David Rockenzahn");

        table1.add(label);
        table2.add(label2);

        add(table1).padRight(3f);
        add(table2);

        row();
        VisTextButton closeButton = new VisTextButton("Close");
        final CreditsWindow window = this;
        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                window.setVisible(false);
            }
        });
        this.add(closeButton);
    }
}
