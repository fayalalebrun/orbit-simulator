package com.orbit.data.UI.tutorial;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

/**
 * Created by fraayala19 on 1/19/18.
 */
public abstract class TutorialPage extends Table {

    protected TutorialPage previousPage;
    protected TutorialPage nextPage;
    protected VisTextButton nextButton;
    protected VisTextButton previousButton;
    protected TutorialWindow tutorialWindow;

    public TutorialPage(TutorialWindow tutorialWindow, float width) {
        super();
        this.tutorialWindow = tutorialWindow;

        assignPages();
        setUpButtons();
        build(width);
    }

    public TutorialPage getPreviousPage() {
        return previousPage;
    }

    public TutorialPage getNextPage() {
        return nextPage;
    }

    private void setUpButtons(){
        final TutorialWindow tempTut = tutorialWindow;
        final TutorialPage tempPrev = previousPage;
        final TutorialPage tempNext = nextPage;


        if(previousPage!=null){
            previousButton = new VisTextButton("previous");
            previousButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    tempTut.changePage(tempPrev);
                }
            });
        } else {
            previousButton = new VisTextButton("previous");
            previousButton.setDisabled(true);
        }

        if(nextPage!=null){
            nextButton = new VisTextButton("next");
            nextButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    tempTut.changePage(tempNext);
                }
            });
        }else{
            nextButton = new VisTextButton("next");
            nextButton.setDisabled(true);
        }
    }

    protected abstract void assignPages();

    protected abstract void build(float width);
}
