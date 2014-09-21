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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.JointEdge;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.badlogic.gdx.utils.Array;

public class GameField implements ContactListener, Constants {
	public static interface WorldListener {

		public void gameStarted();

		public void ballLost();

		public void gameEnded();

		public void tick();		
		
		public void processBallAndBrickContact();
		
		public void processBallAndVausContact();

		public void processBallAndBorderContact();
	}


	
	public static final Vector2 gravity = new Vector2(0, 0);
	
	WorldListener listener;
	public final Random rand;

	public int state;
	
	private World world;
	private int level;
	
	long gameTime;
	
	private final WorldBuilder builder;
	
	private final Vaus vaus;
	private Ball ball;
	private final Border border;
	private List<Brick> bricks;
	
	private int lives;

	private Brick bumpedBrick;
	private int contactMask;

	public GameField(WorldListener listener) {
		this.world = new World(GameField.gravity, true);
		this.world.setContactListener(this);
		this.level = 0;
		
		this.builder = new WorldBuilder(level);
		this.vaus = new Vaus(world, WORLD_WIDTH / 2, VAUS_Y_POS, VAUS_WIDTH, VAUS_HEIGHT);
		this.ball = new Ball(world, WORLD_WIDTH / 2, 300, BALL_RADIUS);
		this.border = new Border(world);
		//Border.createBorder(world, new Vector2[]{new Vector2(1, 1), new Vector2(WORLD_WIDTH - 1, 1)}, 10f, 0f);
		this.bumpedBrick = null;
		this.contactMask = 0;
		this.bricks = new ArrayList<Brick>();
		buildScene(level);
		
		this.listener = listener;
		rand = new Random();		
		
		this.lives = DEFAULT_LIVES;
		this.state = WORLD_STATE_RUNNING;		
	}
	
	public void launchBall() {
		//ball.getBody().setLinearVelocity(new Vector2(0, -50));
		ball.getBody().applyLinearImpulse(new Vector2(0, -10000), ball.getBody().getPosition(), true);		
	}
	
	public void step() {
		int iters = 2;
		step(iters);
		getWorldListener().tick();
		checkLostBall();
	}
	
	public void step(int iters) {
		//float dt = Gdx.graphics.getDeltaTime() / iters;
		float dt = 1 / 60f;
		for (int i = 0; i < iters; i++) {
			world.step(dt, 10, 10);			
		}		
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
	final public static int BORDER_CONTACT = 8;
	//10000 in binary
	final public static int BONUS_CONTACT = 16;
	
	@Override
	public void beginContact(Contact contact) {		
		Object bodyA = contact.getFixtureA().getBody().getUserData();
		Object bodyB = contact.getFixtureB().getBody().getUserData();
		if (bodyA != null && bodyB != null) {
			contactMask = 0;
			if (bodyA.getClass() == Ball.class || bodyB.getClass() == Ball.class) {
				 contactMask = contactMask | BALL_CONTACT;			
			}
			if (bodyA.getClass() == Vaus.class || bodyB.getClass() == Vaus.class) {
				 contactMask = contactMask | VAUS_CONTACT;			
			}
			if (bodyA.getClass() == Border.class || bodyB.getClass() == Border.class) {
				 contactMask = contactMask | BORDER_CONTACT;			
			}
			if (bodyA.getClass() == Brick.class) {
				 contactMask = contactMask | BRICK_CONTACT;
				 bumpedBrick = (Brick)bodyA;
			} else if (bodyB.getClass() == Brick.class) {
				 contactMask = contactMask | BRICK_CONTACT;
				 bumpedBrick = (Brick)bodyB;
			}
			processBeginContact();
		}	
	}
	
	public void processBeginContact() {
		if (contactMask == 9) {
			processBallAndBorderContact();
			return;
		}
	}
	
	private void processBallAndBorderContact() {
		getWorldListener().processBallAndBorderContact();
	}

	public void processBallAndVausContact(Contact contact) {
		WorldManifold wm = contact.getWorldManifold();
		Vector2 normal = wm.getNormal();
		normal.x = normal.x * 1000000;
		normal.y = normal.y * 1000000;
		for (int i = 0; i < 10; i++) {
			world.step(1 / 60f, 10, 10);
			ball.getBody().applyLinearImpulse(normal, ball.getBody().getPosition(), true);
		}		
		getWorldListener().processBallAndVausContact();
	}
	
	public void processBallAndBrickContact() {	
		if (bumpedBrick.hit()) removeBrick(bumpedBrick);
		getWorldListener().processBallAndBrickContact();
	}
	
	public void removeBrick(Brick brick) {
		removeBody(brick.getBody());
		this.bricks.remove(brick);
		brick = null;
		checkBricksCount();
	}
	
	private void checkBricksCount() {
		if (this.bricks.size() <= 0) {
			nextLevelState();
		}		
	}

	private void nextLevelState() {
		state = WORLD_STATE_NEXT_LEVEL;		
	}
	
	public void buildScene() {
		for (int i = 0; i < 1; i++) {
			for (int j = 4; j < 5; j++) {
				bricks.add(new Brick(this.world, BRICK_WIDTH / 2 + WORLD_WIDTH / 10 * j, WORLD_HEIGHT - BRICK_HEIGHT / 2 - BRICK_HEIGHT * i, BRICK_WIDTH, BRICK_HEIGHT));
			}			
		}
		launchBall();
	}
	
	public void buildScene(int level) {
		buildScene();		
	}
	
	public void loadNextLevel() {
		state = WORLD_STATE_RUNNING;
		level++;
		buildScene(level);
		rebuildBall();		
	}
	
	public void rebuildBall() {
		removeBall();
		this.ball = new Ball(world, WORLD_WIDTH / 2, 300, BALL_RADIUS);
		launchBall();
	}

	public void removeBall() {
		removeBody(this.ball.getBody());
		this.ball = null;		
	}
	
	public void removeBody(Body body) {
		step(1);
		if(!world.isLocked()) {			
			if (body != null) {
				body.setUserData(null);				
				//to prevent some obscure c assertion that happened randomly once in a blue moon
			    final Array<JointEdge> list = body.getJointList();
			    for (JointEdge edge : list) {
			        world.destroyJoint(edge.joint);
			    }
			    // actual remove
			    this.world.destroyBody(body);
			}
		}		
	}

	@Override
	public void endContact(Contact contact) {

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		switch (contactMask) {
		case 3:
			processBallAndVausContact(contact);
			break;
		case 5:
			processBallAndBrickContact();
			break;
		} 
		contactMask = 0;
	}
	
	public void vausTarget(int x) {
		if (x < this.vaus.getXPos()) {
			vausMove(-90f);
		} else vausMove(90f);		
	}
	
	public void vausMove(float moveX) {
		Vector2 velocity = new Vector2(moveX, 0);
		if ((this.vaus.getBody().getPosition().x - this.vaus.width / 2) <= 0) {
			velocity = new Vector2(100, 0);
		}
		if ((this.vaus.getBody().getPosition().x + this.vaus.width / 2) >= WORLD_WIDTH) {
			velocity = new Vector2(-100, 0);
		}
		this.vaus.getBody().setLinearVelocity(velocity);		
	}
	
	public void changeGravity(float accelX, float accelY) {
		//this.world.setGravity(new Vector2(accelX, accelY));
	}

	private void checkLostBall() {
		if (this.ball.getYPos() < -BALL_RADIUS) {
			lives--;
			if (lives <= 0) {
				state = WORLD_STATE_GAME_OVER;
			} else {
				rebuildBall();
			}			
		}
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
	
	public List<Brick> getBricks() {
		return bricks;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;		
	}
}
