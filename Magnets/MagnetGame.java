import objectdraw.*;
import java.awt.*;

/*
 * DESCRIBE YOUR CLASS HERE
 * 
 * YOUR NAME / LAB SECTION
 * DATE
 * 
 */
public class MagnetGame extends WindowController {

	
	private Magnet m;
	private Magnet m1;
	private Magnet dragging;
	private Magnet resting;
	private Location start;


	public void begin() {
		m = new Magnet(new Location(250, 250), canvas);
		m1 = new Magnet(new Location(90, 250), canvas);

		
	}
	
    public void onMousePress(Location point) {

    	start = point;
		if(m.contains(start)) {
			
			dragging = m;
			resting = m1;
			
		} else if (m1.contains(start)) {
			
			dragging = m1;
			resting = m;
			
		}
    	
    }

    public void onMouseDrag(Location l) {

		if(dragging == m) {
					
					m.move(l.getX() - start.getX(), l.getY()-start.getY());
					start = l;
					m.interact(m1);
					
		
		} 
		else if (dragging == m1) {
			
			m1.move(l.getX() - start.getX(), l.getY()-start.getY());
			start = l;
			m1.interact(m);
			
			
		}
    	
    }

    public void onMouseClick(Location point) {

    	if (m.contains(point)) {
    		m.flipPoles();
    		m.interact(m1);
    	}
    	if (m1.contains(point)) {
    		m1.flipPoles();
    		m1.interact(m);
    	}
    	
    }
    
    public static void main(String[] args) {
    	new MagnetGame().startController(500,500);
    }

}

