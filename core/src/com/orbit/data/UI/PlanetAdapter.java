package com.orbit.data.UI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.adapter.ArrayListAdapter;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.kotcrab.vis.ui.widget.VisTable;
import com.orbit.data.entities.Planet;

import java.util.ArrayList;

/**
 * Created by Fran on 5/26/2017.
 */
public class PlanetAdapter extends ArrayListAdapter<Planet, VisTable>{
    private final Drawable bg = VisUI.getSkin().getDrawable("window-bg");
    private final Drawable selection = VisUI.getSkin().getDrawable("list-selection");


    public PlanetAdapter(ArrayList<Planet> array) {
        super(array);
        setSelectionMode(SelectionMode.DISABLED);
    }

    @Override
    protected VisTable createView(Planet item) {

        VisTable table = new VisTable();

        table.add(new VisLabel(item.getName())).padRight(3f).width(60f);

        final VisSlider slider = new VisSlider(0f, 100f,1f,false);

        table.add(slider).width(70f);

        slider.addListener(new PlanetMagSliderListener(item, slider));

        return table;
    }

    @Override
    protected void selectView(VisTable view) {
        view.setBackground(selection);
    }

    @Override
    protected void deselectView(VisTable view) {
        view.setBackground(bg);
    }

}
