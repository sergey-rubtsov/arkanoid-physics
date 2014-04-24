package com.arkanoid.game.model;

import java.util.ArrayList;
import java.util.Random;

import com.arkanoid.game.implementation.Constants;

import com.arkanoid.game.implementation.Utils;
import com.arkanoid.game.implementation.VausSetListener;
/**
 * The level is defined by an array of bricks
 * 
 */
public final class Level implements Constants, VausSetListener {
	private final Random rnd;
	private final String name;
	private final Brick[][] bricks;
	private Vaus vaus;

	/**
	 * Constructor of Level, creates a new level (field of bricks).
	 */
	
	public Level(final String name, final Brick[][] brickField,
			final Vaus vaus) {
		rnd = new Random();
		this.name = name;
		this.vaus = vaus;
		bricks = brickField;
	}

	// getters and setters
	public final Brick[][] getBricks() {
		return bricks;
	}

	public final void setVaus(final Vaus vaus) {
		this.vaus = vaus;
	}

	public final String getName() {
		return name;
	}
	
	private final Brick[][] createRandomLevel() {
		Brick[][] bricks = new Brick[FIELD_ROWS][FIELD_COLUMNS];
		for (int row = 4; row<FIELD_ROWS; row++) {
			for (int col = 0; col<FIELD_COLUMNS; col++) {
				bricks[row][col] = Utils.intToBrick(rnd.nextInt(15));
			}
		}
		return bricks;
	}

	/**
	 * Removes a brick
	 * 
	 * @param pos
	 *            the position array
	 */
	public final boolean removeBrick(final float remX, final float remY) {
		if (remX > 0 && remX < FIELD_WIDTH && remY < FIELD_HEIGHT && remY > 0) {
			final int[] pos = Utils.getFieldPosition((int) remX, (int) remY);
			final Brick brick = bricks[pos[1]][pos[0]];

			if (brick != null) {
				final int lives = brick.getLives();
				if (lives == 1) {
					bricks[pos[1]][pos[0]] = null;
				} else if (lives > 1) {
					bricks[pos[1]][pos[0]].decrementLives();
				} else {
					// persistent brick - do nothing
				}
				return true;
			}
		}
		return false;
	}



	/**
	 * Returns true if the level has no more destroyable brick inside  
	 *
	 * @return true if level is cleared
	 */
	public final boolean isCleared() {
		boolean accumulator = true;
		for (int i = 0; (i < bricks.length) && accumulator; i++) {
			for (int j = 0; (j < bricks[i].length) && accumulator; j++) {
				accumulator = ((bricks[i][j] == null));
			}
		}
		return accumulator;
	}
}
