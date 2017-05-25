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
import com.orbit.data.UI.OptionsWindow;
import com.orbit.data.UI.PlacementWindow;
import com.orbit.data.UI.Tool;
import com.orbit.data.UI.Toolbar;
import com.orbit.data.entities.Planet;

/**
 * Created by Fran on 5/22/2017.
 */
public class GameScreen extends BaseScreen {

    Stage stage;

    private Stage uiStage;

    private Planet planet;

    private OptionsWindow options;
    private Toolbar toolbar;
    private PlacementWindow placement;

    private Group ui;

    private GameListener gameListener;
    private UIListener uiListener;

    public static Tool currentTool;

    public GameScreen(Boot boot) {
        super(boot);

        VisUI.load();
        planet = new Planet(50f, 1f, 1f, 1f, Color.BLUE, new Vector2(250f,250f));
        stage = new Stage(new FitViewport(40,30));
        uiStage = new Stage(new ExtendViewport(800,600));
        ui = new Group();
        gameListener = new GameListener(stage);
        uiListener = new UIListener(uiStage);
        currentTool = Tool.MOVE;

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(uiStage);
        multiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(multiplexer);

        placement = new PlacementWindow();
        options = new OptionsWindow(gameListener);
        toolbar = new Toolbar(this.boot.getManager());
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

        stage.addActor(planet);
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
