package com.arkanoid.game.model.bonus;

import java.util.ArrayList;

import com.arkanoid.game.implementation.Constants;
import com.arkanoid.game.implementation.VausSetListener;
import com.arkanoid.game.implementation.BonusListener;
import com.arkanoid.game.model.ArkanoidGame;
import com.arkanoid.game.model.vaus.Vaus;

public abstract class Bonus implements Constants, VausSetListener {
	private int x;
	private int y;
	private Vaus vaus;
	private boolean dead;
	private int lifeInMill;
	private final int bonusClass;

	private final ArrayList<BonusListener> listeners;

	public Bonus(final int bonusClass) {
		x = 0;
		y = 0;
		vaus = null;
		listeners = new ArrayList<BonusListener>();
		lifeInMill = 30000;
		this.bonusClass = bonusClass;
	}

	/**
	 * translates a Bonus to a String
	 * 
	 * @return the String version of the Bonus
	 */
	@Override
	public abstract String toString();

	/**
	 * applies a bonus to a game
	 * 
	 * @param game
	 */
	public abstract void apply(final ArkanoidGame game);

	// getters
	public final int getX() {
		return x;
	}

	public final int getY() {
		return y;
	}

	public final boolean isDead() {
		return dead;
	}

	public int getLife() {
		return lifeInMill;
	}

	public int getBonusClass() {
		return bonusClass;
	}

	// setters
	public final void setX(final int x) {
		this.x = x;
	}

	public final void setY(final int y) {
		this.y = y;
	}

	public final void setVaus(final Vaus vaus) {
		this.vaus = vaus;
	}

	public final void setLife(final int life) {
		lifeInMill = life;
	}
	public void decrementLife() {
		lifeInMill -= TICKS_PER_SECOND;
		fireLifeDecreased();
	}

	/**
	 * Called each time the bonus has to move
	 */
	public final void move() {
		y = y + BONUS_SPEED;
		checkVausCollision();
		checkOutOfReach();
	}

	/**
	 * Checks if a bonus collides woth the vaus
	 */
	private final void checkVausCollision() {
		if (y + BRICK_HEIGHT > VAUS_Y
				&& y + BRICK_HEIGHT < VAUS_Y + BRICK_HEIGHT
				&& x + BRICK_WIDTH >= vaus.getX()
				&& x <= vaus.getX() + vaus.getWidth()) {
			fireBonusTaken();
		}
	}

	/**
	 * check whenever a bonus is out of reach and, in the case, sets the dead field to true
	 */
	private final void checkOutOfReach() {
		if (y > GAME_HEIGHT) {
			dead = true;
		}
	}

	public void addBonusListener(final BonusListener li) {
		listeners.add(li);
	}

	public void removeBonusListener(final BonusListener li) {
		listeners.remove(li);
	}

	protected void fireBonusTaken() {
		for (final BonusListener li : listeners) {
			li.bonusTaken(this);
		}
	}

	protected void fireLifeDecreased() {
		for (final BonusListener li : listeners) {
			li.lifeDecreased(this);
		}
	}

	/**
	 * removes the effect of the bonus from the game
	 * 
	 * @param game
	 */
	public void remove(final ArkanoidGame game) {
		return;
	}

	

}
