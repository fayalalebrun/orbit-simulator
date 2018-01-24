package com.orbit.data.UI.tutorial.pages;

import com.orbit.data.UI.tutorial.TutorialPage;
import com.orbit.data.UI.tutorial.TutorialWindow;

/**
 * Created by fraayala19 on 1/24/18.
 */
public class Page4 extends TutorialPage{

    public Page4(TutorialWindow tutorialWindow, float width) {
        super(tutorialWindow, width);
    }

    @Override
    public void assignPages(float width) {
        previousPage = TutorialWindow.THREE;
    }

    @Override
    public void build(float width) {
        clear();

        addText("Right now the simulation is moving in real time, meaning that while the planets are moving they are " +
                "doing so very slowly.", width);

        row();

        addText("To change this we must change the simulation speed. Go to the window labeled \"Simulation Speed\", and set " +
                "the speed to \"1000000\". Now, click on the \"set\" button to apply the change",width);

        row();

        addImage("tutorial/tutSim.png",width);

        row();

        addText("That's better! Finally some movement. However, the orbit tracing is looking quite rough:", width);

        row();

        addImage("tutorial/tutOrb.png",width);

        row();

        addText("To fix this, let's have a look at the \"Orbit Tracing\" window.", width);

        row();

        addImage("tutorial/tutTrace.png",width);

        row();

        addText("The \"Interval\" field denotes how many seconds will pass before a point is plotted, and the " +
                "\"Max Points\" field denotes how many points will be stored before the trailing points are deleted. " +
                "The lower the interval and the higher the max points the better it will look, however performance will " +
                "suffer.",width);

        row();

        addText("Type \"0.1\" into the \"Interval\" field and \"600\" into the \"Max Points\" field. Click the \"" +
                "Set\" button to apply your changes.", width);

        row();

        addImage("tutorial/tutTrace1.png",width);

        row();

        addText("Much better! Have a look at those beautiful orbits!", width);

        row();

        addImage("tutorial/tutOrb1.png",width);

        row();

        addText("Excellent! You now know all you need to in order to properly view a simulation. Next, we will see how " +
                "you can modify, create and save your own simulations.", width);

        row();

        addButtons();
    }
}
