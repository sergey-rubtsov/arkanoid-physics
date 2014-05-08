package com.arkanoid.game;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.arkanoid.game.model.Ball;
import com.arkanoid.game.model.Border;
import com.arkanoid.game.model.Vaus;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class CollisionTest {
	
	public final int WORLD_HEIGHT = 480;
	public final int WORLD_WIDTH = 320;

	public static final Vector2 gravity = new Vector2(0, -10);
	public World world;
	public Ball ball;
	public Vaus vaus;
	public Border border;	
	
	@Test 
	public void testSetUp() {
		assertNotNull(ball);
		assertNotNull(vaus);
		assertNotNull(border);
	}
	
	@Test
	public void testBallFall() {
		Vector2 pos = ball.getPosition();
		assertEquals(100, pos.x, 0);
		assertEquals(100, pos.y, 0);
		world.step(1/60f, 10, 10);
		pos = ball.getPosition();
		assertEquals(100.0, pos.x, 0);
		assertNotEquals(100.0, pos.y, 0);
		world.step(1/60f, 10, 10);
		world.step(1/60f, 10, 10);
		world.step(1/60f, 10, 10);
		pos = ball.getPosition();
		assertEquals(100.0, pos.x, 0);
		assertNotEquals(100.0, pos.y, 0);
	}
	
	@Test
	public void testScreen() {
	
	}
	
	@Before
	public void setUp() throws Exception {
		createWorld();
	}
	
	public void createWorld() {
		world = new World(gravity, false);
		ball = new Ball(world, 100, 100, 10, 10f, 0);
		vaus = new Vaus(world, 100, 0, 50, 10, 10f, 0);
		border = new Border(world, getBorderVectors(), 10f, 0f);
	}
	
	public Vector2[] getBorderVectors() {
		Vector2[] vectors = new Vector2[4];
		vectors[0] = new Vector2(0, 0);
		vectors[1] = new Vector2(0, WORLD_HEIGHT);
		vectors[2] = new Vector2(WORLD_WIDTH, WORLD_HEIGHT);
		vectors[3] = new Vector2(WORLD_WIDTH, 0);
		return vectors;
	}

	@After
	public void tearDown() throws Exception {
	}

}
