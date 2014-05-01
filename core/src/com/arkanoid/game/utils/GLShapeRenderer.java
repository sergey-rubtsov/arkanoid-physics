package com.arkanoid.game.utils;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Shape;

public class GLShapeRenderer extends ShapeRenderer {	
	
	public GLShapeRenderer() {
		super(500);
	}

	public void drawLine(float x1, float y1, float x2, float y2, int r, int g,
			int b) {
		float fr = r / 255f;
		float fg = g / 255f;
		float fb = b / 255f;
		setColor(fr, fg, fb, 1);
		line(x1, y1, x2, y2);
	}

	public void fillCircle(float cx, float cy, float radius, int r, int g, int b) {
		end();
		begin(ShapeType.Filled);
		float fr = r / 255f;
		float fg = g / 255f;
		float fb = b / 255f;
		setColor(fr, fg, fb, 1);
		circle(cx, cy, radius, 20);
		end();
		begin(ShapeType.Line);		
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
		begin(ShapeType.Line);		
	}

	public void fillRectangle(float x, float y, float width, float height) {
		end();
		begin(ShapeType.Filled);
		float fr = 255 / 255f;
		float fg = 50 / 255f;
		float fb = 50 / 255f;
		setColor(fr, fg, fb, 1);
		rect(x + width / 2, y + height / 2, width, height);
		end();
		begin(ShapeType.Line);		
	}

	public void fillRectangle(Rectangle rectangle) {
		fillRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);		
	}
	
	public void fillShape(Shape shape) {
		
	}
	
	public void begin() {
		begin(ShapeType.Line);
	}

}
