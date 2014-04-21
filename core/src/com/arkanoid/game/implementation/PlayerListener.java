package com.arkanoid.game.implementation;

/**
 * Listen to player
 */

public interface PlayerListener {
	public void modifiedLives(final int lives);

	public void modifiedScore(final int score);

	public void modifiedTime(final int time);
}
