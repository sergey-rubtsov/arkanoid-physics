package com.arkanoid.game.implementation;

import com.arkanoid.game.model.bonus.Bonus;
import com.arkanoid.game.model.brick.Brick;

/**
 * Listen to level
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-05-21 15:09:37 +0200 (Чт, 21 май 2009) $
 * 
 */

public interface LevelListener {
	public void bonusReleased(Bonus bonus);

	public void brickHit(Brick brick);
}
