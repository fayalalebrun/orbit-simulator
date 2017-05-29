package com.orbit.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kotcrab.vis.ui.VisUI;
import com.orbit.data.UI.*;
import com.orbit.data.entities.Planet;

import java.util.ArrayList;

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
    private PlanetListWindow planetWindow;
    private AngleAdjustmentWindow angleAdjustment;
    private SpeedWindow speedWindow;
    private ArrayList<Planet> planetArrayList;


    private Group ui;

    private GameListener gameListener;
    private UIListener uiListener;

    public static Tool currentTool;
    public static double sizeMultVar = 0.0;
    public static double simSpeed = 1.0f;
    public static final double GRAV = 6.67408 * Math.pow(10,-11);


    public GameScreen(Boot boot) {
        super(boot);

        planetArrayList = new ArrayList<Planet>();

        VisUI.load();
        stage = new Stage(new FitViewport(0.04f,0.03f));
        uiStage = new Stage(new ExtendViewport(800,600));
        ui = new Group();

        planetWindow = new PlanetListWindow();
        gameListener = new GameListener(stage, planetWindow, this);
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
        angleAdjustment = new AngleAdjustmentWindow(options);
        speedWindow = new SpeedWindow();

        toolbar.moveBy(800,0);
        placement.moveBy(350,0);
        sizeMult.moveBy(350,56);
        planetWindow.moveBy(0,270);
        angleAdjustment.moveBy(350,110);
        speedWindow.moveBy(490, 110);
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
        ui.addActor(planetWindow);
        ui.addActor(angleAdjustment);
        ui.addActor(speedWindow);

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

    public boolean placePlanet(float x, float y){
        Actor actor = stage.hit(x,y,true);
        if(actor==null&&!gameListener.isDisabled()){
            Planet p = new Planet(getName(),getRadius(),getMass(),getSpeed(),getAngle(),getColor(),
                    new Vector2(x,y), planetArrayList);
            stage.addActor(p);
            planetWindow.addPlanet(p);
            planetArrayList.add(p);
            return true;
        }
        return false;
    }

    private String getName(){
        return options.getNameField().getText();
    }

    private float getRadius(){
        return Float.parseFloat(options.getRadiusField().getText());
    }

    private float getMass(){
        return Float.parseFloat(options.getMassField().getText());
    }

    private float getSpeed(){
        return Float.parseFloat(options.getSpeedField().getText());
    }

    private float getAngle(){
        return Float.parseFloat(options.getAngleField().getText());
    }

    private Color getColor(){
        return options.getPickerImage().getColor().cpy();
    }
}
