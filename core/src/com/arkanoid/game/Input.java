
package com.arkanoid.game;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class Input implements InputProcessor {

	public static final int LEFT = 0;
	public static final int RIGHT = 1;

	public static final int ESCAPE = 2;

	public boolean[] buttons = new boolean[64];
	public boolean[] oldButtons = new boolean[64];

	public void set (int key, boolean down) {
		int button = -1;

		if (key == Keys.DPAD_LEFT) button = LEFT;

		if (key == Keys.DPAD_RIGHT) button = RIGHT;

		if (key == Keys.ESCAPE || key == Keys.MENU) button = ESCAPE;

		if (button >= 0) {
			buttons[button] = down;
		}
	}

	public void tick () {
		for (int i = 0; i < buttons.length; i++) {
			oldButtons[i] = buttons[i];
		}

		if (Gdx.app.getType() == ApplicationType.Android) {
			boolean left = false;
			boolean right = false;

			for (int i = 0; i < 2; i++) {
				int x = (int)(Gdx.input.getX(i) / (float)Gdx.graphics.getWidth() * 320);
				if (!Gdx.input.isTouched(i)) continue;
				if (x < 32) {
					set(Keys.DPAD_LEFT, true);
					left |= true;
				}
				if (x > 32 && x < 90) {
					set(Keys.DPAD_RIGHT, true);
					right |= true;
				}
			}

			if (left == false) set(Keys.DPAD_LEFT, false);
			if (right == false) set(Keys.DPAD_RIGHT, false);
		}
	}

	public void releaseAllKeys () {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = false;
		}
	}

	@Override
	public boolean keyTyped (char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown (int x, int y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp (int x, int y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged (int x, int y, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved (int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled (int amount) {
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
}
