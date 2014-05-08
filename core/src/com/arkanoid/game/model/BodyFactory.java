
package com.arkanoid.game.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class BodyFactory {

	public static Body createCircle(World world, float x, float y, float radius, BodyDef.BodyType type) {
		CircleShape sd = new CircleShape();
		sd.setRadius(radius);

		FixtureDef fdef = new FixtureDef();
		fdef.shape = sd;
		fdef.density = 10.0f;
		fdef.friction = 0f;
		fdef.restitution = 10f;

		BodyDef bd = new BodyDef();
		
		bd.allowSleep = false;
		bd.position.set(x, y);
		Body body = world.createBody(bd);
		
		body.createFixture(fdef);
		body.setType(type);
		
		//body.setActive(true);
		
		sd.dispose();		
		return body;
	}
}
