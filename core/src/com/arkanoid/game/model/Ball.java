package com.arkanoid.game.model;

import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Ball extends PhysicalObject {
	
	private final float radius;
	
	public Ball(World world, float x, float y, float radius) {
		this.radius = radius;
		super.setBody(BodyFactory.createCircle(world, x, y, radius, false));
		getBody().setActive(true);
		getBody().setBullet(true);
		getBody().setUserData(this);
	}
	
	public Shape getShape() {
		return getBody().getFixtureList().get(0).getShape();
	}
	
	public float getShapeRadius() {
		return getBody().getFixtureList().get(0).getShape().getRadius();
	}

	public float getRadius() {
		return radius;
	}
}
