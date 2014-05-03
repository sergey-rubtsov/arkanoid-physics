
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
	private static BodyDef bd;
	
	/** Creates a circle object with the given position and radius. Resitution defaults to 0.6. */
	public static Body createCircle(World world, float x, float y, float radius, boolean isStatic) {
		CircleShape sd = new CircleShape();
		sd.setRadius(radius);

		FixtureDef fdef = new FixtureDef();
		fdef.shape = sd;
		fdef.density = 1.0f;
		fdef.friction = 0.3f;
		fdef.restitution = 5f;

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

	/** Creates a wall by constructing a rectangle whose corners are (xmin,ymin) and (xmax,ymax), and rotating the box
	 * counterclockwise through the given angle. Restitution defaults to 0.5. */
	public static Body createWall(World world, float xmin, float ymin, float xmax, float ymax, float angle) {
		return createWall(world, xmin, ymin, xmax, ymax, angle, 0f);
	}

	/** Creates a wall by constructing a rectangle whose corners are (xmin,ymin) and (xmax,ymax), and rotating the box
	 * counterclockwise through the given angle, with specified restitution. */
	public static Body createWall(World world, float xmin, float ymin, float xmax, float ymax, float angle, float restitution) {
		float cx = (xmin + xmax) / 2;
		float cy = (ymin + ymax) / 2;
		float hx = (xmax - xmin) / 2;
		float hy = (ymax - ymin) / 2;
		if (hx < 0) hx = -hx;
		if (hy < 0) hy = -hy;
		PolygonShape wallshape = new PolygonShape();
		wallshape.setAsBox(hx, hy, new Vector2(0f, 0f), angle);

		FixtureDef fdef = new FixtureDef();
		fdef.shape = wallshape;
		fdef.density = 1.0f;
		if (restitution > 0) fdef.restitution = restitution;

		BodyDef bd = new BodyDef();
		bd.position.set(cx, cy);
		Body wall = world.createBody(bd);
		wall.createFixture(fdef);
		wall.setType(BodyDef.BodyType.StaticBody);
		return wall;
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
		fdef.restitution = 1f;
		fdef.friction = 2f;
		
		Body rectangle = world.createBody(bd);
		
		rectangle.createFixture(fdef);
		
		shape.dispose();
		return rectangle;
	}
	
	public static Body createBorder(World world) {
		Vector2[] vectors = new Vector2[4];
		vectors[0] = new Vector2(1, 1);
		vectors[1] = new Vector2(1, GameField.WORLD_HEIGHT - 1);
		vectors[2] = new Vector2(GameField.WORLD_WIDTH - 1, GameField.WORLD_HEIGHT - 1);
		vectors[3] = new Vector2(GameField.WORLD_WIDTH - 1, 1);
		return createBorder(world, vectors);
	}
	
	public static Body createBorder(World world, Vector2[] vectors) {
		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		ChainShape borderShape = new ChainShape();
		borderShape.createChain(vectors);
		FixtureDef fdef = new FixtureDef();
		fdef.shape = borderShape;
		fdef.restitution = 1f;
		fdef.friction = 0.5f;
		
		Body border = world.createBody(bd);		
		border.createFixture(fdef);
		
		borderShape.dispose();
		return border;		
	}
	
	public static Body createLine(World world, float x1, float y1, float x2, float y2) {
		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;
		ChainShape borderShape = new ChainShape();
		borderShape.createChain(new Vector2[] {new Vector2(x1, y1), new Vector2(x2, y2)});
		FixtureDef fdef = new FixtureDef();
		fdef.shape = borderShape;
		fdef.restitution = 1f;
		fdef.friction = 0.5f;
		
		Body line = world.createBody(bd);		
		line.createFixture(fdef);
		
		borderShape.dispose();
		return line;
	}

	/** Creates a segment-like thin wall with 0.05 thickness going from (x1,y1) to (x2,y2) */
	public static Body createThinWall(World world, float x1, float y1, float x2, float y2, float restitution) {
		// determine center point and rotation angle for createWall
		float cx = (x1 + x2) / 2;
		float cy = (y1 + y2) / 2;
		float angle = (float)Math.atan2(y2 - y1, x2 - x1);
		float mag = (float)Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		return createWall(world, cx - mag / 2, cy - 0.05f, cx + mag / 2, cy + 0.05f, angle, restitution);
	}
}
