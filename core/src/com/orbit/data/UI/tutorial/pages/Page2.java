package com.orbit.data.UI.tutorial.pages;

import com.kotcrab.vis.ui.widget.VisTextButton;
import com.orbit.data.UI.tutorial.TutorialPage;
import com.orbit.data.UI.tutorial.TutorialWindow;

/**
 * Created by fraayala19 on 1/23/18.
 */
public class Page2 extends TutorialPage{

    public Page2(TutorialWindow tutorialWindow, float width, VisTextButton previousButton, VisTextButton nextButton) {
        super(tutorialWindow, width, previousButton, nextButton);
    }

    @Override
    public void assignPages(float width) {
        previousPage = TutorialWindow.ONE;
        nextPage = TutorialWindow.THREE;
    }

    @Override
    public void build(float width) {
        clear();

        addText("You probably won't be able to see much from this distance. To make the camera focus on an object " +
                "you must click \"Go\" on any planet in the \"Planets\" window. Try this with the sun.", width);

        row();

        addImage("tutorial/tutList.png",width);

        row();

        addText("We have a problem now. The camera is locked to the object and we can not move it. Please repeat what you " +
                "have just done, and click the Sun's \"Go\" button again.",width);

        row();

        addText("To move the camera around you have two options. To move it very small amounts, you can use the arrow keys, " +
                "however to pan the camera you must click on the moving arrow tool in the toolbar",width);

        row();

        addImage("tutorial/tutTools.png",width);

        row();

        addText("Now you can move the camera by clicking anywhere on the viewport and dragging the mouse. Try it!", width);

        row();

    }
    @Override
    public String getPageNumber() {
        return "2";
    }
}
