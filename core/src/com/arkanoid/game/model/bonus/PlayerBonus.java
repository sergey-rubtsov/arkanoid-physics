package com.arkanoid.game.model.bonus;

public abstract class PlayerBonus extends Bonus {

	public PlayerBonus(final int bonusClass) {
		super(bonusClass);
	}

	@Override
	public abstract String toString();

}
