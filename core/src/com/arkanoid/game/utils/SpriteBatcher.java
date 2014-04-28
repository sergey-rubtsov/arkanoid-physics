package com.arkanoid.game.utils;

import com.arkanoid.game.Assets;
import com.arkanoid.game.model.GameField;
import com.arkanoid.game.model.Vaus;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteBatcher {
	
	SpriteBatch batch;

	public SpriteBatcher() {
		this.batch = new SpriteBatch();
	}
	
	public void renderBackground () {
		batch.disableBlending();
		batch.begin();
		batch.draw(Assets.backgroundRegion, 0, 0, GameField.WORLD_WIDTH,
				GameField.WORLD_HEIGHT);
		batch.end();
	}
	
	private void renderVaus(float x, float y, float width, float height) {
		batch.enableBlending();
		TextureRegion vausFrame = Assets.defaultVaus;		
		batch.begin();
		batch.draw(vausFrame, x, y, width, height);
		batch.end();	
	}
}
