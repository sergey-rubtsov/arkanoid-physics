package com.arkanoid.game;

import com.arkanoid.game.view.MainMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;


public class Arkanoid extends Game {

	boolean firstTimeCreate = true;
	FPSLogger fps;
	
	@Override
	public void create () {
		Settings.load();
		Assets.load();
		setScreen(new MainMenuScreen(this));
		fps = new FPSLogger();
	}

	@Override
	public void render () {

	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
}
