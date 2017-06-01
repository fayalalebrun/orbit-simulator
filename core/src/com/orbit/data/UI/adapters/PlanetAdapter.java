package com.orbit.data.UI.adapters;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.adapter.ArrayListAdapter;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.orbit.data.GameScreen;
import com.orbit.data.UI.PlanetMagSliderListener;
import com.orbit.data.entities.Planet;

import java.util.ArrayList;

/**
 * Created by Fran on 5/26/2017.
 */
public class PlanetAdapter extends ArrayListAdapter<Planet, VisTable>{
    private final Drawable bg = VisUI.getSkin().getDrawable("window-bg");
    private final Drawable selection = VisUI.getSkin().getDrawable("list-selection");

    private GameScreen gameScreen;

    public PlanetAdapter(ArrayList<Planet> array, GameScreen gameScreen) {
        super(array);
        this.gameScreen = gameScreen;
        setSelectionMode(SelectionMode.DISABLED);
    }

    @Override
    protected VisTable createView(final Planet item) {

        VisTable table = new VisTable();

        table.add(new VisLabel(item.getName())).padRight(3f).width(60f);

        final VisSlider slider = new VisSlider(0f, 100f,1f,false);

        table.add(slider).width(64f).padRight(3f);

        slider.addListener(new PlanetMagSliderListener(item, slider));

        final VisTextButton goButton = new VisTextButton("Go");

        table.add(goButton).width(20f);

        goButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                item.zoomCamera();
                item.centerCamera();
            }
        });

        final VisTextButton eraseButton = new VisTextButton("Del");

        table.add(eraseButton).width(20f);

        eraseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ArrayList<Planet> pList = new ArrayList<Planet>();
                pList.add(item);
                gameScreen.removePlanets(pList);
            }
        });

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
