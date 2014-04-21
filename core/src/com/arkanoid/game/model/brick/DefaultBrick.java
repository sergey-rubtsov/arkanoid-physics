package com.arkanoid.game.model.brick;

/**
 * Default Brick
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-05-24 14:56:38 +0200 (Вс, 24 май 2009) $
 * 
 */

public final class DefaultBrick extends Brick {

	public DefaultBrick() {
		setLives(1);
		setPoints(10);
	}

	@Override
	public final String toString() {
		return "defaultBrick";
	}

}
