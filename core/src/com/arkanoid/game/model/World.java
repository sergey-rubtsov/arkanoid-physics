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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.arkanoid.game.model.Ball;
import com.badlogic.gdx.math.Vector2;

public class World {
	public interface WorldListener {

		public void hit();

		public void bonus();
	}

	public static final float WORLD_WIDTH = 10;
	public static final float WORLD_HEIGHT = 15 * 20;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;
	public static final Vector2 gravity = new Vector2(0, -12);
	
	public final WorldListener listener;
	public final Random rand;

	public float heightSoFar;
	public int score;
	public int state;
	
	public final Vaus vaus;
	public final Ball ball;

	public World(WorldListener listener) {		
		this.vaus = new Vaus();
		this.ball = new Ball();
		this.listener = listener;
		rand = new Random();
		generateLevel();

		this.heightSoFar = 0;
		this.score = 0;
		this.state = WORLD_STATE_RUNNING;
	}

	private void generateLevel() {

	}

	public void update (float deltaTime, float accelX) {
		updateBreaks(deltaTime);
		updateBall(deltaTime, accelX);
		updateVaus(deltaTime);
		checkGameOver();
	}

	private void updateVaus(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	private void updateBall(float deltaTime, float accelX) {
		// TODO Auto-generated method stub
		
	}

	private void updateBreaks(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	private void updateBob (float deltaTime, float accelX) {
/*		if (bob.state != Bob.BOB_STATE_HIT && bob.position.y <= 0.5f) bob.hitPlatform();
		if (bob.state != Bob.BOB_STATE_HIT) bob.velocity.x = -accelX / 10 * Bob.BOB_MOVE_VELOCITY;
		bob.update(deltaTime);
		heightSoFar = Math.max(bob.position.y, heightSoFar);*/
	}

	private void updatePlatforms (float deltaTime) {
/*		int len = platforms.size();
		for (int i = 0; i < len; i++) {
			Platform platform = platforms.get(i);
			platform.update(deltaTime);
			if (platform.state == Platform.PLATFORM_STATE_PULVERIZING && platform.stateTime > Platform.PLATFORM_PULVERIZE_TIME) {
				platforms.remove(platform);
				len = platforms.size();
			}
		}*/
	}

	private void checkCollisions () {
		checkVausCollisions();
		checkBrickCollisions();
	}

	private void checkBrickCollisions() {
		// TODO Auto-generated method stub
		
	}

	private void checkVausCollisions() {
		// TODO Auto-generated method stub
		
	}

	private void checkPlatformCollisions () {
/*		if (bob.velocity.y > 0) return;

		int len = platforms.size();
		for (int i = 0; i < len; i++) {
			Platform platform = platforms.get(i);
			if (bob.position.y > platform.position.y) {
				if (bob.bounds.overlaps(platform.bounds)) {
					bob.hitPlatform();
					listener.jump();
					if (rand.nextFloat() > 0.5f) {
						platform.pulverize();
					}
					break;
				}
			}
		}*/
	}

	private void checkSquirrelCollisions () {
/*		int len = squirrels.size();
		for (int i = 0; i < len; i++) {
			Squirrel squirrel = squirrels.get(i);
			if (squirrel.bounds.overlaps(bob.bounds)) {
				bob.hitSquirrel();
				listener.hit();
			}
		}*/
	}

	private void checkGameOver () {
/*		if (heightSoFar - 7.5f > bob.position.y) {
			state = WORLD_STATE_GAME_OVER;
		}*/
	}
}
