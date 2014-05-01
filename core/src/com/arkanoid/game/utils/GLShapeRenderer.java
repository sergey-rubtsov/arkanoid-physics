package com.arkanoid.game.utils;

import java.util.Iterator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Shape.Type;
import com.badlogic.gdx.utils.Array;

public class GLShapeRenderer extends ShapeRenderer {
	
	private final static Array<Body> bodies = new Array<Body>();
	private final static Vector2[] vertices = new Vector2[500];
	public final Color RECCOLOR = new Color(0.5f, 0.5f, 0.3f, 1);
	public final Color CIRCOLOR = new Color(0.9f, 0.5f, 0.5f, 1);
	public final Color LINCOLOR = new Color(0.5f, 0.5f, 0.9f, 1);
	public final Color DEFCOLOR = new Color(0.6f, 0.6f, 0.6f, 1);
	
	public GLShapeRenderer() {
		//super(vertices.length);
		for (int i = 0; i < vertices.length; i++)
			vertices[i] = new Vector2();
	}
	
	/** This assumes that the projection matrix has already been set. */
	public void render(World world, Matrix4 projMatrix) {
		setProjectionMatrix(projMatrix);
		renderBodies(world);
	}

	private void renderBodies(World world) {
		begin(ShapeType.Line);
			world.getBodies(bodies);
			for (Iterator<Body> iter = bodies.iterator(); iter.hasNext();) {
				Body body = iter.next();
				renderBody(body);
			}
		end();
	}

	protected void renderBody(Body body) {
		Transform transform = body.getTransform();
		for (Fixture fixture : body.getFixtureList()) {
			drawShape(fixture, transform);
		}
	}
	
	private static Vector2 t = new Vector2();
	private static Vector2 axis = new Vector2();
	
	private void drawShape(Fixture fixture, Transform transform) {
		if (fixture.getType() == Type.Circle) {
			CircleShape circle = (CircleShape)fixture.getShape();
			t.set(circle.getPosition());
			transform.mul(t);
			drawSolidCircle(t, circle.getRadius(), axis.set(transform.vals[Transform.COS], transform.vals[Transform.SIN]), CIRCOLOR);
			return;
		}

		if (fixture.getType() == Type.Edge) {
			EdgeShape edge = (EdgeShape)fixture.getShape();
			edge.getVertex1(vertices[0]);
			edge.getVertex2(vertices[1]);
			transform.mul(vertices[0]);
			transform.mul(vertices[1]);
			drawSolidPolygon(vertices, 2, DEFCOLOR, true);
			return;
		}

		if (fixture.getType() == Type.Polygon) {
			PolygonShape polygon = (PolygonShape)fixture.getShape();
			int vertexCount = polygon.getVertexCount();
			for (int i = 0; i < vertexCount; i++) {
				polygon.getVertex(i, vertices[i]);
				transform.mul(vertices[i]);
			}
			drawSolidPolygon(vertices, vertexCount, RECCOLOR, true);			
			return;
		}

		if (fixture.getType() == Type.Chain) {
			ChainShape chain = (ChainShape)fixture.getShape();
			int vertexCount = chain.getVertexCount();
			for (int i = 0; i < vertexCount; i++) {
				chain.getVertex(i, vertices[i]);
				transform.mul(vertices[i]);
			}
			drawSolidPolygon(vertices, vertexCount, LINCOLOR, false);			
		}
	}
	
	public void drawConvexQuadrangle(PolygonShape shape, Color color) {
		if (shape.getVertexCount() != 4) throw new IllegalArgumentException("Tetragon must contain 4 points.");		
		setColor(color);
		end();
		begin(ShapeType.Filled);
		triangle(vertices[0].x, vertices[0].y, vertices[1].x, vertices[1].y, vertices[2].x, vertices[2].y);
		triangle(vertices[0].x, vertices[0].y, vertices[3].x, vertices[3].y, vertices[2].x, vertices[2].y);
		end();
	}

	private final Vector2 f = new Vector2();
	private final Vector2 v = new Vector2();
	private final Vector2 lv = new Vector2();
	
	private void drawSolidCircle (Vector2 center, float radius, Vector2 axis, Color color) {
		float angle = 0;
		float angleInc = 2 * (float)Math.PI / 20;
		setColor(color.r, color.g, color.b, color.a);
		for (int i = 0; i < 20; i++, angle += angleInc) {
			v.set((float)Math.cos(angle) * radius + center.x, (float)Math.sin(angle) * radius + center.y);
			if (i == 0) {
				lv.set(v);
				f.set(v);
				continue;
			}
			line(lv.x, lv.y, v.x, v.y);
			lv.set(v);
		}
		line(f.x, f.y, lv.x, lv.y);
		line(center.x, center.y, 0, center.x + axis.x * radius, center.y + axis.y * radius, 0);
	}
	
	public void fillCircle(float cx, float cy, float radius, int r, int g, int b) {
		begin(ShapeType.Filled);
		float fr = r / 255f;
		float fg = g / 255f;
		float fb = b / 255f;
		setColor(fr, fg, fb, 1);
		circle(cx, cy, radius, 20);
		end();
	}

	public void frameCircle(float cx, float cy, float radius, int r, int g,
			int b) {
		begin(ShapeType.Line);
		float fr = r / 255f;
		float fg = g / 255f;
		float fb = b / 255f;
		setColor(fr, fg, fb, 1);
		circle(cx, cy, radius, 20);
		end();	
	}

	private void drawSolidPolygon(Vector2[] vertices, int vertexCount, Color color, boolean closed) {
		setColor(color.r, color.g, color.b, color.a);
		lv.set(vertices[0]);
		f.set(vertices[0]);
		for (int i = 1; i < vertexCount; i++) {
			Vector2 v = vertices[i];
			line(lv.x, lv.y, v.x, v.y);
			lv.set(v);
		}
		if (closed) line(f.x, f.y, lv.x, lv.y);
	}
	
	public void fillRectangle(float x, float y, float width, float height) {
		begin(ShapeType.Filled);
		float fr = 255 / 255f;
		float fg = 50 / 255f;
		float fb = 50 / 255f;
		setColor(fr, fg, fb, 1);
		rect(x + width / 2, y + height / 2, width, height);
		end();		
	}

	public void fillRectangle(Rectangle rectangle) {
		fillRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);		
	}
	
	public void fillShape(Shape shape) {
		
	}
	
	public void dispose () {
		super.dispose();
	}

}
