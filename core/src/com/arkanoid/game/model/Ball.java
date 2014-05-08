package com.arkanoid.game.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Ball extends PhysicalObject {
	
	private final float radius;
	
	//0001 in binary
	final public static short CATEGORY_BALL = 0x0001;
	
	public Ball(World world, float x, float y, float radius, float restitution, float friction) {
		this.radius = radius;
		super.setBody(createBall(world, x, y, radius, restitution, friction));
		getBody().setActive(true);
		getBody().setBullet(true);
		getBody().setUserData(this);
	}
	
	public Ball(World world, float x, float y, float radius) {
		this(world, x, y, radius, 10f, 0f);
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

	@Override
	public float getBottomLeftXPos() {
		return getXPos() - radius;
	}

	@Override
	public float getBottomLeftYPos() {
		return getYPos() - radius;
	}
	
	public static Body createBall(World world, float x, float y, float radius, float restitution, float friction) {
		BodyDef bd = new BodyDef();
		bd.position.set(x, y);
		bd.type = BodyDef.BodyType.DynamicBody;
		CircleShape cs = new CircleShape();
		cs.setRadius(radius);
		
		Body body = world.createBody(bd);
		Fixture fixture = body.createFixture(cs, 1);
		fixture.setFriction(friction);	
		fixture.setRestitution(restitution);
		
		Filter f = new Filter();
		f.categoryBits = CATEGORY_BALL;
		f.maskBits = ContactFilter.MASK_BALL;
		fixture.setFilterData(f);	
		return body;
	}
}
