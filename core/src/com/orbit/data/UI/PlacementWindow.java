package com.orbit.data.UI;

import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.util.form.SimpleFormValidator;
import com.kotcrab.vis.ui.widget.*;

import java.lang.reflect.Field;

/**
 * Created by fraayala19 on 5/25/17.
 */
public class PlacementWindow extends VisWindow{
    VisValidatableTextField xField;
    VisValidatableTextField yField;
    VisTextButton placeButton;

    public PlacementWindow() {
        super("Specific Placement");
        TableUtils.setSpacingDefaults(this);
        addWidgets();
        pack();

        setPosition(1000,0);
    }

    private void addWidgets(){
        xField = new VisValidatableTextField();
        yField = new VisValidatableTextField();

        placeButton = new VisTextButton("Place");

        VisTable fieldTable = new VisTable();

        fieldTable.add(new VisLabel("x:")).padRight(3);
        fieldTable.add(xField).padRight(3).size(128f,24f);
        fieldTable.add(new VisLabel("AU y:")).padRight(3);
        fieldTable.add(yField).padRight(3).size(128f,24f);
        fieldTable.add(new VisLabel("AU")).padRight(3);
        fieldTable.add(placeButton);
        add(fieldTable);


        SimpleFormValidator validator = new SimpleFormValidator(placeButton);
        validator.floatNumber(xField,"");
        validator.floatNumber(yField, "");

    }
}
