package com.arkanoid.game.model;

import com.badlogic.gdx.physics.box2d.World;

public class Ball extends PhysicalObject {
	
	private final float radius;
	
	public Ball(World world, float x, float y, float radius) {
		this.radius = radius;
		super.setBody(BodyFactory.createCircle(world, x, y, radius, false));
		getBody().setActive(true);
		getBody().setBullet(true);
	}

	public float getRadius() {
		return radius;
	}
}
