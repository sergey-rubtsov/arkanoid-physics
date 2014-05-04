package com.arkanoid.game.model;

import com.badlogic.gdx.physics.box2d.World;

public class Brick extends PhysicalObject {
	
	private final int resistance;
	private final float kick;
	public int life; 
	public final float width;
	public final float heigth;
	public final Property property;
	
	public enum Property {
		DISAPPEARS, FALLS, EXPLODES, GELATIN, DEFAULT
	}
	
	public Brick(World world, float x, float y, float width, float height) {
		this(world, x, y, width, height, 1, 1, 2, Property.DEFAULT);
	}
	
	public Brick(World world, float x, float y, float width, float height, Property property) {
		this(world, x, y, width, height, 1, 1, 2, property);
	}

	public Brick(World world, float x, float y, float width, float height, int resistance, float kick, int life, Property property) {
		this.resistance = resistance;
		this.kick = kick;
		this.life = life;
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
	
	public int getLife() {
		return life;
	}
	
	public boolean hit() {
		life--;
		if (life <= 0) return true;
		return false;
	}
	
	public void destroyUserData() {
		getBody().setUserData(null);		
	}
}
