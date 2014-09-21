package com.arkanoid.game.model;

import com.badlogic.gdx.Gdx;

public interface Constants {
	
	public static final int WORLD_WIDTH = Gdx.graphics.getWidth();
	public static final int WORLD_HEIGHT = Gdx.graphics.getHeight();
	public static final int VAUS_WIDTH = WORLD_WIDTH / 5;	
	public static final int VAUS_HEIGHT = WORLD_HEIGHT / 40;
	public static final int VAUS_Y_POS = WORLD_HEIGHT / 30;
	
	public static final int BRICK_WIDTH = WORLD_WIDTH / 10;	
	public static final int BRICK_HEIGHT = WORLD_HEIGHT / 30;
	
	public static final int BALL_RADIUS = WORLD_WIDTH / 40;
	
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;
	public static final int DEFAULT_LIVES = 3;

}
