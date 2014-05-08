package com.arkanoid.game.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Brick extends PhysicalObject {
	
	private final int resistance;
	private final float kick;
	public int life; 
	public final float width;
	public final float height;
	public final Property property;
	
	//0100 in binary
	final public static short CATEGORY_BRICK = 0x0004;
	
	public enum Property {
		DISAPPEARS, FALLS, EXPLODES, GELATIN, DEFAULT
	}
	
	public Brick(World world, float x, float y, float width, float height) {
		this(world, x, y, width, height, 1, 1, 2, Property.DEFAULT);
	}
	
	public Brick(World world, float x, float y, float width, float height, Property property) {
		this(world, x, y, width, height, 1, 1, 2, property);
	}

	public Brick(World world, float x, float y, float width, float height, int resistance, float kick, int life, Property property) {
		this.resistance = resistance;
		this.kick = kick;
		this.life = life;
		this.property = property;
		this.width = width;
		this.height = height;
		super.setBody(createBrick(world, x, y, width, height));
		getBody().setUserData(this);
	}

	public int getResistance() {
		return resistance;
	}
	
	public int getLife() {
		return life;
	}
	
	public boolean hit() {
		life--;
		if (life <= 0) return true;
		return false;
	}
	
	public void destroyUserData() {
		getBody().setUserData(null);		
	}

	@Override
	public float getBottomLeftXPos() {
		return getXPos() - (width / 2);
	}

	@Override
	public float getBottomLeftYPos() {
		return getYPos() - (height / 2);
	}
	
	public static Body createBrick(World world, float x, float y,
			float width, float height) {
		BodyDef bd = new BodyDef();
		bd.position.set(x, y);
		bd.type = BodyDef.BodyType.StaticBody;		
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, height / 2);

		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.restitution = 10f;
		fdef.friction = 1f;
		fdef.filter.categoryBits = CATEGORY_BRICK;
		fdef.filter.maskBits = ContactFilter.MASK_BRICK;
		
		Body rectangle = world.createBody(bd);
		
		rectangle.createFixture(fdef);
		
		shape.dispose();
		return rectangle;
	}
}
