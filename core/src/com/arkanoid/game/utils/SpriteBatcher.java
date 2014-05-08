package com.arkanoid.game.utils;

import java.util.List;

import com.arkanoid.game.Assets;
import com.arkanoid.game.model.Ball;
import com.arkanoid.game.model.Brick;
import com.arkanoid.game.model.GameField;
import com.arkanoid.game.model.PhysicalObject;
import com.arkanoid.game.model.Vaus;
import com.arkanoid.game.view.GameScreen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class SpriteBatcher extends SpriteBatch {
	
	public SpriteBatcher() {
	}
	
	public void renderBackground() {
		disableBlending();
		begin();
		draw(Assets.backgroundRegion, 0, 0, GameField.WORLD_WIDTH,
				GameField.WORLD_HEIGHT);
		end();
	}
	
	public void renderVaus(float x, float y, float width, float height) {
		enableBlending();
		TextureRegion vausFrame = Assets.defaultVaus;		
		begin();
		draw(vausFrame, x, y, width, height);
		end();	
	}
	
	public void renderBall(Ball ball) {
		enableBlending();
		TextureRegion vausFrame = Assets.defaultBall;		
		begin();
		draw(vausFrame, ball.getBottomLeftXPos(), ball.getBottomLeftYPos(), ball.getRadius() * 2, ball.getRadius() * 2);
		end();		
	}
	
	public void renderBricks(List<Brick> bricks) {
		for (Brick brick : bricks) {
			renderBrick(brick);
		}
	}
	
	public void renderBrick(Brick brick) {
		enableBlending();
		TextureRegion vausFrame = Assets.defaultBrick;		
		begin();
		draw(vausFrame, brick.getBottomLeftXPos(), brick.getBottomLeftYPos(), brick.width, brick.height);
		end();		
	}
	
	public void renderVaus(Vaus vaus) {
		enableBlending();
		TextureRegion vausFrame = Assets.defaultVaus;		
		begin();
		draw(vausFrame, vaus.getBottomLeftXPos(), vaus.getBottomLeftYPos(), vaus.width, vaus.height);
		end();	
	}
	
	public void presentReady() {
		draw(Assets.ready, 160 - 192 / 2, 240 - 32 / 2, 192, 32);
	}

	public void presentRunning() {
		draw(Assets.pause, 320 - 64, 480 - 64, 64, 64);
	}

	public void presentPaused() {
		draw(Assets.pauseMenu, 160 - 192 / 2, 240 - 96 / 2, 192, 96);
		//Assets.font.draw(this, "test", 16, 480 - 20);
	}

	public void presentLevelEnd() {

	}

	public void presentGameOver () {
		draw(Assets.gameOver, 160 - 160 / 2, 240 - 96 / 2, 160, 96);
	}
}
