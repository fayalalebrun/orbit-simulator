package com.orbit.data.UI.tutorial;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by fraayala19 on 1/19/18.
 */
public abstract class TutorialPage extends Table {

    protected TutorialPage previousPage;
    protected TutorialPage nextPage;
    protected TutorialWindow tutorialWindow;

    public TutorialPage(TutorialWindow tutorialWindow) {
        super();
        this.tutorialWindow = tutorialWindow;
    }

    public TutorialPage getPreviousPage() {
        return previousPage;
    }

    public TutorialPage getNextPage() {
        return nextPage;
    }

    public abstract void build(TutorialPage previousPage, TutorialPage nextPage);
}
