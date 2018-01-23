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

		manager.finishLoading();

		setScreen(new GameScreen(this));
	}

	public static AssetManager getManager(){
		return manager;
	}

}
