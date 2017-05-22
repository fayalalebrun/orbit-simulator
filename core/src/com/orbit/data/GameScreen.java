package com.orbit.data;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by Fran on 5/22/2017.
 */
public class GameScreen extends BaseScreen {

    Stage stage;

    public GameScreen(Boot boot) {
        super(boot);

        stage = new Stage(new FitViewport(800,600));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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

    }
}
