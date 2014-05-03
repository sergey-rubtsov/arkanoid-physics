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
	
	@Override
	public void impact(PhysicalObject body) {
		int i;
		i = 0;
		i++;		
	}
	
	Vector2 impulseForBall(Body ball) {
		if (this.kick <= 0) return null;
		// compute unit vector from center of peg to ball, and scale by kick value to get impulse
		Vector2 ballpos = ball.getWorldCenter();
		//float ix = ballpos.x - this.cx;
		//float iy = ballpos.y - this.cy;
		//float mag = (float)Math.sqrt(ix * ix + iy * iy);
		//float scale = this.kick / mag;
		//return new Vector2(ix * scale, iy * scale);
		return null;
	}

	public void handleCollision (Body ball) {
		Vector2 impulse = this.impulseForBall(ball);
		if (impulse != null) {
			ball.applyLinearImpulse(impulse, ball.getWorldCenter(), true);
		}		
	}
}
