package com.orbit.data.UI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.util.form.SimpleFormValidator;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisValidatableTextField;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.orbit.data.entities.OrbitManager;

/**
 * Created by fraayala19 on 6/5/17.
 */
public class OrbitTracingWindow extends VisWindow{

    private OrbitManager orbitManager;

    public OrbitTracingWindow(OrbitManager orbitManager) {
        super("Orbit Tracing");
        this.orbitManager = orbitManager;

        TableUtils.setSpacingDefaults(this);
        addWidgets();
        pack();
    }

    private void addWidgets(){
        final VisValidatableTextField intervalField = new VisValidatableTextField();
        final VisValidatableTextField limitField = new VisValidatableTextField();

        VisTextButton setButton = new VisTextButton("Set");

        add(new VisLabel("Interval:"));
        add(intervalField);
        row();

        add(new VisLabel("Max Points:"));
        add(limitField);
        row();

        add(setButton);

        SimpleFormValidator validator = new SimpleFormValidator(setButton);

        validator.floatNumber(intervalField,"");
        validator.valueGreaterThan(intervalField,"",0,true);

        validator.integerNumber(limitField,"");
        validator.valueGreaterThan(limitField,"",0,true);

        setButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                orbitManager.clearAll();
                orbitManager.setInterval(Float.parseFloat(intervalField.getText()));
                orbitManager.setMaxPoints(Integer.parseInt(limitField.getText()));
            }
        });
        
        intervalField.setText(orbitManager.getInterval()+"");
        limitField.setText(orbitManager.getMaxPoints()+"");
    }
}
