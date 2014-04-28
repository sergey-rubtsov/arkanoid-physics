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

package com.arkanoid.game.view;

import com.arkanoid.game.Assets;
import com.arkanoid.game.model.GameField.WorldListener;
import com.arkanoid.game.model.GameField;
import com.arkanoid.game.utils.GLShapeRenderer;
import com.arkanoid.game.utils.GameRendering;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class GameScreen implements Screen {
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 4;

	Game game;

	int state;
	OrthographicCamera guiCam;
	Vector3 touchPoint;
	SpriteBatch batcher;
	GLShapeRenderer renderer;
	GameField field;
	WorldListener worldListener;
	GameRendering gameRendering;
	
	Rectangle pauseBounds;
	Rectangle resumeBounds;
	Rectangle quitBounds;
	int lastScore;
	String scoreString;

	public GameScreen (Game game) {
		this.game = game;

		state = GAME_READY;
		guiCam = new OrthographicCamera(320, 480);
		guiCam.position.set(320 / 2, 480 / 2, 0);
		touchPoint = new Vector3();
		batcher = new SpriteBatch();
		renderer = new GLShapeRenderer();
		worldListener = new WorldListener() {
			@Override
			public void gameStarted(GameField field) {
				Assets.playSound(Assets.hitSound);				
			}

			@Override
			public void ballLost(GameField field) {
				Assets.playSound(Assets.hitSound);
			}

			@Override
			public void gameEnded(GameField field) {
				Assets.playSound(Assets.hitSound);
			}

			@Override
			public void tick(GameField field, long msecs) {
				//Assets.playSound(Assets.hitSound);
			}

			@Override
			public void vausMoved(GameField field) {
				Assets.playSound(Assets.hitSound);
			}		
		};
		field = new GameField(worldListener);
		
		gameRendering = new GameRendering(batcher, renderer, field);
		pauseBounds = new Rectangle(320 - 64, 480 - 64, 64, 64);
		resumeBounds = new Rectangle(160 - 96, 240, 192, 36);
		quitBounds = new Rectangle(160 - 96, 240 - 36, 192, 36);
		lastScore = 0;
		scoreString = "SCORE: 0";
	}

	public void update (float deltaTime) {
		if (deltaTime > 0.1f) deltaTime = 0.1f;

		switch (state) {
		case GAME_READY:
			updateReady();
			break;
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		case GAME_PAUSED:
			updatePaused();
			break;
		case GAME_LEVEL_END:
			updateLevelEnd();
			break;
		case GAME_OVER:
			updateGameOver();
			break;
		}
	}

	private void updateReady () {
		if (Gdx.input.justTouched()) {
			state = GAME_RUNNING;
		}
	}

	private void updateRunning (float deltaTime) {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (pauseBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				state = GAME_PAUSED;
				return;
			}
		}		
		ApplicationType appType = Gdx.app.getType();
		
		// should work also with Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)
		if (appType == ApplicationType.Android || appType == ApplicationType.iOS) {
			field.changeGravity(Gdx.input.getAccelerometerX(), Gdx.input.getAccelerometerY());
		} else {
			float moveX = 0;
			if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) moveX = -50f;
			if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) moveX = 50f;
			float accelX = 0;
			float accelY = 0;
			if (Gdx.input.isKeyPressed(Keys.A)) accelX = 5f;
			if (Gdx.input.isKeyPressed(Keys.D)) accelX = -5f;
			if (Gdx.input.isKeyPressed(Keys.W)) accelY = 5f;
			if (Gdx.input.isKeyPressed(Keys.S)) accelY = -5f;
			field.vausMove(moveX);			
			field.changeGravity(accelX, accelY);
		}
		if (field.state == GameField.WORLD_STATE_NEXT_LEVEL) {
			state = GAME_LEVEL_END;
		}
		if (field.state == GameField.WORLD_STATE_GAME_OVER) {
			state = GAME_OVER;
		}		
	}

	private void updatePaused () {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (resumeBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				state = GAME_RUNNING;
				return;
			}

			if (quitBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new MainMenuScreen(game));
				return;
			}
		}
	}

	private void updateLevelEnd () {
		if (Gdx.input.justTouched()) {
			field = new GameField(worldListener);
			gameRendering = new GameRendering(batcher, renderer, field);
			state = GAME_READY;
		}
	}

	private void updateGameOver () {
		if (Gdx.input.justTouched()) {
			game.setScreen(new MainMenuScreen(game));
		}
	}

	public void draw () {
		GL20 gl = Gdx.gl;
		field.tick((long)(Gdx.graphics.getDeltaTime() * 3000), 4);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameRendering.render();
		
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);
		batcher.enableBlending();
		batcher.begin();
		switch (state) {
		case GAME_READY:
			presentReady();
			break;
		case GAME_RUNNING: {
			//field.tick((long)(Gdx.graphics.getDeltaTime() * 3000), 4);
			presentRunning();
			break;
		}
			
		case GAME_PAUSED:
			presentPaused();
			break;
		case GAME_LEVEL_END:
			presentLevelEnd();
			break;
		case GAME_OVER:
			presentGameOver();
			break;
		}
		batcher.end();
	}

	private void presentReady () {
		batcher.draw(Assets.ready, 160 - 192 / 2, 240 - 32 / 2, 192, 32);
	}

	private void presentRunning () {
		batcher.draw(Assets.pause, 320 - 64, 480 - 64, 64, 64);
	}

	private void presentPaused () {
		batcher.draw(Assets.pauseMenu, 160 - 192 / 2, 240 - 96 / 2, 192, 96);
		//Assets.font.draw(batcher, scoreString, 16, 480 - 20);
	}

	private void presentLevelEnd () {

	}

	private void presentGameOver () {
		batcher.draw(Assets.gameOver, 160 - 160 / 2, 240 - 96 / 2, 160, 96);
	}

	@Override
	public void render (float delta) {
		update(delta);
		draw();
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void show () {
	}

	@Override
	public void hide () {
	}

	@Override
	public void pause () {
		if (state == GAME_RUNNING) state = GAME_PAUSED;
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}
}
