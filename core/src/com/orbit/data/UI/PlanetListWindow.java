package com.orbit.data.UI;

import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.orbit.data.GameScreen;
import com.orbit.data.UI.adapters.PlanetAdapter;
import com.orbit.data.entities.Planet;

import java.util.ArrayList;

/**
 * Created by Fran on 5/26/2017.
 */
public class PlanetListWindow extends VisWindow{

    PlanetAdapter adapter;
    GameScreen gameScreen;

    public PlanetListWindow(GameScreen gameScreen) {
        super("Planets");
        this.gameScreen = gameScreen;
        TableUtils.setSpacingDefaults(this);
        addWidgets();
        setSize(180,270);
    }

    public void addWidgets(){
        adapter = new PlanetAdapter(new ArrayList<Planet>(),gameScreen);
        ListView<Planet> view = new ListView<Planet>(adapter);
        VisScrollPane scrollPane = new VisScrollPane(view.getMainTable());
        scrollPane.setFlickScroll(false);
        scrollPane.setFadeScrollBars(false);
        add(scrollPane).spaceTop(30).growX().width(200f).height(200f);
    }

    public void addPlanet(Planet planet){
        adapter.add(planet);

    }

    public void removePlanet(Planet planet){
        adapter.remove(planet);
    }

}
