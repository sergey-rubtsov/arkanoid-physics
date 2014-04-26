package com.arkanoid.game.model;

import com.badlogic.gdx.physics.box2d.Body;

public class PhysicalObject {
	private Body body;
	
	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}
	
	public float getXPos() {
		return this.body.getPosition().x;
	}
	
	public float getYPos() {
		return this.body.getPosition().y;
	}

}
