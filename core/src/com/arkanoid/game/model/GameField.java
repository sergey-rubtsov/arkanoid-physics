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

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class GameField implements ContactListener {
	public static interface WorldListener {

		public void gameStarted(GameField field);

		public void ballLost(GameField field);

		public void gameEnded(GameField field);

		public void tick(GameField field, long msecs);

		//public void processCollision (GameField field, FieldElement element, Body hitBody, Body ball);

		public void vausMoved(GameField field);
	}

	public static final float WORLD_WIDTH = 100;
	public static final float WORLD_HEIGHT = 150;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;
	public static final Vector2 gravity = new Vector2(0, -12);
	
	WorldListener listener;
	public final Random rand;

	public int state;
	
	private World world;
	
	long gameTime;
	
	private final Vaus vaus;
	private final Ball ball;

	public GameField(WorldListener listener) {
		generateWorld();
		//this.vaus = new Vaus(WORLD_WIDTH / 2 - (Vaus.VAUS_WIDTH / 2), 10 - Vaus.VAUS_HEIGHT / 2);
		this.vaus = new Vaus(world, 200, 150);
		this.ball = new Ball(world, 200, 100, 10);
		this.listener = listener;
		rand = new Random();		

		this.state = WORLD_STATE_RUNNING;
	}
	
	private void generateWorld() {
		Vector2 gravity = new Vector2(0.0f, -1.0f);
		boolean doSleep = true;
		world = new World(gravity, doSleep);
		world.setContactListener(this);
	}
	
	public void tick(long msecs, int iters) {
		float dt = (msecs / 1000.0f) / iters;

		for (int i = 0; i < iters; i++) {
			//clearBallContacts();
			world.step(dt, 10, 10);
			//processBallContacts();
		}

		gameTime += msecs;
		//processElementTicks();
		//processScheduledActions();

		getWorldListener().tick(this, msecs);
	}
	
	public WorldListener getWorldListener() {
		return listener;
	}
	
	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

	public void update(float deltaTime, float vausMoveX, float accelX, float accelY) {
		updateBall(deltaTime, accelX, accelY);
		updateVaus(deltaTime, vausMoveX);
		checkGameOver();
	}

	private void updateVaus(float deltaTime, float vausMoveX) {
		//vaus.update(deltaTime, vausMoveX);
	}

	private void updateBall(float deltaTime, float accelX, float accelY) {
		
	}

	private void updateBreaks(float deltaTime) {
		
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
		checkBallCollisions();
	}

	private void checkBallCollisions() {		
		
	}

	private void checkBrickCollisions() {
	
	}

	private void checkVausCollisions() {
		
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

	public Ball getBall() {
		return ball;
	}

	public Vaus getVaus() {
		return vaus;
	}

}
