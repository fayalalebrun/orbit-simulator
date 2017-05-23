package com.orbit.data.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.util.form.SimpleFormValidator;
import com.kotcrab.vis.ui.widget.*;
import com.kotcrab.vis.ui.widget.color.ColorPicker;
import com.kotcrab.vis.ui.widget.color.ColorPickerAdapter;

/**
 * Created by fraayala19 on 5/23/17.
 */
public class OptionsWindow extends VisWindow{

    private static final Drawable white = VisUI.getSkin().getDrawable("white");

    private ColorPicker picker;

    public OptionsWindow() {
        super("Planetary options");
        TableUtils.setSpacingDefaults(this);
        columnDefaults(0).left();
        addWidgets();
        pack();

        setPosition(100,100);
    }

    private void addWidgets(){
        final Image image = new Image(white);

        picker = new ColorPicker("Choose color", new ColorPickerAdapter(){
            @Override
            public void finished (Color newColor) {
                image.setColor(newColor);
            }
        });

        VisTextButton showPickerButton = new VisTextButton("Color");

        showPickerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getStage().addActor(picker.fadeIn());
            }
        });

        Color c = new Color(27 / 255.0f, 161 / 255.0f, 226 / 255.0f, 1);
        picker.setColor(c);
        image.setColor(c);

        VisTable pickerTable = new VisTable(true);
        TableUtils.setSpacingDefaults(pickerTable);
        pickerTable.add(showPickerButton);
        pickerTable.add(image).size(32).pad(3);
        pickerTable.pack();

        VisValidatableTextField radiusField = new VisValidatableTextField();
        VisValidatableTextField massField = new VisValidatableTextField();
        VisValidatableTextField speedField = new VisValidatableTextField();
        VisValidatableTextField angleField = new VisValidatableTextField();



        add(new VisLabel("Radius(m):"));
        add(radiusField);

        row();

        add(new VisLabel("Mass(kg):"));
        add(massField).expand().fill();

        row();

        add(new VisLabel("Speed(m/s):"));
        add(speedField).expand().fill();

        row();

        add(new VisLabel("Angle(degrees):")).expand().fill();
        add(angleField).expand().fill();

        row();

        add(pickerTable);
    }
}
