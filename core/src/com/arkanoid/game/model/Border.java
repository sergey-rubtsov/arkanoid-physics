package com.arkanoid.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Border extends PhysicalObject {
	
	//1000 in binary
	final public static short CATEGORY_BORDER = 0x0008; 

	public Border(World world) {
		this(world, getDefaultVectors(), 10f, 0f);
	}
	
	public Border(World world, Vector2[] vectors, float restitution, float friction) {
		super.setBody(createBorder(world, vectors, restitution, friction));
		getBody().setActive(true);
		getBody().setUserData(this);		
	}

	@Override
	public float getBottomLeftXPos() {
		return getXPos();
	}

	@Override
	public float getBottomLeftYPos() {
		return getYPos();
	}
	
	public static Vector2[] getDefaultVectors() {
		Vector2[] vectors = new Vector2[4];
		vectors[0] = new Vector2(1, 0);
		vectors[1] = new Vector2(0, GameField.WORLD_HEIGHT - 1);
		vectors[2] = new Vector2(GameField.WORLD_WIDTH, GameField.WORLD_HEIGHT - 1);
		vectors[3] = new Vector2(GameField.WORLD_WIDTH - 1, 0);
		return vectors;
	}
	
	public static Body createBorder(World world, Vector2[] vectors, float restitution, float friction) {
		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;
		ChainShape borderShape = new ChainShape();
		borderShape.createChain(vectors);
		FixtureDef fdef = new FixtureDef();
		fdef.shape = borderShape;
		fdef.restitution = restitution;
		fdef.friction = friction;
		fdef.filter.categoryBits = CATEGORY_BORDER;
		fdef.filter.maskBits = ContactFilter.MASK_BORDER;
		
		Body border = world.createBody(bd);		
		border.createFixture(fdef);
		
		borderShape.dispose();
		return border;		
	}
	
	
}
