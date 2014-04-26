package com.arkanoid.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Vaus {
	
	public final Vector2 position;
	public final Rectangle bounds;
	public final Vector2 velocity;
	public final Vector2 accel;

	static final int VAUS_HEIGHT = 5;
	static final int VAUS_WIDTH = 20;
	public static final float VAUS_VELOCITY = 5f;
	
	public Vaus(float x, float y) {
		this.position = new Vector2(x, y);
		this.bounds = new Rectangle(x - VAUS_WIDTH / 2, y - VAUS_HEIGHT / 2, VAUS_WIDTH, VAUS_HEIGHT);
		velocity = new Vector2();
		accel = new Vector2();
	}

	public void update(float deltaTime, float vausMoveX) {
		
		if (position.x <= 0) {
			position.x = position.x + 0.1f;
			return;
		}
		if (position.x >= GameField.WORLD_WIDTH - VAUS_WIDTH) {
			position.x = position.x - 0.1f;
			return;
		}		
		position.add(vausMoveX * deltaTime * 3, 0);
		bounds.x = position.x - bounds.width / 2;
		bounds.y = position.y - bounds.height / 2;		
	}	
}
