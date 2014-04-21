package com.arkanoid.game.model.bonus;

import com.arkanoid.game.model.vaus.Vaus;

public abstract class VausBonus extends Bonus {

	public VausBonus(final int bonusClass) {
		super(bonusClass);
	}

	@Override
	public abstract String toString();

	protected final Vaus translateVaus(final Vaus vaus, final Vaus newVaus) {
		newVaus.setWidth(vaus.getWidth());
		newVaus.setVausListenerLsit(vaus.getVausListenerLsit());
		return newVaus;
	}

}
