import objectdraw.*;
import java.awt.*;

public class Frog
{
    // Height of the frog image
    private static final double FROG_HEIGHT = 48;

    // This should refer to the image of the frog.  Note that it is not initialize by
    // the code we have provided.
    private VisibleImage frogImage;
    private Location start;
    private int laneWidth;
    private final int FROG_WIDTH = 83;
    private DrawingCanvas c;
    private boolean isAlive = true;
    private Text ouch;


    public Frog(Image data, Location start, int laneWidth, DrawingCanvas canvas)
    {

    	frogImage = new VisibleImage(data, start, FROG_WIDTH, FROG_HEIGHT, canvas);
    	this.start = start;
    	this.laneWidth = laneWidth;
    	c = canvas;
    	ouch = new Text("", start, c);
    	
    }

    public  boolean overlaps(VisibleImage vehicleImage)
    {
    	return(vehicleImage.overlaps(frogImage));   // YOU NEED TO CHANGE THIS!
    }

    public void kill()
    {
    	isAlive = false;
    	ouch = new Text("OUCH!", start.getX(), start.getY()+30, c);
    	ouch.setFontSize(40);
    	ouch.setColor(Color.red);
    }

    public void reincarnate()
    {
    	frogImage.moveTo(start);
    	ouch.removeFromCanvas();
    	isAlive = true;
    }


    public void hopToward( Location point )
    {

    	if (point.getX() > frogImage.getX() && point.getX() < frogImage.getX() + FROG_WIDTH) {
    		
    		if (point.getY() > frogImage.getY()) {
    			System.out.print(laneWidth);
    			frogImage.move(0, laneWidth);
    			
    		} else if( point.getY() < frogImage.getY() ) {
    			
    			
    			frogImage.move(0, -laneWidth);
    			
    		}
    		
    	} else if (point.getX() < frogImage.getX()) {
    		
    		frogImage.move(-FROG_WIDTH, 0);
    		
    	} else {
    		
    		frogImage.move(FROG_WIDTH, 0);
    		
    	}
    	
    	
    }

    public boolean isAlive () {
    	return isAlive;     // YOU NEED TO CHANGE THIS!
    }

}
