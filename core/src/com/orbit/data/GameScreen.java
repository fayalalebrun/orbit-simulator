package com.orbit.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.orbit.data.UI.OptionsWindow;
import com.orbit.data.entities.Planet;

/**
 * Created by Fran on 5/22/2017.
 */
public class GameScreen extends BaseScreen {

    Stage stage;

    Planet planet;

    VisTable table;

    OptionsWindow options;

    public GameScreen(Boot boot) {
        super(boot);
        Gdx.input.setInputProcessor(stage);
        VisUI.load();
        planet = new Planet(50f, 1f, 1f, 1f, Color.BLUE, new Vector2(250f,250f));
        stage = new Stage(new FitViewport(800,600));
        table = new VisTable(true);
        table.setFillParent(true);
        options = new OptionsWindow();
    }

    @Override
    public void show() {

        stage.addActor(planet);
        stage.addActor(table);
        table.addActor(options);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        if(width==0&&height==0){
            return;
        }
        stage.getViewport().update(width,height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        VisUI.dispose();
    }
}
