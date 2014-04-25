package com.arkanoid.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.arkanoid.game.Arkanoid;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Arkanoid";
		config.width = 480;
		config.height = 800;
		new LwjglApplication(new Arkanoid(), config);
	}
}
