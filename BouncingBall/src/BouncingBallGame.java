import java.awt.Image;

import objectdraw.*;

public class BouncingBallGame extends WindowController {

	private FilledOval ball;
	private Ball betterball;
	private VisibleImage goat;
	private Image data;
	private FilledRect paddle;
	
	public void begin() {
		
		ball = new FilledOval(50,100,100,100, canvas);
		data = getImage("ball.png");
		paddle = new FilledRect(canvas.getWidth()/2, canvas.getHeight() - 40,100,20,canvas);
		//betterball = new Ball(data, 200,100, 20, 20,paddle,canvas);
		BallGenerator bg = new BallGenerator(data,paddle,canvas);
		
	}
	
	public void onMouseClick(Location l) {
				
		new Ball(data, l.getX(),l.getY(), 20, 20,paddle,canvas);
		
	}
	
	public void onMouseMove(Location l) {
		
		paddle.moveTo(l.getX()-paddle.getWidth()/2, paddle.getY());
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BouncingBallGame bb = new BouncingBallGame();
		bb.startController(600,600);
		
	}

}
