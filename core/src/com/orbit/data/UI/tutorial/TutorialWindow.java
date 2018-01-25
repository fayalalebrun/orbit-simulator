package com.orbit.data.UI.tutorial;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.orbit.data.UI.tutorial.pages.*;

/**
 * Created by fraayala19 on 1/19/18.
 */
public class TutorialWindow extends VisWindow{

    private static float WIDTH = 400f;

    public static TutorialPage ONE, TWO, THREE, FOUR, FIVE, SIX;

    private VisTextButton previousButton;
    private VisTextButton nextButton;
    private TutorialPage currentPage;

    public TutorialWindow() {
        super("Tutorial");

        previousButton = new VisTextButton("previous");
        previousButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                changePage(currentPage.getPreviousPage());
            }
        });

        nextButton = new VisTextButton("next");
        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                changePage(currentPage.getNextPage());
            }
        });

        ONE = new Page1(this, WIDTH-50, previousButton, nextButton);
        TWO = new Page2(this, WIDTH-50, previousButton, nextButton);
        THREE = new Page3(this,WIDTH-50, previousButton, nextButton);
        FOUR = new Page4(this,WIDTH-50, previousButton, nextButton);
        FIVE = new Page5(this, WIDTH-50, previousButton, nextButton);
        SIX = new Page6(this,WIDTH-50, previousButton, nextButton);

        TableUtils.setSpacingDefaults(this);
        currentPage = ONE;
        changePage(ONE);
        pack();

        setVisible(false);
    }



    public void changePage(TutorialPage page){
        currentPage = page;
        page.assignPages(WIDTH-50);
        page.setUpButtons();
        page.build(WIDTH-50);

        Table upperToolbar = new Table();

        VisLabel pageLabel = new VisLabel(("page "+page.getPageNumber()+" / 6"));

        upperToolbar.add(pageLabel).padRight(50f);
        upperToolbar.left();

        VisTextButton pdfButton = new VisTextButton("Open in pdf");
        upperToolbar.add(pdfButton);



        Table table = new Table();
        table.left();
        table.add(previousButton).padRight(3f);
        table.left();
        table.add(nextButton).padRight(3f);

        VisTextButton close = new VisTextButton("close");

        close.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setVisible(false);
            }
        });

        table.left();
        table.add(close);


        add(table).left();


        clearChildren();
        VisScrollPane scrollPane = new VisScrollPane(page);
        scrollPane.setFlickScroll(false);
        scrollPane.setFadeScrollBars(false);

        add(upperToolbar);
        row();
        add(scrollPane).growX().width(WIDTH).height(300f);
        row();
        add(table);
    }

}
