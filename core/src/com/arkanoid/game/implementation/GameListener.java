package com.arkanoid.game.implementation;

import com.arkanoid.game.model.Level;

/**
 * Listen to game
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-05-24 14:54:35 +0200 (Вс, 24 май 2009) $
 * 
 */

public interface GameListener {
	public void levelChanged(Level level);

	public void gameOver();

	public void bonusErase();

	public void levelCleared(Level level);

	public void arcadeCleared();
}
