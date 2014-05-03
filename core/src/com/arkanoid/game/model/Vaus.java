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
}
