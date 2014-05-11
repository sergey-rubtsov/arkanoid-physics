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

import com.arkanoid.game.model.Ball;
import com.arkanoid.game.model.GameField;
import com.arkanoid.game.model.Vaus;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Body;

public class GameRendering {
	
	GameField field;
	OrthographicCamera cam;
	SpriteBatcher batcher;
	GLShapeRenderer renderer;

	public GameRendering (SpriteBatcher batch, GLShapeRenderer renderer, GameField field) {
		this.field = field;
		this.cam = new OrthographicCamera(GameField.WORLD_WIDTH, GameField.WORLD_HEIGHT);
		this.cam.position.set(GameField.WORLD_WIDTH / 2, GameField.WORLD_HEIGHT / 2, 0);
		this.batcher = batch;
		this.renderer = renderer;
	}

	public void render() {
		cam.update();
		batcher.setProjectionMatrix(cam.combined);
		batcher.renderBackground();
		renderObjects();
	}

	public void renderObjects () {
		renderBall();
		renderVaus();	
	}

	private void renderVaus() {
		renderer.fillRectangle(this.field.getVaus().getRectangle());	
	}

	private void renderBall() {
		Ball ball = this.field.getBall();
		renderer.fillCircle(ball.getXPos(), ball.getYPos(), ball.getRadius(), 100, 100, 100);
	}

	public void setProjectionMatrix (Matrix4 matrix) {
		renderer.setProjectionMatrix(matrix);
	}
}
