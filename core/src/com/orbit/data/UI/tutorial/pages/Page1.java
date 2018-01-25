package com.orbit.data.UI.tutorial.pages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.orbit.data.Boot;
import com.orbit.data.UI.tutorial.TutorialPage;
import com.orbit.data.UI.tutorial.TutorialWindow;


/**
 * Created by fraayala19 on 1/22/18.
 */
public class Page1 extends TutorialPage {

    public Page1(TutorialWindow tutorialWindow, float width, VisTextButton previousButton, VisTextButton nextButton) {
        super(tutorialWindow, width, previousButton, nextButton);
    }

    @Override
    public void assignPages(float width) {
        nextPage = TutorialWindow.TWO;
    }

    @Override
    public void build(float width) {
        clear();

        addText("To get started, click on the File Button at the top left of the screen.", width);
        row();

        addImage("tutorial/tutFile.png",width);

        row();

        addText("Now, please select the \"Load\" function.",width);
        row();

        addImage("tutorial/tutLoad.png",width);

        row();

        addText("Please search for the \"3D_Solar_System_2017_08_06.txt\" test case and select it. If" +
                "you received the program via DVD, it should be in a folder named \"examples\".",width);

        row();

        addImage("tutorial/tutSelect.png",width);

        row();

        addText("Now click on the button labeled \"Open\".",width);

        row();

        addImage("tutorial/tutOpen.png",width);

        row();

        addText("Congratulations! You have loaded a case.", width);

        row();

    }

    @Override
    public String getPageNumber() {
        return "1";
    }
}
