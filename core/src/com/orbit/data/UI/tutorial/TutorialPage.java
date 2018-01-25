package com.orbit.data.UI.tutorial;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.orbit.data.Boot;

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


    }

    public TutorialPage getPreviousPage() {
        return previousPage;
    }

    public TutorialPage getNextPage() {
        return nextPage;
    }

    public void setUpButtons(){
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

    protected static float calcImageHeight(float width, Image image){
        return (image.getHeight()/image.getWidth())*width;
    }

    protected void addText(String text, float width){
        VisLabel body = new VisLabel(text);
        body.setWrap(true);
        add(body).width(width).padBottom(5f);
    }

    protected void addImage(String path, float width){
        VisImage image = new VisImage(Boot.manager.get(path, Texture.class));


        add(image).width(width-50).height(calcImageHeight(width-50,image)).padBottom(8f);

    }

    protected void addButtons(){
        Table table = new Table();
        table.left();
        table.add(previousButton).padRight(3f);
        table.left();
        table.add(nextButton).padRight(3f);

        VisTextButton close = new VisTextButton("close");

        close.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                tutorialWindow.setVisible(false);
            }
        });

        table.left();
        table.add(close);

        add(table).left();
    }

    public abstract void assignPages(float width);

    public abstract void build(float width);
}
