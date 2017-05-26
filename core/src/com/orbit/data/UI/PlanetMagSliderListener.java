package com.orbit.data.UI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.orbit.data.entities.Planet;

/**
 * Created by Fran on 5/26/2017.
 */
public class PlanetMagSliderListener extends ChangeListener{
    Planet planet;
    Slider slider;
    public PlanetMagSliderListener(Planet planet, Slider slider) {
        super();
        this.planet = planet;
        this.slider = slider;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        planet.setMagnificationAmount(slider.getValue());
    }
}
