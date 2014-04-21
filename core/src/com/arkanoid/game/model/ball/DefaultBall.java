package com.arkanoid.game.model.ball;

import com.arkanoid.game.model.level.Level;
import com.arkanoid.game.model.vaus.Vaus;

/**
 * This is the default ball.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-05-24 14:56:38 +0200 (Вс, 24 май 2009) $
 * 
 */

public class DefaultBall extends Ball {

	public DefaultBall(final Vaus vaus, final Level level) {
		super(vaus, level);
	}

	@Override
	public final Ball copy() {
		final Ball returnBall = new DefaultBall(vaus, level);

		return transferBall(returnBall);
	}

	@Override
	public String toString() {
		return "defaultBall";
	}

}
