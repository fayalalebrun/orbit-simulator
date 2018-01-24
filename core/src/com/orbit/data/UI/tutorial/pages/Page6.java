package com.orbit.data.UI.tutorial.pages;

import com.orbit.data.UI.tutorial.TutorialPage;
import com.orbit.data.UI.tutorial.TutorialWindow;

/**
 * Created by fraayala19 on 1/24/18.
 */
public class Page6 extends TutorialPage{

    public Page6(TutorialWindow tutorialWindow, float width) {
        super(tutorialWindow, width);
    }

    @Override
    public void assignPages(float width) {
        previousPage = TutorialWindow.FIVE;
    }

    @Override
    public void build(float width) {
        clear();

        addText("To place an object into the simulation, we have two options. The first option is to place it with the " +
                "mouse, and the second is to use specific placement.", width);

        row();

        addText("First, we will see how to place our black hole with the mouse. First, select the pointer option in the " +
                "toolbar.", width);

        row();

        addImage("tutorial/tutPointer.png",width/4);

        row();

        addText("Now, click wherever you want the black hole to be placed. Instantly, you can see the results!", width);

        row();

        addImage("tutorial/tutHole.png",width);

        row();

        addText("The second option is with the \"Specific Placement\" window. Suppose you wanted to place a planet 1 AU" +
                "(The distance from the earth to the sun) " +
                "from the origin(In the case of the solar system, where the sun is). Then, input the position into the \"x\" " +
                "and \"y\" fields and click the \"Place\" button",width);

        row();

        addImage("tutorial/tutSpec.png",width);

        row();

        addText("Suppose you wanted to delete one of the planets. You can do this at any time by going into the " +
                "\"Planets\" window and looking for the planet, then hitting the \"Del\" button next to it",width);

        row();

        addImage("tutorial/tutDel.png",width);

        row();

        addText("This is a most beautiful case you have created! To save it, first open the file menu at the top" +
                " left of the screen.", width);

        row();

        addImage("tutorial/tutFile.png",width);

        row();

        addText("Now, click on \"Save\"",width);

        row();

        addImage("tutorial/tutSave.png",width);

        row();

        addText("Use the file browser to select where you would want to save your case and what name you would like to " +
                "give it.",width);

        row();

        addImage("tutorial/tutSaveSel.png",width);

        row();

        addText("Congratulations! You have completed the tutorial.", width);

        row();

        addButtons();
    }
}
