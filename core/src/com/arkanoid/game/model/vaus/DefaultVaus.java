package com.arkanoid.game.model.vaus;

/**
 * The default Vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-05-24 14:56:38 +0200 (Вс, 24 май 2009) $
 * 
 */

public final class DefaultVaus extends Vaus {

	public DefaultVaus(final int x) {
		super(x);
	}

	@Override
	public final String toString() {
		switch (vausWidth) {
		case LONGVAUS_WIDTH:
			return "longVaus";
		case SHORTVAUS_WIDTH:
			return "shortVaus";
		default:
			return "defaultVaus";
		}
	}

}
