package com.arkanoid.game.implementation;

import com.arkanoid.game.model.bonus.Bonus;

public interface BonusListener {
	public void bonusTaken(Bonus bonus);

	public void lifeDecreased(Bonus bonus);
}
