package com.arkanoid.game.model;


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
	
	public void setRestitution(float restitution) {
		for (Fixture fixture : body.getFixtureList()) {
			fixture.setRestitution(restitution);
		}		
	}
	
	public float getXPos() {
		return this.body.getPosition().x;
	}
	
	public float getYPos() {
		return this.body.getPosition().y;
	}
	
	public abstract void impact(PhysicalObject body);
}
