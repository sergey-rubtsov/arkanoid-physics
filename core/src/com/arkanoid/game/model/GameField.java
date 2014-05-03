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
	public static final int BALL_RADIUS = WORLD_WIDTH / 40;
	
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;
	public static final Vector2 gravity = new Vector2(0, -9.81f);
	
	WorldListener listener;
	public final Random rand;

	public int state;
	
	private World world;
	
	long gameTime;
	
	public enum BodyType {
	    VAUS, BALL, BRICK, OTHER 
	}
	
	private final Vaus vaus;
	private final Ball ball;
	private final Border border;

	public GameField(WorldListener listener) {
		world = new World(Const.gravity, true);
		world.setContactListener(this);
		this.vaus = new Vaus(world, WORLD_WIDTH / 2, 50, VAUS_WIDTH, VAUS_HEIGHT);
		this.ball = new Ball(world, WORLD_WIDTH / 2, 300, BALL_RADIUS);
		this.border = new Border(world);
		//BodyFactory.createBorder(world);
		this.listener = listener;
		rand = new Random();		

		this.state = WORLD_STATE_RUNNING;
	}
	
	public void step() {
		int iters = 4;
		float dt = Gdx.graphics.getDeltaTime() * 3000 / 1000.0f / iters;
		for (int i = 0; i < iters; i++) {
			world.step(dt, 10, 10);
		}
		getWorldListener().tick(this, (long)Gdx.graphics.getDeltaTime());
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
		Object bodyA = contact.getFixtureA().getBody().getUserData();
		Object bodyB = contact.getFixtureB().getBody().getUserData();
		if (bodyA != null && bodyB != null) {
			PhysicalObject impactedA = (PhysicalObject)bodyA;
			PhysicalObject impactedB = (PhysicalObject)bodyB;
			impactedA.impact(impactedB);
		}	
	}
	
	public void processBallAndVausContact() {
		
	}
	
	public void processBallAndBrickContact() {
		
	}

	@Override
	public void endContact(Contact contact) {

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		//destroy brick
	}
	
	public void vausMove(float moveX) {
		Vector2 velocity = new Vector2(moveX, 0);
		if ((this.vaus.getBody().getPosition().x - this.vaus.width / 2) <= 0) {
			velocity = new Vector2(60, 0);
		}
		if ((this.vaus.getBody().getPosition().x + this.vaus.width / 2) >= WORLD_WIDTH) {
			velocity = new Vector2(-60, 0);
		}
		this.vaus.getBody().setLinearVelocity(velocity);
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

	public Border getBorder() {
		return border;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
}
