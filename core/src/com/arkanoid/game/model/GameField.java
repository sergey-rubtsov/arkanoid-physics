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
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class GameField implements ContactListener {
	public static interface WorldListener {

		public void gameStarted();

		public void ballLost();

		public void gameEnded();

		public void tick();		
		
		public void processBallAndBrickContact();
		
		public void processBallAndVausContact();
	}

	public static final int WORLD_WIDTH = Gdx.graphics.getWidth();
	public static final int WORLD_HEIGHT = Gdx.graphics.getHeight();
	public static final int VAUS_WIDTH = WORLD_WIDTH / 5;	
	public static final int VAUS_HEIGHT = WORLD_HEIGHT / 50;
	
	public static final int BRICK_WIDTH = WORLD_WIDTH / 10;	
	public static final int BRICK_HEIGHT = WORLD_HEIGHT / 20;
	
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
	
	private final Vaus vaus;
	private final Ball ball;
	private final Border border;
	private List<Brick> bricks;
	private Brick bumpedBrick;

	public GameField(WorldListener listener) {
		this.world = new World(Const.gravity, true);
		this.world.setContactListener(this);
		this.vaus = new Vaus(world, WORLD_WIDTH / 2, 50, VAUS_WIDTH, VAUS_HEIGHT);
		this.ball = new Ball(world, WORLD_WIDTH / 2, 300, BALL_RADIUS);
		this.border = new Border(world);
		this.bumpedBrick = null;
		this.bricks = new ArrayList<Brick>();
		buildScene();
		
		this.listener = listener;
		rand = new Random();		

		this.state = WORLD_STATE_RUNNING;
	}
	
	public void buildScene() {
		for (int i = 0; i < 9; i++) {
			bricks.add(new Brick(this.world, WORLD_WIDTH / 10 + (WORLD_WIDTH / 10 * i), 400, BRICK_WIDTH, BRICK_HEIGHT));
		}
	}
	
	public void step() {
		int iters = 4;
		float dt = Gdx.graphics.getDeltaTime() * 3000 / 1000.0f / iters;
		for (int i = 0; i < iters; i++) {
			world.step(dt, 10, 10);
		}
		getWorldListener().tick();
	}

	public WorldListener getWorldListener() {
		return listener;
	}
	
	//00001 in binary
	final public static int BALL_CONTACT = 1;	  
	//00010 in binary
	final public static int VAUS_CONTACT = 2;
	//00100 in binary
	final public static int BRICK_CONTACT = 4;
	//01000 in binary
	final public static int BOARD_CONTACT = 8;
	//10000 in binary
	final public static int BONUS_CONTACT = 16;
	
	@Override
	public void beginContact(Contact contact) {		
		Object bodyA = contact.getFixtureA().getBody().getUserData();
		Object bodyB = contact.getFixtureB().getBody().getUserData();
		if (bodyA != null && bodyB != null) {
			int mask = 0;
			if (bodyA.getClass() == Ball.class || bodyB.getClass() == Ball.class) {
				 mask = mask | BALL_CONTACT;			
			}
			if (bodyA.getClass() == Vaus.class || bodyB.getClass() == Vaus.class) {
				 mask = mask | VAUS_CONTACT;			
			}
			if (bodyA.getClass() == Brick.class) {
				 mask = mask | BRICK_CONTACT;
				 bumpedBrick = (Brick)bodyA;
			} else if (bodyB.getClass() == Brick.class) {
				 mask = mask | BRICK_CONTACT;
				 bumpedBrick = (Brick)bodyB;
			}
			processContact(mask);
		}	
	}
	
	public void processContact(int mask) {
		if (mask == 3) processBallAndVausContact();
		if (mask == 5) processBallAndBrickContact();
	}
	
	public void processBallAndVausContact() {
		getWorldListener().processBallAndVausContact();;
	}
	
	public void processBallAndBrickContact() {
		getWorldListener().processBallAndBrickContact();
	}

	@Override
	public void endContact(Contact contact) {

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		//this.bricks.remove(bumpedBrick);
		//if (bumpedBrick != null)
		//this.world.destroyBody(bumpedBrick.getBody());
		//this.bumpedBrick = null;
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
