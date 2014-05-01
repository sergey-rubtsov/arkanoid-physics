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

import com.arkanoid.game.utils.Const;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class GameField implements ContactListener {
	public static interface WorldListener {

		public void gameStarted(GameField field);

		public void ballLost(GameField field);

		public void gameEnded(GameField field);

		public void tick(GameField field, long msecs);

		public void processCollision (GameField field, PhysicalObject element, Body hitBody, Body ball);

		public void vausMoved(GameField field);
	}

	public static final int WORLD_WIDTH = Gdx.graphics.getWidth();
	public static final int WORLD_HEIGHT = Gdx.graphics.getHeight();
	public static final int VAUS_WIDTH = WORLD_WIDTH / 5;
	public static final int VAUS_HEIGHT = WORLD_HEIGHT / 50;
	public static final int BALL_RADIUS = WORLD_WIDTH / 50;
	
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;
	public static final Vector2 gravity = new Vector2(0, -9.81f);
	
	WorldListener listener;
	public final Random rand;

	public int state;
	
	private World world;
	
	long gameTime;
	
	private final Vaus vaus;
	private final Ball ball;
	private final Body leftBorder;

	public GameField(WorldListener listener) {
		world = new World(Const.gravity, true);
		world.setContactListener(this);
		this.vaus = new Vaus(world, WORLD_WIDTH / 2, 50, VAUS_WIDTH, VAUS_HEIGHT);
		this.ball = new Ball(world, WORLD_WIDTH / 2, 300, BALL_RADIUS);
		//this.leftBorder = BodyFactory.createLine(world, 0, 2, WORLD_WIDTH, 2);
		this.leftBorder = BodyFactory.createBorder(world);
		this.listener = listener;
		rand = new Random();		

		this.state = WORLD_STATE_RUNNING;
	}
	
	public void tick(long msecs, int iters) {
		float dt = (msecs / 1000.0f) / iters;

		for (int i = 0; i < iters; i++) {
			//clearBallContacts();
			//world.step(dt, 10, 10);
			world.step(1 / 60f, 10, 10);
			//processBallContacts();
		}

		gameTime += msecs;
		//processElementTicks();
		//processScheduledActions();

		getWorldListener().tick(this, msecs);
	}
	
	private void processBallContacts() {
		Body ball = getBall().getBody();
		
		if (ball.getUserData() == null) return;

		List<Fixture> fixtures = (List<Fixture>)ball.getUserData();
		int len2 = fixtures.size();
		for (int j = 0; j < len2; j++) {
			Fixture f = fixtures.get(j);
			if (f.getBody() != null)
			getVaus().handleCollision(f.getBody());
		}
		fixtures.clear();
				
	}

	public WorldListener getWorldListener() {
		return listener;
	}
	
	@Override
	public void beginContact(Contact contact) {
		Body ball = contact.getFixtureA().getBody();		
	}

	@Override
	public void endContact(Contact contact) {
		Body ball = contact.getFixtureA().getBody();
		Fixture fixture = contact.getFixtureB();

		if (ball != null) {
			List<Fixture> fixtures = (List<Fixture>)ball.getUserData();
			if (fixtures == null) {
				ball.setUserData(fixtures = new ArrayList<Fixture>());
			}
			fixtures.add(fixture);
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
	
	public void vausMove(float deltaX) {
		this.vaus.getBody().setLinearVelocity(new Vector2(deltaX, 0));
	}
	
	public void changeGravity(float accelX, float accelY) {
		//this.world.setGravity(new Vector2(accelX, accelY));
	}

	private void checkGameOver () {
/*		if (true) {
			state = WORLD_STATE_GAME_OVER;
		}*/
	}

	public Ball getBall() {
		return ball;
	}

	public Vaus getVaus() {
		return vaus;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
