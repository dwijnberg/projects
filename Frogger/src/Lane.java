import objectdraw.*;
import java.awt.*;
import java.util.ArrayList;

public class Lane extends ActiveObject {

        // Distance from front bumper to back bumper of the longest vehicle, in pixels.
    private static final int MAX_VEHICLE_SIZE = 139;
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
    

    private double velocity;
    

    private Frog f;
    private DrawingCanvas c;
    private Image images;
    private int laneNum;

    public Lane(Frog f, DrawingCanvas c, Image images, int laneNum)
    {
            // Add code to construct the lane here.
    		this.f = f;
    		this.c = c;
    		this.images = images;
    		velocity = (Math.random()/10 + 0.03);
    		this.laneNum = laneNum;
            start();

    }
    
    public void run()
    {
        // Add code here if necessary
    	if (laneNum<= 1){

        	new Vehicle(images, HIGHWAY_RIGHT+MAX_VEHICLE_SIZE, HIGHWAY_TOP,laneNum, -velocity, f, HIGHWAY_LEFT, c);

    		
    	} else {
    		

        	new Vehicle(images, HIGHWAY_LEFT , HIGHWAY_TOP,laneNum, velocity, f, HIGHWAY_RIGHT+MAX_VEHICLE_SIZE, c);

    		
    	}

        // Loop until the program stops. 
    	
        while ( true )
        {
        	if (laneNum<= 1){
        		int i = (int) (Math.random()*2 +2);
            	pause(((MAX_VEHICLE_SIZE*i) + MAX_VEHICLE_SIZE)/velocity);
            	new Vehicle(images, HIGHWAY_RIGHT+MAX_VEHICLE_SIZE, HIGHWAY_TOP,laneNum, -velocity, f, HIGHWAY_LEFT, c);

        		
        	} else {
        		
        		int i = (int) (Math.random()*2 +2);
            	pause(((MAX_VEHICLE_SIZE*i) + MAX_VEHICLE_SIZE)/velocity);
            	new Vehicle(images, HIGHWAY_LEFT , HIGHWAY_TOP,laneNum, velocity, f, HIGHWAY_RIGHT+MAX_VEHICLE_SIZE, c);

        		
        	}
        	

        }
    }


}