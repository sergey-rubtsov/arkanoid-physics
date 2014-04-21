package com.arkanoid.game.model.ball;

import com.arkanoid.game.model.ArkanoidGame;
import com.arkanoid.game.model.level.Level;
import com.arkanoid.game.model.vaus.Vaus;

/**
 * Initial ball
 */

public class StartBall extends Ball {

	public StartBall(final Vaus vaus, final Level level) {
		super(vaus, level);
	}

	@Override
	public final Ball copy() {
		final Ball returnBall = new DefaultBall(vaus, level);

		return transferBall(returnBall);
	}

	@Override
	public final String toString() {
		return "startBall";
	}

	@Override
	public void start(final ArkanoidGame game) {
		setSpeedX(0);
		setSpeedY(-3);
		final Ball newBall = new DefaultBall(vaus, level);
		newBall.setBoxEnabled(getBoxEnabled());
		newBall.setSpeedMod(getSpeedMod());
		newBall.setSpeedX(getSpeedX());
		newBall.setSpeedY(getSpeedY());
		newBall.setX(getX());
		newBall.setY(getY());
		game.replaceBall(this, newBall);
	}

	@Override
	public final void vausMoved(final int newX) {
		setX((VAUS_WIDTH / 2 - BALL_RADIUS) + newX);
	}

}
