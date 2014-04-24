package com.arkanoid.game.model;

public class Brick {
	private int lives;
	private int points;

	public Brick() {
	}

	public final int getLives() {
		return lives;
	}

	public final int getPoints() {
		return points;
	}

	public final void setLives(final int lives) {
		this.lives = lives;
	}

	public final void setPoints(final int points) {
		this.points = points;
	}

	public final void decrementLives() {
		lives--;
	}

}
