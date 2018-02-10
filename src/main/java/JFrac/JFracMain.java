package JFrac;

/**
 * File:	JFracMain.java
 * Date:	1/29/2018
 * Author:	Stephen Sanchagrin
 * Purpose:	Fun exercise to expereiment around with some new GUI
 * 		components and features.
 */

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class JFracMain extends JFrame {

	// Fields
	DisplayPanel jpt;

	/**
	 * main. Main method and entry point for the application.
	 */
	public static void main(String[] args) {
		JFracMain jf = new JFracMain();
	}

	/**
	 *  Constructor
	 */
	public JFracMain() {
		System.out.println("JFrac v 0.1");

		// Set the frame properties
		setTitle("JFrac v 0.1");
		setSize(600,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Initalize JButtons
		JButton jblc = new JButton("Line Color");
		JButton jbbgc = new JButton("Background Color");

		// Initalize JComboBox
		JComboBox jcb = new JComboBox<String>();
		jcb.addItem("<Make Selection>");
		jcb.addItem("Recursive Circles");
		jcb.addItem("Cantor Set");
		jcb.addItem("Fractal");

		// Initialize the JPanels and add the components
		jpt = new DisplayPanel();
		JPanel jpb = new JPanel();
		jpb.add(jcb);
		jpb.add(jblc);
		jpb.add(jbbgc);

		// Add the JPanels to the frame
		add(jpt, BorderLayout.CENTER);
		add(jpb, BorderLayout.PAGE_END);
		setVisible(true);
		validate();

		// Component Action Listners
		jblc.addActionListener (e -> selectColor(jblc.getText()));
		jbbgc.addActionListener (e -> selectColor(jbbgc.getText()));
		jcb.addActionListener (e -> selectDisplay((String)jcb.getSelectedItem()));
	}

	/**
	 * selectColor. Determines either background or line color based on the calling button. Opens a
	 * JColorSelector and sets the appropriate field in the DisplayPanel.
	 * @param button Input button's text.
	 */
	private void selectColor(String button) {
		if(button.equals("Line Color"))
			jpt.setLineColor(JColorChooser.showDialog(this, "Select Line Color", Color.YELLOW));		
		if(button.equals("Background Color"))
			jpt.setBackgroundColor(JColorChooser.showDialog(this, "Select Line Color", Color.BLACK));		
	}

	/**
	 * selectDisplay. Determines the JComboBox selection and sets the appropriate field in the DisplayPanel.
	 */
	private void selectDisplay(String selected) {
		jpt.setSelection(selected);
	}

}
