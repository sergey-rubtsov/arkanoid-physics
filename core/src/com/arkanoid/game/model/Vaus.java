package com.arkanoid.game.model;

import com.arkanoid.game.implementation.Constants;

public class Vaus implements Constants {
	protected int posX;
	private int moveLeft;
	private int moveRight;
	protected int vausWidth;

	public Vaus(int posX) {
		this.posX = posX;
		vausWidth = VAUS_WIDTH;
	}
	
	public Vaus() {
		this.posX = 0;
		vausWidth = VAUS_WIDTH;		
	}

	// getters and setters
	public final void setX(final int posX) {
		this.posX = posX;
	}


	public final int getX() {
		return posX;
	}

	public final void setWidth(final int width) {
		vausWidth = width;
	}

	public final int getWidth() {
		return vausWidth;
	}

	/**
	 * Move the Vaus of a specified delta
	 * 
	 * @param deltaX
	 */
	public final void move(final int delta) {
		setX(posX + delta);
	}

	/**
	 * Move the Vaus based on the moveX field
	 */
	public void move() {
		setX(posX + moveRight * VAUS_SPEED - moveLeft * VAUS_SPEED);
		if (posX <= 5) {
			setX(5);
		}
		if (posX + getWidth() + 5 >= GAME_WIDTH) {
			setX(GAME_WIDTH - getWidth() - 5);
		}
	}

	public final void moveLeft() {
		moveLeft = 1;
	}

	public final void moveRight() {
		moveRight = 1;
	}

	public final void stopLeft() {
		moveLeft = 0;
	}

	public final void stopRight() {
		moveRight = 0;
	}

}
