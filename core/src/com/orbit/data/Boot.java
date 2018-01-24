package com.orbit.data;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;

public class Boot extends Game {

	public static AssetManager manager;

	@Override
	public void create () {
		Gdx.graphics.setResizable(true);
		Gdx.graphics.setWindowedMode(800,600);
		Gdx.graphics.setTitle("Orbit Simulator");
		Gdx.graphics.setVSync(true);

		manager = new AssetManager();

		FileHandle dirHandle = Gdx.files.internal("./Planet Textures/");

		for(FileHandle f : dirHandle.list()){
			if(f.extension().equals("jpg")||f.extension().equals("png")) {
				manager.load(f.file().getPath(), Texture.class);
			}
		}


		manager.load("pointer.png", Texture.class);
		manager.load("move.png", Texture.class);
		manager.load("zoom-in.png", Texture.class);
		manager.load("zoom-out.png", Texture.class);
		manager.load("stars_milky_way.jpg", Texture.class);
		manager.load("tutorial/tutFile.png", Texture.class);
		manager.load("tutorial/tutLoad.png", Texture.class);
		manager.load("tutorial/tutSelect.png", Texture.class);
		manager.load("tutorial/tutOpen.png", Texture.class);
		manager.load("tutorial/tutList.png", Texture.class);
		manager.load("tutorial/tutTools.png", Texture.class);
		manager.load("tutorial/tutMag.png", Texture.class);
		manager.load("tutorial/tutSun.png", Texture.class);
		manager.load("tutorial/tutGlobMag.png", Texture.class);
		manager.load("tutorial/tutView.png", Texture.class);
		manager.load("tutorial/tutVenus.png", Texture.class);
		manager.load("tutorial/tutSim.png", Texture.class);
		manager.load("tutorial/tutOrb.png", Texture.class);
		manager.load("tutorial/tutTrace.png", Texture.class);
		manager.load("tutorial/tutTrace1.png", Texture.class);
		manager.load("tutorial/tutOrb1.png", Texture.class);
		manager.load("tutorial/tutPlanOpt.png", Texture.class);
		manager.load("tutorial/tutPlanOpt1.png", Texture.class);
		manager.load("tutorial/tutAdjust.png", Texture.class);
		manager.load("tutorial/tutAdjust1.png", Texture.class);
		manager.load("tutorial/tutPlanOpt2.png", Texture.class);
		manager.load("tutorial/tutColor.png", Texture.class);
		manager.load("tutorial/tutPlanOpt3.png", Texture.class);
		manager.load("tutorial/tutPointer.png", Texture.class);
		manager.load("tutorial/tutHole.png", Texture.class);
		manager.load("tutorial/tutSpec.png", Texture.class);
		manager.load("tutorial/tutDel.png", Texture.class);
		manager.load("tutorial/tutSave.png", Texture.class);
		manager.load("tutorial/tutSaveSel.png", Texture.class);

		manager.finishLoading();

		setScreen(new GameScreen(this));
	}

	public static AssetManager getManager(){
		return manager;
	}

}
