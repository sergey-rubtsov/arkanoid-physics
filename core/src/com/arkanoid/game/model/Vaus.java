package com.arkanoid.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Vaus extends PhysicalObject {
	
	public final Rectangle rectangle;
	public final float width;
	public final float height;
	
	//0010 in binary
	final public static short CATEGORY_VAUS = 0x0002;
	
	public Vaus(World world, float x, float y, float width, float height, float restitution, float friction) {
		this.width = width;
		this.height = height;
		this.rectangle = new Rectangle(x - width, y - height, width, height);		
		super.setBody(createVaus(world, x, y, width, height, restitution, friction));
		getBody().setActive(true);
		getBody().setBullet(true);
		getBody().setUserData(this);
	}
	
	public Vaus(World world, float x, float y, float width, float height) {
		this(world, x, y, width, height, 10f, 0f);
	}
	
	public PolygonShape getShape() {
		return (PolygonShape)getBody().getFixtureList().get(0).getShape();
	}
	
	public Rectangle getRectangle() {
		rectangle.x = super.getXPos() - this.width;
		rectangle.y = super.getYPos() - this.height;
		return rectangle;
	}
	
	@Override
	public float getBottomLeftXPos() {
		return getXPos() - (width / 2);
	}

	@Override
	public float getBottomLeftYPos() {
		return getYPos() - (height / 2);
	}
	
	public static Body createVaus(World world, float x, float y, float width, float height, float restitution, float friction) {
		BodyDef bd = new BodyDef();
		bd.position.set(x, y);
		bd.type = BodyDef.BodyType.KinematicBody;		
		
		Body vaus = world.createBody(bd);
		
		PolygonShape polygon = new PolygonShape();		
		polygon.setAsBox(width / 2, height / 2);
		Fixture center = vaus.createFixture(polygon, 10);
		center.setRestitution(restitution);
		center.setFriction(friction);
		
		CircleShape leftSide = new CircleShape();
		CircleShape rightSide = new CircleShape();
		leftSide.setRadius(height / 2);
		rightSide.setRadius(height / 2);
		leftSide.setPosition(new Vector2(-width / 2 + height / 4, 0));
		rightSide.setPosition(new Vector2(width / 2 - height / 4, 0));
		
		Fixture left = vaus.createFixture(leftSide, 10);		
		Fixture right = vaus.createFixture(rightSide, 10);
		left.setRestitution(restitution);
		right.setRestitution(restitution);
		left.setFriction(friction);
		right.setFriction(friction);
		
		Filter f = new Filter();
		f.groupIndex = 1;
		f.categoryBits = CATEGORY_VAUS;
		f.maskBits = ContactFilter.MASK_VAUS;
		right.setFilterData(f);
		left.setFilterData(f);
		center.setFilterData(f);
		
		polygon.dispose();
		rightSide.dispose();
		leftSide.dispose();
		return vaus;
	}
}
