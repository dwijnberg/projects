import objectdraw.*;
import java.awt.*;

public class Vehicle extends ActiveObject
{
	
	private double velocity;
	private Frog f;
	private VisibleImage vi;
	private double highwayEnd;
	private static final int MAX_VEHICLE_SIZE = 139;
	private static final int MAX_VEHICLE_HEIGHT = 60;
    private static final double HIGHWAY_LENGTH = 700;
    private static final double LANE_WIDTH = 100;
    private static final int NUM_LANES = 4;
    private static final double HIGHWAY_WIDTH = LANE_WIDTH * NUM_LANES;
    private static final double LINE_WIDTH = LANE_WIDTH / 10;

    // Constants defining the locations of the background components
    private static final double HIGHWAY_LEFT = 0;
    private static final double HIGHWAY_RIGHT = HIGHWAY_LEFT + HIGHWAY_LENGTH;
    private static final double HIGHWAY_TOP = LANE_WIDTH;
    private static final double HIGHWAY_BOTTOM = HIGHWAY_TOP + HIGHWAY_WIDTH;

    // Constants describing the lines on the highway
    private static final double LINE_SPACING = LINE_WIDTH / 2;
    private static final double DASH_LENGTH = LANE_WIDTH / 3;
    private static final double DASH_SPACING = DASH_LENGTH / 2;
    
	
        public Vehicle(Image data, double highwayLeft, double highwayTop, int lane, double velocity, Frog f, double highwayEnd, DrawingCanvas c)
    {
        // insert code to construct the vehicle here
        	if (lane <= 1) {
        		
        		vi = new VisibleImage(data, new Location(highwayLeft - MAX_VEHICLE_SIZE, highwayTop + (LANE_WIDTH-MAX_VEHICLE_HEIGHT)/2 + lane*LANE_WIDTH), c);
        		
        	} else {
        		
        		vi = new VisibleImage(data, new Location(highwayLeft, highwayTop + (LANE_WIDTH-MAX_VEHICLE_HEIGHT)/2 + lane*LANE_WIDTH), c);
        		
        	}
        	this.velocity = velocity;
        	this.f = f;
        	this.highwayEnd = highwayEnd;
              start();
    }


        public void run()
    {
        	if(velocity > 0) {
        		while (vi.getX() < highwayEnd) {
            		long time = System.currentTimeMillis();
            		pause(30);
            		vi.move((System.currentTimeMillis() - time) * velocity, 0);
            		if (f.overlaps(vi)) {
            			if (f.isAlive()) {
                			f.kill();

            			}
            			
            		}
            	}
            	vi.removeFromCanvas();
        	}
        	else {
        		
        		while (vi.getX() > highwayEnd-139) {
            		long time = System.currentTimeMillis();
            		pause(30);
            		vi.move((System.currentTimeMillis() - time) * velocity, 0);
            		if (f.overlaps(vi)) {
            			
            			if (f.isAlive()) {
                			f.kill();

            			}            			
            		}
            	}
            	vi.removeFromCanvas();
        		
        	}
        	
        	
        	
    }
}





