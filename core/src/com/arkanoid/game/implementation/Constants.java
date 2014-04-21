package com.arkanoid.game.implementation;

import com.arkanoid.game.model.level.LevelManager;

public interface Constants {
	int TICKS_PER_SECOND = 10;
	int GAME_WIDTH = 798;
	int GAME_HEIGHT = 600;
	int BALL_RADIUS = 8;
	float BALL_SPEED = 3;
	int BONUS_SPEED = 2;
	int VAUS_SPEED = 8;
	int VAUS_HEIGHT = 20;
	int VAUS_Y = GAME_HEIGHT - 40;
	int VAUS_WIDTH = 100;
	int LONGVAUS_WIDTH = 150;
	int SHORTVAUS_WIDTH = 65;
	int FIELD_WIDTH = 798;
	int FIELD_HEIGHT = 525;
	int FIELD_COLUMNS = 14;
	int FIELD_ROWS = 21;
	int BRICK_WIDTH = 57;
	int BRICK_HEIGHT = 25;
	
	int BALL_LIFE = 20;
	
	int INSTANTANEOUS = 0;
	
	int MAX_LEVEL = LevelManager.getMaxLevel();
}
