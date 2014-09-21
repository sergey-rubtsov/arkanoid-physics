
package com.arkanoid.game.utils;

public class MathUtils {

	public static float asFloat (Object obj, float defvalue) {
		if (obj instanceof Number) return ((Number)obj).floatValue();
		return defvalue;
	}

	public static float asFloat (Object obj) {
		return asFloat(obj, 0f);
	}

	public static float toRadians (float degrees) {
		return (float)(Math.PI / 180) * degrees;
	}

}
