package a10;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * This class displays sample on an XY graph and draws lines to the nearest neighbors.
 * Samples are colored by their classification. 
 * The unknown shows up as yellow.
 * @author dejohnso
 * @author Spencer Fajardo and Serena Neel
 */
public class SampleDisplayPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ClassifierGUI gui;
	
	public SampleDisplayPanel(ClassifierGUI initGUI) {
		super();
		gui = initGUI;
	}

	/**
	 * Positions the oval centered at x,y rather than at a corner.
	 * @param g2d
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	private void drawCenteredOval(Graphics2D g2d, int x, int y, int width, int height) {
		g2d.fillOval(x - width/2,  y- height/2,  width,  height);
	}
	
	/* 
	 * Draws the sample on a XY graph and connects to nearest neighbors.
	 */
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int h = getHeight();
		int w = getWidth();
	    g2d.translate(0, h);
	    g2d.scale(1.0, -1.0);
		List<Face> kClosest = gui.getClassifier().getkClosestSet();
		Face unknown = gui.getClassifier().getUnknown();

		// Draw the nearest neighbor lines first show they appear "under" the samples.
		if (kClosest != null) {
			for (Face face : kClosest) {
				g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(2));
				g2d.drawLine((int)(face.getX()*w),  (int)(face.getY()*h),  (int)(unknown.getX()*w), (int)(unknown.getY()*h));
			}
		}

		// Draw the samples as colored circles.
		for (Face face : gui.getClassifier().getSamples()) {
			if (face.getClassification().equals("smiling"))
				g2d.setColor(Color.GREEN);
			if (face.getClassification().equals("neutral"))
				g2d.setColor(Color.GRAY);
			if (face.getClassification().equals("surprised"))
				g2d.setColor(Color.RED);
			drawCenteredOval(g2d, (int)(face.getX()*w),  (int)(face.getY()*h),  12,  12);
			// Draw the unknown as a larger yellow dot.
			if (unknown != null) {
				g2d.setColor(Color.YELLOW);
				drawCenteredOval(g2d, (int)(unknown.getX()*w),  (int)(unknown.getY()*h),  15,  15);
			}
		}
	}
}
