package com.orbit.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.*;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileTypeFilter;
import com.kotcrab.vis.ui.widget.file.SingleFileChooserListener;
import com.orbit.data.nBodyAlgorithms.BruteForce;
import com.orbit.data.nBodyAlgorithms.NBodyAlgorithm;
import com.orbit.data.UI.*;
import com.orbit.data.entities.OrbitManager;
import com.orbit.data.entities.Planet;
import com.orbit.data.nBodyAlgorithms.ZBruteForce;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Fran on 5/22/2017.
 */
public class GameScreen extends BaseScreen {

    private NBodyAlgorithm algorithm;

    Stage stage;

    private Stage uiStage;

    private MenuBar menuBar;

    private OptionsWindow options;
    private Toolbar toolbar;
    private PlacementWindow placement;
    private SizeMultWindow sizeMult;
    private PlanetListWindow planetWindow;
    private AngleAdjustmentWindow angleAdjustment;
    private SpeedWindow speedWindow;
    private OrbitTracingWindow orbitWindow;
    private Vector<Planet> planetArrayList;

    public OrbitManager orbitManager;

    FileChooser fileChooser;

    private VisTable ui;
    private Group uiGroup;

    private GameListener gameListener;
    private UIListener uiListener;

    public static Tool currentTool;
    public static double sizeMultVar = 0.0;
    public volatile static double simSpeed = 1.0f;


    FPSLogger logger =new FPSLogger();

    public GameScreen(Boot boot) {
        super(boot);

        planetArrayList = new Vector<Planet>();

        VisUI.load();
        stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth()*0.00004f,Gdx.graphics.getHeight()*0.00004f));
        uiStage = new Stage(new ExtendViewport(800,600));
        ui = new VisTable();
        ui.setFillParent(true);
        TableUtils.setSpacingDefaults(ui);
        uiGroup = new Group();

        orbitManager = new OrbitManager();

        planetWindow = new PlanetListWindow(this);
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
        orbitWindow = new OrbitTracingWindow(orbitManager);

        menuBar = new MenuBar();

        fileChooser = new FileChooser(FileChooser.Mode.OPEN);
        setupFileChooser();

        algorithm = new ZBruteForce(this);

        toolbar.moveBy(750,0);
        placement.moveBy(345,0);
        sizeMult.moveBy(345,185);
        planetWindow.moveBy(0,270);
        angleAdjustment.moveBy(345,56);
        speedWindow.moveBy(700, 185);
        orbitWindow.moveBy(485, 56);
    }

    @Override
    public void show() {
        stage.addListener(gameListener);
        ui.addListener(uiListener);

        stage.addActor(orbitManager);

        uiStage.addActor(ui);
        uiStage.addActor(uiGroup);

        ui.add(menuBar.getTable()).expandX().fillX().row();
        ui.add().expand().fill();

        uiGroup.addActor(options);
        uiGroup.addActor(toolbar);
        uiGroup.addActor(placement);
        uiGroup.addActor(sizeMult);
        uiGroup.addActor(planetWindow);
        uiGroup.addActor(angleAdjustment);
        uiGroup.addActor(speedWindow);
        uiGroup.addActor(orbitWindow);

        createMenus();

        (new Thread(algorithm)).start();
    }

    private void setupFileChooser(){
        FileChooser.setDefaultPrefsName("orbit-simulator");

        fileChooser.setSelectionMode(FileChooser.SelectionMode.FILES);
        final FileTypeFilter fileTypeFilter = new FileTypeFilter(true);
        fileTypeFilter.addRule("Text files (*.txt)","txt");

        fileChooser.setFileTypeFilter(fileTypeFilter);

        fileChooser.setListener(new SingleFileChooserListener() {
            @Override
            protected void selected(FileHandle file) {
                if(fileChooser.getMode()== FileChooser.Mode.OPEN){
                    SaveFileManager.loadGame(returnSelf(), file);
                } else if(fileChooser.getMode()== FileChooser.Mode.SAVE){
                    SaveFileManager.saveGame(returnSelf(), file);
                }
            }
        });
    }

    private void createMenus(){
        Menu windowMenu = new Menu("Window");

        windowMenu.addItem(createWindowToggle("Planets", planetWindow));
        windowMenu.addItem(createWindowToggle("Specific Placement", placement));
        windowMenu.addItem(createWindowToggle("Planetary Options", options));
        windowMenu.addItem(createWindowToggle("Global Magnification", sizeMult));
        windowMenu.addItem(createWindowToggle("Angle Adjustment", angleAdjustment));
        windowMenu.addItem(createWindowToggle("Simulation Speed", speedWindow));
        windowMenu.addItem(createWindowToggle("Tools", toolbar));
        windowMenu.addItem(createWindowToggle("Orbit Tracing", orbitWindow));

        Menu fileMenu = new Menu("File");

        createFileMenuItems(fileMenu);

        menuBar.addMenu(fileMenu);
        menuBar.addMenu(windowMenu);
    }

    private MenuItem createWindowToggle(String name, final VisWindow window){
        final MenuItem item = new MenuItem(name);
        item.setText(item.getText()+" [X]");

        item.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StringBuilder text = (StringBuilder)item.getText();
                text.deleteCharAt(text.length-2);

                if(window.isVisible()){
                    text.insert(text.length-1, ".");
                } else {
                    text.insert(text.length-1, "X");
                }

                item.setText(text);
                window.setVisible(!window.isVisible());
            }
        });

        return  item;
    }

    private void createFileMenuItems(Menu fileMenu){
        MenuItem load = new MenuItem("Load");

        load.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                fileChooser.setMode(FileChooser.Mode.OPEN);
                uiStage.addActor(fileChooser.fadeIn());
            }
        });

        MenuItem save = new MenuItem("Save");

        save.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                fileChooser.setMode(FileChooser.Mode.SAVE);
                uiStage.addActor(fileChooser.fadeIn());
            }
        });

        fileMenu.addItem(load);
        fileMenu.addItem(save);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        uiStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));


        logger.log();
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
                    x, y);
            addPlanet(p);
            return true;
        }
        return false;
    }

    public void addPlanet(Planet p){
        synchronized (planetArrayList) {
            stage.addActor(p);
            planetWindow.addPlanet(p);
            planetArrayList.add(p);
            orbitManager.addOrbit(p);
        }
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

    public void removePlanets(Vector<Planet> planets){
        synchronized (planets) {
            for (Planet p : planets) {
                p.remove();
                planetWindow.removePlanet(p);
            }

            this.planetArrayList.removeAll(planets);
        }
    }

    public Vector<Planet> getPlanetArrayList() {
        return planetArrayList;
    }

    public GameScreen returnSelf(){
        return this;
    }

    public void hideUI(){
        uiGroup.setVisible(!uiGroup.isVisible());
    }
}
