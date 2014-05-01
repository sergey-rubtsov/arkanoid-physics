package com.arkanoid.game;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.arkanoid.game.model.Ball;
import com.arkanoid.game.view.GameScreen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class CollisionTest {

	public static final Vector2 gravity = new Vector2(0, -20);
	World world;
	//GameScreen screen;
	TestScreen screen;
	
	@Before
	public void setUp() throws Exception {
	
	}

	@After
	public void tearDown() throws Exception {
	}
	
	public void createEverything() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Arkanoid";
		config.width = 320;
		config.height = 534;

		TestApplication app = new TestApplication();
		//screen = new GameScreen(app);
		screen = new TestScreen();
		world = new World(gravity, false);
		screen.setWorld(world);
		app.setScreen(screen);
		new LwjglApplication(app, config);			
	}
	
	@Test
	public void testScreen() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Arkanoid";
		config.width = 320;
		config.height = 534;

		TestApplication app = new TestApplication();
		//screen = new GameScreen(app);
		screen = new TestScreen();
		world = new World(gravity, false);
		screen.setWorld(world);
		assertNotNull(screen);
		
	}	

	@Test
	public void testBallRadius() {
		//createEverything();
		//assertNotNull(world);
		//Ball ball = new Ball(world, 10, 10, 10);
		//assertNotNull(ball);
		//assertEquals(ball.getRadius(), ball.getShapeRadius());
	}

}
