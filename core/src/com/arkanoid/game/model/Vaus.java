package com.arkanoid.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Vaus extends PhysicalObject {
	
	public final Rectangle rectangle;
	public final float width;
	public final float heigth;
	
	public Vaus(World world, float x, float y, float width, float height) {
		this.width = width;
		this.heigth = height;
		this.rectangle = new Rectangle(x - width, y - height, width, height);
		super.setBody(BodyFactory.createKinematicRectangle(world, x, y, width, height));
		getBody().setActive(true);
		getBody().setBullet(true);
		getBody().setUserData(this);
	}
	
	public PolygonShape getShape() {
		return (PolygonShape)getBody().getFixtureList().get(0).getShape();
	}
	
	public Rectangle getRectangle() {
		rectangle.x = super.getXPos() - this.width;
		rectangle.y = super.getYPos() - this.heigth;
		return rectangle;
	}
	
	public void handleCollision(Body ball) {
		//Vector2 impulse = this.impulseForBall(ball);
		//ball.applyLinearImpulse(impulse, ball.getWorldCenter(), true);
	}
	
	Vector2 impulseForBall(Body ball) {		
		// compute unit vector from center of peg to ball, and scale by kick value to get impulse
		Vector2 ballpos = ball.getWorldCenter();
		float ix = ballpos.x - this.getXPos();
		float iy = ballpos.y - this.getYPos();
		float mag = (float)Math.sqrt(ix * ix + iy * iy);
		float scale = 0.1f / mag;
		return new Vector2(ix * scale, iy * scale);
	}

	@Override
	public void impact(PhysicalObject body) {
		if (body.getClass() == Ball.class) {
			float accX = body.getBody().getLinearVelocity().x * 200;
			float accY = body.getBody().getLinearVelocity().y * 200;
			this.getBody().applyForce(accX, accY, getXPos(), getYPos(), true);
		}
	}
}
