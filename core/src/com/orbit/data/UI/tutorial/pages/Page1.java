package com.orbit.data.UI.tutorial.pages;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.orbit.data.UI.tutorial.TutorialPage;
import com.orbit.data.UI.tutorial.TutorialWindow;


/**
 * Created by fraayala19 on 1/22/18.
 */
public class Page1 extends TutorialPage {

    public Page1(TutorialWindow tutorialWindow) {
        super(tutorialWindow);
    }

    @Override
    protected void assignPages() {

    }

    @Override
    protected void build() {
        VisLabel body = new VisLabel("aaaaaa aaaaaa aaaaaa aaaaaa aaaaaa ");
        body.setWrap(true);
        add(body).size(100);
        row();
        add(previousButton).padRight(3f);
        add(nextButton);
    }
}
