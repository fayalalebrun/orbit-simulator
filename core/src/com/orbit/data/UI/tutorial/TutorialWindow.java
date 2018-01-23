package com.orbit.data.UI.tutorial;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.orbit.data.UI.tutorial.pages.Page1;

/**
 * Created by fraayala19 on 1/19/18.
 */
public class TutorialWindow extends VisWindow{

    static float WIDTH = 400f;

    public TutorialWindow() {
        super("Tutorial");
        TableUtils.setSpacingDefaults(this);
        changePage(new Page1(this,WIDTH-50));
        pack();

        setVisible(false);
    }



    public void changePage(TutorialPage page){
        clearChildren();
        VisScrollPane scrollPane = new VisScrollPane(page);
        scrollPane.setFlickScroll(false);
        scrollPane.setFadeScrollBars(false);
        add(scrollPane).spaceTop(30).growX().width(WIDTH).height(300f);
    }

}
