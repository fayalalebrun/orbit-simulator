package com.orbit.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kotcrab.vis.ui.VisUI;
import com.orbit.data.UI.*;
import com.orbit.data.entities.Planet;

/**
 * Created by Fran on 5/22/2017.
 */
public class GameScreen extends BaseScreen {

    Stage stage;

    private Stage uiStage;

    private OptionsWindow options;
    private Toolbar toolbar;
    private PlacementWindow placement;
    private SizeMultWindow sizeMult;

    private Group ui;

    private GameListener gameListener;
    private UIListener uiListener;

    public static Tool currentTool;

    public GameScreen(Boot boot) {
        super(boot);

        VisUI.load();
        stage = new Stage(new FitViewport(0.04f,0.03f));
        uiStage = new Stage(new ExtendViewport(800,600));
        ui = new Group();
        gameListener = new GameListener(stage);
        uiListener = new UIListener(uiStage);
        currentTool = Tool.MOVE;

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(uiStage);
        multiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(multiplexer);

        placement = new PlacementWindow(gameListener);
        options = new OptionsWindow(gameListener);
        toolbar = new Toolbar(this.boot.getManager());
        sizeMult = new SizeMultWindow();
        toolbar.moveBy(800,400);
    }

    @Override
    public void show() {
        stage.addListener(gameListener);
        ui.addListener(uiListener);

        uiStage.addActor(ui);

        ui.addActor(options);
        ui.addActor(toolbar);
        ui.addActor(placement);
        ui.addActor(sizeMult);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        uiStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));

        stage.draw();
        uiStage.draw();
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
