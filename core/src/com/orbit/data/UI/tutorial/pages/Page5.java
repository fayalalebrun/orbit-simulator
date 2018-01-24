package com.orbit.data.UI.tutorial.pages;

import com.orbit.data.UI.tutorial.TutorialPage;
import com.orbit.data.UI.tutorial.TutorialWindow;

/**
 * Created by fraayala19 on 1/24/18.
 */
public class Page5 extends TutorialPage {

    public Page5(TutorialWindow tutorialWindow, float width) {
        super(tutorialWindow, width);
    }

    @Override
    public void assignPages(float width) {
        previousPage = TutorialWindow.FOUR;
    }

    @Override
    public void build(float width) {
        clear();

        addText("So let's pretend we wanted to see what would happen if a black hole suddenly appeared in our" +
                "solar system. To achieve this, we must first take a look at the \"Planetary Options\" window.", width);

        row();

        addImage("tutorial/tutPlanOpt.png",width);

        row();

        addText("\"Name\", this refers to the identifier given to this object, which will help you identify it in " +
                "the planet list.", width);

        row();

        addText("\"Radius\", this field refers to the radius of the new object, given in kilometers.", width);

        row();

        addText("\"Mass\", this field refers to the amount of mass of the objects, given in earth masses. For example" +
                " the earth would have a mass of 1.", width);

        row();

        addText("\"Speed\", this field refers to how fast the object is moving, given in kilometers per second.", width);

        row();

        addText("\"Angle\", this is the angle at which the object is moving. 0 degrees point north, and they increase" +
                " clockwise.",width);

        row();

        addText("\"Color\" is the field with which to select which color the body will appear as. If you press the " +
                "\"Color\" button, a color wheel will appear, allowing you to select your color of choice.", width);

        row();

        addText("Now, set the \"Name\" field to \"Black Hole\", the \"Radius\" field to \"695000\" and the \"Mass\" " +
                "field to \"5000000\"",width);

        row();

        addImage("tutorial/tutPlanOpt1.png",width);

        row();

        addText("We could now set the speed and angle manually, however we have a tool to do it for us, in the form " +
                "of the \"Angle Adjustment\" window. Lets have a look at it: ", width);

        row();

        addImage("tutorial/tutAdjust.png",width);

        row();

        addText("If you click anywhere on the black circle, a green line will appear. It's direction will determine " +
                "the angle, and its length will speed.", width);

        row();

        addText("Lets try clicking somewhere pointing to about three o' clock.", width);

        row();

        addImage("tutorial/tutAdjust1.png",width);

        row();

        addText("Now, your \"Planetary Options\" window should look as follows:",width);

        row();

        addImage("tutorial/tutPlanOpt2.png",width);

        row();

        addText("Click on the \"Color\" button. Using the color wheel, select the white color, so that we may see the " +
                "black hole in front of the black background.",width);

        row();

        addImage("tutorial/tutColor.png",width);

        row();

        addText("Click on the \"OK\" button, and your \"Planetary Options\" window should look like this: ",width);

        row();

        addImage("tutorial/tutPlanOpt3.png",width);

        row();

        addText("Next we will see how we can place this body into the simulation.",width);

        row();

        addButtons();
    }
}
