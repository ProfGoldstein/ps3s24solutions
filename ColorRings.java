
// going to be lazy about imports in these examples...
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * A program to demonstrate drawing concentric rings that 
 * change color if clicked on.  A click outside of all the 
 * rings will change all of the rings' colors.
 * 
 * Passing true as a parameter to ColorRings will  allow the
 * user to drage the rings around
 * 
 * @author Ira Goldstein
 * @author Jim Teresco (based on DragMany)
 * @version Spring 2024
 */

public class ColorRings extends MouseAdapter implements Runnable {

	// some named constants
	public static final int NUM_RINGS = 5;
	public static final int PANEL_SIZE = 700;

	// our list of rings
	// note: normally we would declare as List, but with the "lazy"
	// imports above, we are importing two things that are "List"s
	private java.util.List<ColorRing> rings;

	// we have a variable that says where the mouse last was so we can move
	// the ring relative to that position, and this will be null
	// if the mouse is dragging but was not pressed on a ring 
	private Point lastMouse;

	// the ring being dragged
	private ColorRing dragging;

	// determins if dragging is available
	private static boolean drag = false;

	private JPanel panel;

	/**
	 * A constructor so we can receive the number of rings that

	 * 
	 */
	public ColorRings() {

	}

	/**
	 * The run method to set up the graphical user interface
	 */
	@Override
	public void run() {

		// set up the GUI "look and feel" which should match
		// the OS on which we are running
		JFrame.setDefaultLookAndFeelDecorated(true);

		// create a JFrame in which we will build our very
		// tiny GUI, and give the window a name
		JFrame frame = new JFrame("ColorRings");
		frame.setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));

		// tell the JFrame that when someone closes the
		// window, the application should terminate
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// JPanel with a paintComponent method
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {

				// first, we should call the paintComponent method we are
				// overriding in JPanel
				super.paintComponent(g);

				// for each ring we have, paint it on the Graphics object
				for (ColorRing s : rings) {
					s.paint(g);
				}

			}
		};
		frame.add(panel);
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);

		// construct our list of rings
		rings = new ArrayList<ColorRing>(NUM_RINGS);

		Random r = new Random();

		for (int i = 0; i < NUM_RINGS; i++) {
			int size = (i + 1) * 80;
			int position = (PANEL_SIZE/2 -(NUM_RINGS * 40)) + (NUM_RINGS - i - 1) * 40;
			Point point = new Point(position, position);
			Color color = new Color(r.nextInt(255),
					r.nextInt(255),
					r.nextInt(255));
			rings.add(new ColorRing(size, point, color));
		}

		// display the window we've created
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {

		// make sure we don't start dragging if we didn't press on a ring
		lastMouse = null;

		Point p = e.getPoint();
		
		// Flag if we clicked on any ring
		Boolean found = false;
		
		// if we pressed on a ring in our list, set up for dragging
		// note the reverse loop so we first encounter the object drawn on
		// top in the case of any overlap
		for (int i = rings.size() - 1; i >= 0; i--) {
			if (rings.get(i).contains(p)) {
				found = true;
				dragging = rings.get(i);
				lastMouse = p;
				// also move to the end of the list so this one's drawn on top
				rings.remove(i);
				rings.add(dragging);
				break;
			}
		}
		
		// change all of the colors if we did not click on a ring
		if (!found) {
			for (int i = rings.size() - 1; i >= 0; i--) {
				rings.get(i).changeColor();
			}
			panel.repaint();
		}	
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		// if we are dragging an object, update its position by the
		// amount the mouse has moved since the last press or drag
		// event
		if (lastMouse != null) {
			int dx = e.getPoint().x - lastMouse.x;
			int dy = e.getPoint().y - lastMouse.y;
			dragging.translate(dx, dy);
			dragging.changeColor();
			panel.repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		// if we are dragging an object, update its position by the
		// amount the mouse has moved since the last press or drag
		// event
		if ( !drag ) {
			lastMouse = null;
		}
		if (lastMouse != null) {
			int dx = e.getPoint().x - lastMouse.x;
			int dy = e.getPoint().y - lastMouse.y;
			dragging.translate(dx, dy);
			lastMouse = e.getPoint();
			panel.repaint();
		}
		
	}

	/**
	 * Main method to launch our application.
	 * 
	 * @param args[0] the number of rings to draw
	 */
	public static void main(String args[]) {
		// Nothing stops us from taking command-line parameters
		if (args.length != 0) {
			try {
				 drag = Boolean.parseBoolean(args[0]);
			} 
			catch (NumberFormatException e) {
				System.err.println("Could not parse " + args[0] + " as boolean.");
				System.exit(1);
			}
		}


		// and we can pass things to our constructor
		javax.swing.SwingUtilities.invokeLater(new ColorRings());
	}
}

/**
 * A class to encapsulate information about a ring on the screen.
 */
class ColorRing {
	
	// Ring info
	private int size;
	private Point upperLeft;
	private Color color;

	public ColorRing(int size, Point upperLeft, Color color) {

		// some bounds checking on these would probably be nice
		this.size = size;
		this.color = color;

		// since Point is mutable, we want to avoid side-effects that
		// could result from changes to the Point object, so we
		// make a new one here that we know will not be modified
		// by anyone else
		this.upperLeft = new Point(upperLeft);
	}
	
	public void changeColor() {
		Random r = new Random();
		Color newColor = new Color(r.nextInt(255),
			r.nextInt(255),
			r.nextInt(255));		
		this.color = newColor;
	}

	/**
	 * paint this object onto the given Graphics area
	 * 
	 * @param g the Graphics object where the ring should be drawn
	 */
	public void paint(Graphics g) {
		int i = 0;
		g.setColor(color);
		for (i = 0; i < 40; i++) {
			g.drawOval(upperLeft.x+i, upperLeft.y+i, size-i*2, size-i*2);
			g.drawOval(upperLeft.x+i+1, upperLeft.y+i, size-i*2, size-i*2);
			g.drawOval(upperLeft.x+i, upperLeft.y+i+1, size-i*2, size-i*2);
		}
	}

	/**
	 * A relative move of this object.
	 * 
	 * @param dx amount to translate in x
	 * @param dy amount to translate in y
	 */
	public void translate(int dx, int dy) {

		upperLeft.translate(dx, dy);
	}

	/**
	 * Determine if the given point is within this ring.
	 * 
	 * @param p Point to check
	 */
	public boolean contains(Point p) {
		Point circleCenter = new Point(upperLeft.x + size / 2, upperLeft.y + size / 2) ;
		return (circleCenter.distance(p) <= size / 2) && (circleCenter.distance(p) > ((size - 80) / 2));
	}
}
