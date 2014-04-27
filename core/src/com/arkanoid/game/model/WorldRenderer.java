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

package com.arkanoid.game.model;

import com.arkanoid.game.Assets;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;

public class WorldRenderer implements Renderer {
	
	static final int CIRCLE_VERTICES = 10;	
	
	static final float FRUSTUM_WIDTH = 100;
	static final float FRUSTUM_HEIGHT = 150;
	GameField field;
	OrthographicCamera cam;
	SpriteBatch batch;
	ShapeRenderer renderer;

	public WorldRenderer (SpriteBatch batch, GameField field) {
		this.field = field;
		this.cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
		this.batch = batch;
		renderer = new ShapeRenderer(500);
	}

	public void render() {
		//if (world.bob.position.y > cam.position.y) cam.position.y = world.bob.position.y;
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
		batch.enableBlending();
		TextureRegion vausFrame = Assets.defaultVaus;		
		batch.begin();
		Vaus vaus = this.field.getVaus();		
		batch.draw(vausFrame, vaus.getXPos(), vaus.getYPos(), Vaus.VAUS_WIDTH, Vaus.VAUS_HEIGHT);
		batch.end();	
	}

	private void renderBall() {
		begin();
		Ball ball = this.field.getBall();
		fillCircle(ball.getXPos(), ball.getYPos(), ball.getRadius(), 100, 100, 100);
		end();
	}

	public void begin () {
		renderer.begin(ShapeType.Line);
	}

	@Override
	public void drawLine (float x1, float y1, float x2, float y2, int r, int g, int b) {
		float fr = r / 255f;
		float fg = g / 255f;
		float fb = b / 255f;
		renderer.setColor(fr, fg, fb, 1);
		renderer.line(x1, y1, x2, y2);
	}

	@Override
	public void fillCircle (float cx, float cy, float radius, int r, int g, int b) {
		end();
		renderer.begin(ShapeType.Filled);
		float fr = r / 255f;
		float fg = g / 255f;
		float fb = b / 255f;
		renderer.setColor(fr, fg, fb, 1);
		renderer.circle(cx, cy, radius, 20);
		end();
		begin();
	}

	@Override
	public void frameCircle (float cx, float cy, float radius, int r, int g, int b) {
		end();
		renderer.begin(ShapeType.Line);
		float fr = r / 255f;
		float fg = g / 255f;
		float fb = b / 255f;
		renderer.setColor(fr, fg, fb, 1);
		renderer.circle(cx, cy, radius, 20);
		end();
		begin();
	}

	public void end () {
		renderer.end();
	}

	public void setProjectionMatrix (Matrix4 matrix) {
		renderer.setProjectionMatrix(matrix);
	}
}
