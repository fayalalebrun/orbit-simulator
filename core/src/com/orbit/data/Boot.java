package com.orbit.data;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Boot extends Game {

	AssetManager manager;

	@Override
	public void create () {
		manager = new AssetManager();

		setScreen(new GameScreen(this));
	}

	public AssetManager getManager(){
		return manager;
	}

}
