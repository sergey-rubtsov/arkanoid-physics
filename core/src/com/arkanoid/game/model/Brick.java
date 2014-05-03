package com.arkanoid.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Brick extends PhysicalObject {
	
	private final int resistance;
	private final float kick;
	public final float width;
	public final float heigth;
	public final Property property;
	
	public enum Property {
		DISAPPEARS, FALLS, EXPLODES, GELATIN, DEFAULT
	}
	
	public Brick(World world, float x, float y, float width, float height) {
		this(world, x, y, width, height, 1, 1, Property.DEFAULT);
	}
	
	public Brick(World world, float x, float y, float width, float height, Property property) {
		this(world, x, y, width, height, 1, 1, property);
	}

	public Brick(World world, float x, float y, float width, float height, int resistance, float kick, Property property) {
		this.resistance = resistance;
		this.kick = kick;
		this.property = property;
		this.width = width;
		this.heigth = height;
		super.setBody(BodyFactory.createStaticRectangle(world, x, y, width, height));
		getBody().setUserData(this);
	}

	public int getResistance() {
		return resistance;
	}
	
	public boolean impact(Ball ball) {
		ball.getBody().applyAngularImpulse(0, false);
		return false;
	}
}
