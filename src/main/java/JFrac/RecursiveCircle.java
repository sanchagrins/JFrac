package JFrac;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class RecursiveCircle {
	private float x;
	private float y;
	private float radius;
	private Ellipse2D.Float ellipse;
	private ArrayList<Ellipse2D.Float> circles;

	RecursiveCircle(float x, float y, float radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		circles = new ArrayList<Ellipse2D.Float>();
	}

	public void drawCircles(float x, float y, float radius) {
		if(radius > 2) {
			circles.add(new Ellipse2D.Float(x, y, radius/2, radius/2));
			radius *= 0.75f;
			drawCircles(x, y, radius);
		}
	}

}
