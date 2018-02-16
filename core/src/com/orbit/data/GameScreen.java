package com.orbit.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.*;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileTypeFilter;
import com.kotcrab.vis.ui.widget.file.SingleFileChooserListener;
import com.orbit.data.UI.tutorial.HelpWindow;
import com.orbit.data.UI.tutorial.TutorialWindow;
import com.orbit.data.nBodyAlgorithms.NBodyAlgorithm;
import com.orbit.data.UI.*;
import com.orbit.data.entities.OrbitManager;
import com.orbit.data.entities.Planet;
import com.orbit.data.nBodyAlgorithms.VelocityVerlet;

import java.awt.*;
import java.net.URI;
import java.util.Vector;

/**
 * Created by Fran on 5/22/2017.
 */
public class GameScreen extends BaseScreen {

    private NBodyAlgorithm algorithm;

    private Stage stage;

    private Stage uiStage;

    private Stage backgroundStage;

    private MenuBar menuBar;

    private OptionsWindow options;
    private Toolbar toolbar;
    private PlacementWindow placement;
    private SizeMultWindow sizeMult;
    private PlanetListWindow planetWindow;
    private AngleAdjustmentWindow angleAdjustment;
    private SpeedWindow speedWindow;
    private OrbitTracingWindow orbitWindow;
    private CreditsWindow creditsWindow;
    private HelpWindow helpWindow;

    private TutorialWindow tutorialWindow;

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

    Thread algorithmThread;

    FPSLogger logger =new FPSLogger();

    public GameScreen(Boot boot) {
        super(boot);

        planetArrayList = new Vector<Planet>();

        VisUI.load();
        stage = new Stage(new ScreenViewport());
        uiStage = new Stage(new ScreenViewport());
        backgroundStage = new Stage(new ScreenViewport());
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
        creditsWindow = new CreditsWindow();
        helpWindow = new HelpWindow();
        tutorialWindow = new TutorialWindow();


        menuBar = new MenuBar();

        fileChooser = new FileChooser(FileChooser.Mode.OPEN);
        setupFileChooser();

        algorithm = new VelocityVerlet(this);

        Image backgroundImage = new Image(Boot.getManager().get("stars_milky_way.jpg", Texture.class));
        backgroundImage.setSize(Gdx.graphics.getDisplayMode().width,Gdx.graphics.getDisplayMode().height);

        backgroundStage.addActor(backgroundImage);

        toolbar.moveBy(750,0);
        placement.moveBy(345,0);
        sizeMult.moveBy(345,185);
        planetWindow.moveBy(0,270);
        angleAdjustment.moveBy(345,56);
        speedWindow.moveBy(700, 185);
        orbitWindow.moveBy(485, 56);
        creditsWindow.moveBy(300,400);
        helpWindow.moveBy(300, 400);
    }

    @Override
    public void show() {
        stage.addListener(gameListener);
        ui.addListener(uiListener);


        stage.addActor(orbitManager);

        uiStage.addActor(ui);
        uiStage.addActor(uiGroup);
        ui.left().top();
        ui.add(menuBar.getTable()).fillX().expandX().row();

        uiGroup.addActor(options);
        uiGroup.addActor(toolbar);
        uiGroup.addActor(placement);
        uiGroup.addActor(sizeMult);
        uiGroup.addActor(planetWindow);
        uiGroup.addActor(angleAdjustment);
        uiGroup.addActor(speedWindow);
        uiGroup.addActor(orbitWindow);
        uiGroup.addActor(creditsWindow);
        uiGroup.addActor(tutorialWindow);
        uiGroup.addActor(helpWindow);

        createMenus();

        algorithmThread = new Thread(algorithm);
        algorithmThread.start();
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

        Menu aboutMenu = new Menu("About");
        Menu tutorialMenu = new Menu("Tutorials");

        createAboutMenuItems(aboutMenu);
        createTutorialMenuItems(tutorialMenu);

        menuBar.addMenu(fileMenu);
        menuBar.addMenu(windowMenu);
        menuBar.addMenu(tutorialMenu);
        menuBar.addMenu(aboutMenu);

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

    private void createTutorialMenuItems(Menu tutorialMenu){
        MenuItem tutorial = new MenuItem("Tutorial");
        MenuItem help = new MenuItem("Help");
        MenuItem videoTutorial = new MenuItem("Video guide");

        videoTutorial.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if (Gdx.graphics.isFullscreen()){
                    toggleFullscreen();
                }

                try {
                    Desktop desktop = java.awt.Desktop.getDesktop();
                    URI oURL = new URI("https://rickrolled.fr/");
                    //desktop.browse(oURL);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        help.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                helpWindow.setVisible(true);
            }
        });

        tutorial.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                tutorialWindow.setVisible(true);
            }
        });

        tutorialMenu.addItem(tutorial);
        tutorialMenu.addItem(videoTutorial);
        tutorialMenu.addItem(help);

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

    private void createAboutMenuItems(Menu aboutMenu){

        MenuItem credits = new MenuItem("Credits");


        credits.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                creditsWindow.setVisible(true);
            }
        });



        aboutMenu.addItem(credits);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        uiStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        backgroundStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));


        logger.log();
        backgroundStage.draw();
        stage.draw();
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        if(width==0&&height==0){
            return;
        }
        stage.getViewport().update(width,height,false);
        uiStage.getViewport().update(width,height, true);
        backgroundStage.getViewport().update(width,height,true);

        ui.clearChildren();



        ui.add(menuBar.getTable()).fillX().expandX().row();
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
        uiStage.dispose();
        VisUI.dispose();
        algorithmThread.interrupt();

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
        Vector<Planet> list = new Vector<Planet>();
        list.add(p);
        addPlanet(list);
    }

    public void addPlanet(Vector<Planet> planetList){
        synchronized (planetArrayList){
            for(Planet p : planetList){
                stage.addActor(p);
                planetWindow.addPlanet(p);
                planetArrayList.add(p);
                orbitManager.addOrbit(p);
            }
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

    public void toggleFullscreen(){
        if(!Gdx.graphics.isFullscreen()){
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        } else {
            Gdx.graphics.setWindowedMode(800,600);
        }
    }
}
