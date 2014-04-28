/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.arkanoid.game.utils;

import com.arkanoid.game.Assets;
import com.arkanoid.game.model.Ball;
import com.arkanoid.game.model.GameField;
import com.arkanoid.game.model.Vaus;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

public class GameRendering {
	
	static final float FRUSTUM_WIDTH = 100;
	static final float FRUSTUM_HEIGHT = 150;
	GameField field;
	OrthographicCamera cam;
	SpriteBatch batch;
	GLShapeRenderer renderer;

	public GameRendering (SpriteBatch batch, GLShapeRenderer renderer, GameField field) {
		this.field = field;
		this.cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
		this.batch = batch;
		this.renderer = renderer;
	}

	public void render() {
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		renderBackground();
		renderObjects();
	}

	public void renderBackground () {
		batch.disableBlending();
		batch.begin();
		batch.draw(Assets.backgroundRegion, cam.position.x - FRUSTUM_WIDTH / 2, cam.position.y - FRUSTUM_HEIGHT / 2, FRUSTUM_WIDTH,
			FRUSTUM_HEIGHT);
		batch.end();
	}

	public void renderObjects () {
		renderBall();
		renderVaus();	
	}

	private void renderVaus() {
		Vaus vaus = this.field.getVaus();
		renderer.fillRectangle(vaus.getRectangle());	
	}

	private void renderBall() {
		Ball ball = this.field.getBall();
		renderer.fillCircle(ball.getXPos(), ball.getYPos(), ball.getRadius(), 100, 100, 100);
	}

	public void setProjectionMatrix (Matrix4 matrix) {
		renderer.setProjectionMatrix(matrix);
	}
}
