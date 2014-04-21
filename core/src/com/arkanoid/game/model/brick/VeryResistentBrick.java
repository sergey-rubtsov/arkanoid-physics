package com.arkanoid.game.model.brick;

/**
 * Very Resistent Brick - this brick has to be hit more times to be destroyed
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-05-24 14:56:38 +0200 (Вс, 24 май 2009) $
 * 
 */

public final class VeryResistentBrick extends Brick {

	public VeryResistentBrick() {
		setLives(3);
		setPoints(7);
	}

	@Override
	public final String toString() {
		final int i = getLives();
		if (i == 3) {
			return "veryResistentBrick";
		} else if (i == 2) {
			return "veryResistentBrick2";
		} else {
			return "veryResistentBrick1";
		}
	}

}
