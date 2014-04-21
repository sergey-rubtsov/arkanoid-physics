package com.arkanoid.game.model.brick;

/**
 * Persistent Brick - this brick cannot be destroyed
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-05-24 14:56:38 +0200 (Вс, 24 май 2009) $
 * 
 */

public final class PersistentBrick extends Brick {

	public PersistentBrick() {
		setLives(-1);
		setPoints(0);
	}

	@Override
	public final String toString() {
		return "persistentBrick";
	}

}
