package com.orbit.data.UI;

import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.orbit.data.entities.Planet;

import java.util.ArrayList;

/**
 * Created by Fran on 5/26/2017.
 */
public class PlanetListWindow extends VisWindow{

    PlanetAdapter adapter;

    public PlanetListWindow() {
        super("Planets");
        TableUtils.setSpacingDefaults(this);
        addWidgets();
        setSize(180,250);
    }

    public void addWidgets(){
        adapter = new PlanetAdapter(new ArrayList<Planet>());
        ListView<Planet> view = new ListView<Planet>(adapter);
        VisScrollPane scrollPane = new VisScrollPane(view.getMainTable());
        scrollPane.setFlickScroll(false);
        scrollPane.setFadeScrollBars(false);
        add(scrollPane).spaceTop(30).growX().width(150f).height(200f);
    }

    public void addPlanet(Planet planet){
        adapter.add(planet);

    }

}
