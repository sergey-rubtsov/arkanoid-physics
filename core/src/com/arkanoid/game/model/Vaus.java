package com.arkanoid.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Vaus extends PhysicalObject {
	
	public final Rectangle rectangle;

	static final float VAUS_HEIGHT = 5;
	static final float VAUS_WIDTH = 20;
	
	public Vaus(World world, float x, float y) {
		this.rectangle = new Rectangle(x - VAUS_WIDTH / 2, y - VAUS_HEIGHT / 2, VAUS_WIDTH, VAUS_HEIGHT);
		super.setBody(BodyFactory.createRectangle(world, x, y, VAUS_WIDTH, VAUS_HEIGHT));
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
}
