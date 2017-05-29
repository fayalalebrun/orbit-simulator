package com.orbit.data.UI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.util.form.SimpleFormValidator;
import com.kotcrab.vis.ui.widget.*;
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
        final VisValidatableTextField speedField = new VisValidatableTextField();
        speedField.setText("1.0");
        VisTextButton setButton = new VisTextButton("Set");

        SimpleFormValidator validator = new SimpleFormValidator(setButton);

        validator.notEmpty(speedField, "");
        validator.floatNumber(speedField, "");

        setButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameScreen.simSpeed = Float.parseFloat(speedField.getText());
            }
        });

        this.add(speedField).padRight(3.0f).width(96f);
        this.add(setButton);
    }
}
