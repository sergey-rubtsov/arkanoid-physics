package com.arkanoid.game.model;

import com.badlogic.gdx.physics.box2d.World;

public class Border extends PhysicalObject {	

	public Border(World world) {
		super.setBody(BodyFactory.createBorder(world));
		getBody().setActive(true);
	}
}
