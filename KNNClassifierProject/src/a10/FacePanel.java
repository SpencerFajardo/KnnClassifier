package a10;


import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

/** 
 * A JPanel for displaying a BufferedImage.
 *
 * @author David Johnson
 * @author Spencer Fajardo and Serena Neel
 */
public class FacePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	// Hold several images and an index to which one is displaying
	private ArrayList<BufferedImage> images;
	private int frameNumber;
	private int width;
	private int height;
	private Face face;
	
	/***
	 * Construct a panel with a starter image
	 * @param img
	 */
	public FacePanel(Face initFace, int setWidth, int setHeight) {
		face = initFace;
		images = new ArrayList<BufferedImage>();
		images.add(face.getImage());		
		frameNumber = 0;
		width = setWidth;
		height = setHeight;
		
		setPreferredSize(new Dimension(setWidth, setHeight));
	}
	
	/***
	 * Add img to the ArrayList of images
	 * @param img
	 */
	public void add(BufferedImage img) {
		images.add(img);
	}
	
	public Face getFace() {
		return face;
	}
	
	/***
	 * Cycle through the frames using the % mod operation
	 */
	public void nextFrame() {
		frameNumber = (frameNumber + 1)%images.size();		
	}

	/***
	 * Implement the paint method to draw the images
	 */
	@Override
	  public void paintComponent(Graphics g) {
		// Make sure to call the super method to draw backgrounds and such
		super.paintComponent(g);
		// Then draw the image on top
		BufferedImage img = images.get(frameNumber);
		g.drawImage(img,  
					0, 0, width, height, 
					0, 0, img.getWidth(), img.getHeight(),
					null);
	}
}
