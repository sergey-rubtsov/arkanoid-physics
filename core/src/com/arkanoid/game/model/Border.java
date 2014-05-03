package com.arkanoid.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Border extends PhysicalObject {	

	public Border(World world) {
		super.setBody(BodyFactory.createBorder(world));
		getBody().setActive(true);
	}

	@Override
	public void impact(PhysicalObject object) {		
		if (object.getClass() == Vaus.class) {
			float back = -2;
			Vector2 vel = object.getBody().getLinearVelocity();
			vel.x = vel.x * back;
			object.getBody().setLinearVelocity(vel);
			
		}
	}

}
