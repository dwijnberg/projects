import java.awt.Image;

import objectdraw.*;

public class BallGenerator extends ActiveObject {

	private Image i;
	private static final int BALL_HEIGHT = 50;
	private static final int BALL_WIDTH = 50;
	private DrawingCanvas c;
	private FilledRect paddle;

	
	
	public BallGenerator(Image i, FilledRect paddle, DrawingCanvas c) {
		
		this.i = i;
		this.paddle = paddle;
		this.c = c;
		start();
		
	}
	

	public void run() {
		

		while(true) {
			
			Ball b = new Ball(i,c.getWidth()/2,10,BALL_WIDTH,BALL_HEIGHT,paddle,c);
			pause(Math.random() * 1000 + 1000);
			
		}
		
	}
	
}
