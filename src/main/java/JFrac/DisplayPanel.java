package JFrac;

/**
 * File:	DisplayPanel.java
 * Date:	1/29/2018
 * Author:	Stephen Sanchagrin
 * Purpose:	Creates a DisplayPanel object used for showing
 * 		fractal drawings.
 */

import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class DisplayPanel extends JPanel {
	final static BasicStroke stroke = new BasicStroke(2.0f);
	private Color bgColor;
	private Color lnColor;
	private int pWidth;
	private int pHeight;
	private int pCenterW;
	private int pCenterH;
	private String selection;
	
	/** 
	 * Constructor.
	 * @param None.
	 */
	DisplayPanel() {
		setOpaque(true);
		setBackground(Color.BLACK);
		lnColor = Color.YELLOW;
		bgColor = Color.BLACK;
		selection = "";
	}

	/**
	 * drawCircles. Recursive function that draws circles of decreasing size.
	 * @param radius circle radius
	 * @param g Graphics element for the DisplayPanel
	 */
	public void drawCircles(int radius, Graphics g) {
		g.drawOval(pCenterW-radius/2, pCenterH-radius/2, radius, radius);
		if(radius>2) {
			radius *= 0.75;
			drawCircles(radius, g);
		}
	}

	/**
	 * drawLines. Recursive function that draws a Cantor Set.
	 * @param x1 Starting x position for each line
	 * @param y1 Starting y position for each line
	 * @param x2 Ending x position for each line
	 * @param y2 Ending y position for each line
	 * @param g Graphics object for the DisplayPanel
	 */
	public void drawLines(int x1, int y1, int x2, int y2, Graphics g) {
		g.drawLine(x1, y1, x2, y2);

		int dx = x2-x1;
		int dy = y2-y1;

		if (dx == 0 && dy > 4) {
			drawLines(x1-dy/3, y1, x1+dy/3, y1, g);
			drawLines(x1-dy/3, y2, x1+dy/3, y2, g);
		} else if (dy == 0 && dx > 4) {
			drawLines(x1, y1-dx/3, x1, y1+dx/3, g);
			drawLines(x2, y1-dx/3, x2, y1+dx/3, g);
		}
	}

	/**
	 * drawFractal. Draws a simple fractal.
	 * @param level level of recusion to obtain
	 * @param x1 Starting x position for each line
	 * @param y1 Starting y position for each line
	 * @param x2 Ending x position for each line
	 * @param y2 Ending y position for each line
	 * @param g Graphics object for the DisplayPanel
	 */
	public void drawFractal(int level, int x1, int y1, int x2, int y2, Graphics g) {
		if(level==0)
			g.drawLine(x1, y1, x2, y2);
		else {
			int midX = (x1 + x2)/2;
			int midY = (y1 + y2)/2;
			
			int x3 = x1 + (midX - x1)/2 - (midY - y1)/2;
			int y3 = y1 + (midY - y1)/2 + (midX - x1)/2;

			drawFractal(level-1, x3, y3, x1, y1, g);
			drawFractal(level-1, x3, y3, midX, midY, g);
			drawFractal(level-1, x3, y3, x2, y2, g);
		}
	}

	/**
	 * setLineColor. Sets the line color for each drawing.
	 * @param c Color object to use selection
	 */
	public void setLineColor(Color c) {
		lnColor = c;
		repaint();
	}

	/**
	 * setBackgorundColor. Sets the background color for each drawing.
	 * @param c Color object to use selection
	 */
	public void setBackgroundColor(Color c) {
		bgColor = c;
		repaint();
	}

	/**
	 * setSelection. Sets the varaible used to determine which fractal to draw.
	 * @param s String value of the JComboBox in the main application.
	 */
	public void setSelection(String s) {
		selection = s;
		repaint();
	}

	/**
	 * paintComponent. Overriden method used to paint the Graphics object in the
	 * DisplayPanel.
	 * @param g Graphics object to paint.
	 */
	@Override
	public void paintComponent(Graphics g) {

		// Set the background and line color
		super.paintComponent(g);
		setBackground(bgColor);
		g.setColor(lnColor);

		// Get the DisplayPanel width, height and calculate the center
		pWidth = getWidth();
		pHeight = getHeight();
		pCenterW = pWidth/2;
		pCenterH = pHeight/2;

		// Determine which fractal to display
		if(selection.equals("Recursive Circles")) 
			drawCircles(400, g);
		else if(selection.equals("Cantor Set")) 
			drawLines(10, pCenterH, pWidth-10, pCenterH, g);
		else if(selection.equals("Fractal")) 
			drawFractal(15, 100, 90, 480, 300, g);
		else {
			g.setFont(new Font("Helvetica", Font.BOLD, 24));
			g.drawString("JFrac v 0.1", pCenterW-60, pCenterH-10);
		}
	}

}
