
package com.arkanoid.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class BodyFactory {

	/** Creates a circle object with the given position and radius. Resitution defaults to 0.6. */
	public static Body createCircle(World world, float x, float y, float radius, boolean isStatic) {
		CircleShape sd = new CircleShape();
		sd.setRadius(radius);

		FixtureDef fdef = new FixtureDef();
		fdef.shape = sd;
		fdef.density = 10.0f;
		fdef.friction = 0.1f;
		fdef.restitution = 10f;

		BodyDef bd = new BodyDef();
		
		bd.allowSleep = false;
		bd.position.set(x, y);
		Body body = world.createBody(bd);
		
		body.createFixture(fdef);
		if (isStatic) {
			body.setType(BodyDef.BodyType.StaticBody);
		} else {
			body.setType(BodyDef.BodyType.DynamicBody);
		}
		//body.setActive(true);
		
		sd.dispose();		
		return body;
	}

	public static Body createKinematicRectangle(World world, float x, float y, float width, float height) {
		return createRectangle(world, x, y, width, height, BodyDef.BodyType.KinematicBody);
	}
	
	public static Body createStaticRectangle(World world, float x, float y,
			float width, float height) {
		return createRectangle(world, x, y, width, height, BodyDef.BodyType.StaticBody);
	}
	
	public static Body createRectangle(World world, float x, float y, float width, float height, BodyDef.BodyType type) {
		BodyDef bd = new BodyDef();
		bd.position.set(x, y);
		bd.type = type;		
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, height / 2);

		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.restitution = 10f;
		fdef.friction = 1f;
		
		Body rectangle = world.createBody(bd);
		
		rectangle.createFixture(fdef);
		
		shape.dispose();
		return rectangle;
	}
	
	public static Body createBorder(World world) {
		Vector2[] vectors = new Vector2[4];
		vectors[0] = new Vector2(1, 0);
		vectors[1] = new Vector2(0, GameField.WORLD_HEIGHT - 1);
		vectors[2] = new Vector2(GameField.WORLD_WIDTH, GameField.WORLD_HEIGHT - 1);
		vectors[3] = new Vector2(GameField.WORLD_WIDTH - 1, 0);
		return createBorder(world, vectors);
	}
	
	public static Body createBorder(World world, Vector2[] vectors) {
		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		ChainShape borderShape = new ChainShape();
		borderShape.createChain(vectors);
		FixtureDef fdef = new FixtureDef();
		fdef.shape = borderShape;
		fdef.restitution = 10f;
		fdef.friction = 0f;
		
		Body border = world.createBody(bd);		
		border.createFixture(fdef);
		
		borderShape.dispose();
		return border;		
	}
}
