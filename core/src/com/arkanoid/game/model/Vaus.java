package com.arkanoid.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Vaus extends PhysicalObject {
	
	public final Rectangle rectangle;
	
	public Vaus(World world, float x, float y, float width, float height) {
		this.rectangle = new Rectangle(x - width / 2, y - height / 2, width, height);
		super.setBody(BodyFactory.createKinematicRectangle(world, x, y, width, height));
	}
	
	public Rectangle getRectangle() {
		rectangle.x = super.getXPos();
		rectangle.y = super.getYPos();
		return rectangle;
	}
}
