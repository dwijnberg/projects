
import objectdraw.*;
import java.awt.*;

/*
 * Definition of class of objects used to represent bar magnets.
 * DESCRIBE YOUR CLASS HERE
 * 
 * YOUR NAME / LAB SECTION
 * DATE
 * 
 * Complete the implementation of these methods and add 
 * the others described in the lab handout.
 */
public class Magnet {

    //  dimensions of magnets	
    private static final double MAGNET_WIDTH = 150;
    private static final double MAGNET_HEIGHT = 50;

    //  Box representing perimeter of magnet
    private FramedRect box;
    private Pole s;
    private Pole n;

    //  Create a new magnet at location upperLeft
    public Magnet(Location upperLeft, DrawingCanvas canvas) {
    	box = new FramedRect(upperLeft, MAGNET_WIDTH, MAGNET_HEIGHT, canvas);
    	n = new Pole(this, upperLeft.getX() - 20 + MAGNET_WIDTH, MAGNET_HEIGHT/2 + upperLeft.getY(), "N", canvas);
    	s = new Pole(this, upperLeft.getX() + 20, MAGNET_HEIGHT/2 + upperLeft.getY(), "S", canvas);
    }

    // returns the upper-left coordinates of the magnet
    
    public Pole getSouth() {
    	
    	return s;
    	
    }
    public Pole getNorth() {
    	
    	return n;
    	
    }
    
    public Location getLocation() {
        return box.getLocation();
    }

    public void move(double xoff, double yoff) {
    	box.move(xoff, yoff);
    	s.move(xoff, yoff);
    	n.move(xoff, yoff);


    }

    public void moveTo(Location point) {
    	box.moveTo(point);
    	s.move(point.getX() + 20 - s.getX(), MAGNET_HEIGHT/2 + point.getY() - s.getY());
    	n.move(point.getX() - 20 + MAGNET_WIDTH - n.getX(), MAGNET_HEIGHT/2 + point.getY() - n.getY());
    }

    /*
     * This should return true if the given point is within the magnet.
     * The current implementation does not do this!!!  You must change
     * the body of this method so that it has the proper functionality!!!
     */
    public boolean contains(Location point) {
        return box.contains(point); // REPLACE THIS LINE OF CODE WITH THE CORRECT IMPLEMENTATION
    }

    // returns the width of the magnet
    public double getWidth() {
        return MAGNET_WIDTH;
    }

    // returns the height of the magnet
    public double getHeight() {
        return MAGNET_HEIGHT;
    }
    
    public void flipPoles() {
    	
    	Location temp = this.getSouth().getLocation();
    	this.getSouth().move(this.getNorth().getX()-this.getSouth().getX(), 0);
    	this.getNorth().move(temp.getX()-this.getNorth().getX(), 0);
    	
    }
    public void interact(Magnet m) {
    	
    	if (distance(m.getNorth().getLocation(), this.getSouth().getLocation()) < (distance(m.getNorth().getLocation(), this.getNorth().getLocation()))) {
    		
    		this.getSouth().attract(m.getNorth());
    		
    	}
    	if (distance(m.getSouth().getLocation(), this.getNorth().getLocation()) < (distance(m.getSouth().getLocation(), this.getSouth().getLocation()))) {
    		
    		this.getNorth().attract(m.getSouth());
    		
    	}
    	if (distance(m.getSouth().getLocation(), this.getSouth().getLocation()) < (distance(m.getSouth().getLocation(), this.getNorth().getLocation()))) {
    		
    		this.getSouth().repel(m.getSouth());
    		
    	} else {
    		
    		this.getNorth().repel(m.getNorth());
    		
    	}
    	
    }
    
    public double distance(Location l1, Location l2) {
    	
    	
    	return(Math.sqrt(Math.pow((l1.getX() - l2.getX()),2)+Math.pow((l1.getY() - l2.getY()),2)));
    	
    	
    }

}
