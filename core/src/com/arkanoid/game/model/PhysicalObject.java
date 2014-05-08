package com.arkanoid.game.model;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public abstract class PhysicalObject {
	
	private Body body;
	
	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}
	
	public void setRestitutionAndFriction(float restitution, float friction) {
		for (Fixture fixture : body.getFixtureList()) {
			fixture.setRestitution(restitution);
			fixture.setFriction(friction);
		}		
	}
	
	public float getXPos() {
		return this.body.getPosition().x;
	}
	
	public float getYPos() {
		return this.body.getPosition().y;
	}
	
	public Vector2 getPosition() {
		return this.body.getPosition();
	}
	
	public abstract float getBottomLeftXPos();
	
	public abstract float getBottomLeftYPos();
}
