import objectdraw.*;
import java.awt.Image;
public class Ball extends ActiveObject {
	
	private VisibleImage vi;
	private DrawingCanvas c;
	private FilledRect p;
	
	public Ball(Image i, 
			double x, 
			double y, 
			double width, 
			double height, 
			FilledRect paddle,
			DrawingCanvas canvas) {
		vi = new VisibleImage(
				i, 
				x, y, 
				width, height, 
				canvas);
		c = canvas;
		p = paddle;
		start();
		
	}
	
	public void run() {
		double dirX = Math.random();
		if(Math.random() < 0.5) {
			dirX *= -1;
		}
		double dirY = 1;
		double minHeight = vi.getY();
		while(vi.getY() < c.getHeight()) {
				vi.move(dirX, dirY);
				pause(10);
				if(vi.overlaps(p)) {
					dirY *= -1.25;
					dirX *= 1.25;
				}
				else if(vi.getY() < 0) {
					dirY *= -1;
				} else if (vi.getX() <= 0 || 
						vi.getX() + vi.getWidth() > c.getWidth()) {
					dirX *= -1;
				}
		
		}
		vi.removeFromCanvas();
	}
}