package com.arkanoid.game.model.bonus;

/**
 * This enum returns a new instance of the given bonus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-05-24 14:56:38 +0200 (Вс, 24 май 2009) $
 * 
 */

public enum Bonuses {

	ADD_LIFE(16, "bonus_addlife", 1, new AddLifeBonus());
	
	private final int probability;
	private final int bonusNumber;
	private final Bonus bonus;
	private final String bonusName;

	/**
	 * Constructor of the enum
	 * 
	 * @param bonusNumber
	 * @param bonusName
	 * @param prob
	 * @param bonus
	 */
	Bonuses(final int bonusNumber, final String bonusName, final int prob,
			final Bonus bonus) {
		probability = prob;
		this.bonusNumber = bonusNumber;
		this.bonus = bonus;
		this.bonusName = bonusName;
	}

	/**
	 * Get the probability that a bonus occurs (on a scale from 1 (rarest) to 5
	 * (common)
	 * 
	 * @param bonusNumber
	 */

	public final static int getProb(final int bonusNumber) {
		for (final Bonuses b : Bonuses.values()) {
			if (b.bonusNumber == bonusNumber) {
				return b.probability;
			}
		}
		return -1;
	}

	/**
	 * Get a bonus given its bonus number
	 * 
	 * @param bonusNumber
	 */

	public final static Bonus getBonus(final int bonusNumber) {
		for (final Bonuses b : Bonuses.values()) {
			if (b.bonusNumber == bonusNumber) {
				try {
					return b.bonus.getClass().newInstance();
				} catch (final InstantiationException e) {
					e.printStackTrace();
				} catch (final IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * Get a bonus given its name
	 * 
	 * @param bonusName
	 */

	public final static Bonus stringToBonus(final String bonusName) {
		for (final Bonuses b : Bonuses.values()) {
			if (b.bonusName.equals(bonusName)) {
				try {
					return b.bonus.getClass().newInstance();
				} catch (final InstantiationException e) {
					e.printStackTrace();
				} catch (final IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
