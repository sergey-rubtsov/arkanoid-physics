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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;

public class WorldRenderer implements Renderer {
	
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
		//batch.enableBlending();
		//TextureRegion vausFrame = Assets.defaultVaus;		
		//batch.begin();
		begin();
		Vaus vaus = this.field.getVaus();
		fillRectangle(vaus.getRectangle());
		end();
		//batch.draw(vausFrame, vaus.getXPos(), vaus.getYPos(), Vaus.VAUS_WIDTH, Vaus.VAUS_HEIGHT);
		//batch.end();	
	}

	private void fillRectangle(Rectangle rectangle) {
		fillRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}
	
	public void fillRectangle(float x, float y, float width, float height) {
		end();
		renderer.begin(ShapeType.Filled);
		float fr = 255 / 255f;
		float fg = 50 / 255f;
		float fb = 50 / 255f;
		renderer.setColor(fr, fg, fb, 1);
		renderer.rect(x, y, width, height);
		end();
		begin();
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
	
	public void end () {
		renderer.end();
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

	public void setProjectionMatrix (Matrix4 matrix) {
		renderer.setProjectionMatrix(matrix);
	}
}
