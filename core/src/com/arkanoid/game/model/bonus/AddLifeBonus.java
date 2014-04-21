package com.arkanoid.game.model.bonus;

import com.arkanoid.game.model.ArkanoidGame;

/**
 * This bonus increases the lives of the player by one.
 */

public final class AddLifeBonus extends PlayerBonus {

	public AddLifeBonus() {
		super(0);
		super.setLife(INSTANTANEOUS);
	}

	@Override
	public final String toString() {
		return "bonus_addlife";
	}

	@Override
	public final void apply(final ArkanoidGame game) {
		game.getPlayer().incrementLives();
	}

}
