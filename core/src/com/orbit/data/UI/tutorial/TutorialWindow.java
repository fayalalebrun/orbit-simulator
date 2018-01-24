package com.orbit.data.UI.tutorial;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.orbit.data.UI.tutorial.pages.Page1;
import com.orbit.data.UI.tutorial.pages.Page2;
import com.orbit.data.UI.tutorial.pages.Page3;

/**
 * Created by fraayala19 on 1/19/18.
 */
public class TutorialWindow extends VisWindow{

    private static float WIDTH = 400f;

    public static TutorialPage ONE, TWO, THREE;

    public TutorialWindow() {
        super("Tutorial");

        ONE = new Page1(this, WIDTH-50);
        TWO = new Page2(this, WIDTH-50);
        THREE = new Page3(this,WIDTH-50);

        TableUtils.setSpacingDefaults(this);
        changePage(ONE);
        pack();

        setVisible(false);
    }



    public void changePage(TutorialPage page){
        page.assignPages(WIDTH-50);
        page.setUpButtons();
        page.build(WIDTH-50);


        clearChildren();
        VisScrollPane scrollPane = new VisScrollPane(page);
        scrollPane.setFlickScroll(false);
        scrollPane.setFadeScrollBars(false);
        add(scrollPane).spaceTop(30).growX().width(WIDTH).height(300f);
    }

}
