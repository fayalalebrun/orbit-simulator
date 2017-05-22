package com.orbit.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.orbit.data.entities.Planet;

/**
 * Created by Fran on 5/22/2017.
 */
public class GameScreen extends BaseScreen {

    Stage stage;

    Planet planet;

    public GameScreen(Boot boot) {
        super(boot);
        planet = new Planet(50f, 1f, 1f, 1f, Color.BLUE, new Vector2(250f,250f));
        stage = new Stage(new FitViewport(800,600));
    }

    @Override
    public void show() {
        stage.addActor(planet);
    }

    @Override
    public void render(float delta) {
        stage.act();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
    }
}
