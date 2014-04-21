package com.arkanoid.game.model.brick;

import com.arkanoid.game.model.bonus.Bonus;

public abstract class Brick {
	private int lives;
	private int points;
	private Bonus bonus;


	public Brick() {
		bonus = null;
	}
	
	/**
	 * translates a Brick to a String
	 * 
	 * @return the String version of the Brick
	 */
	@Override
	public abstract String toString();

	// getters
	public final Bonus getBonus() {
		return bonus;
	}

	public final int getLives() {
		return lives;
	}

	public final int getPoints() {
		return points;
	}

	// setters
	public final void setLives(final int lives) {
		this.lives = lives;
	}

	public final void setBonus(final Bonus bonus) {
		this.bonus = bonus;
	}

	public final void setPoints(final int points) {
		this.points = points;
	}

	public final void decrementLives() {
		lives--;
	}

}
