package com.arkanoid.game.model;

import com.arkanoid.game.implementation.Constants;

/**
 * This class contains information about a ball.
 * 
 */

public class Ball implements Constants {
	protected float x;
	protected float y;
	protected float speedX;
	protected float speedY;
	protected float speed;

	public Ball() {

	}

	/**
	 * For test purposes only
	 * 
	 * @return
	 */
	public final String toStringTest() {
		return x + " " + y + " " + speedX + " " + speedY;
	}

}
