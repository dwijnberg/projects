import java.awt.Color;

import objectdraw.*;

public class Sorter extends WindowController {

	private FramedRect whites;
	private FramedRect darks;
	private FramedRect colors;
	private FilledRect laundry;
	private FramedRect laundryBorder;
	private Location start;
	private Boolean dragging = false;
	private Color color;
	private int numCorrect = 0;
	private int numTotal = 0;
	private Text correctLabel;
	private Text wrongLabel;



	public void begin() {
		
		whites = new FramedRect(50, 320, 50, 50, canvas);
		Text wtext = new Text("whites", 55, 335, canvas);
		darks = new FramedRect(175, 320, 50, 50, canvas);
		Text dtext = new Text("darks", 183, 335, canvas);
		colors = new FramedRect(300, 320, 50, 50, canvas);
		Text ctext = new Text("colors", 303, 335, canvas);
		laundry = new FilledRect(175, 200, 50, 50, canvas);
		laundryBorder = new FramedRect(175, 200, 50, 50, canvas);
		color = new Color(255,255,255);
		laundry.setColor(color);
		laundryBorder.setColor(Color.black);
		correctLabel = new Text("Correct: " + numCorrect, 170, 400, canvas);
		wrongLabel = new Text("Wrong: " + (numTotal - numCorrect), 170, 420, canvas);


		
	}
	
	public static Color setColor(FilledRect rect) {
		
		
		float r = (float) Math.random();
		float g = (float) Math.random();
		float b =  (float) Math.random();
		Color c = new Color(r,g,b);
		rect.setColor(c);
		return c;
		
		
		
	}
	
	public void onMousePress(Location l) {
		
		start = l;
		if(laundry.contains(start)) {
			
			dragging = true;
			
		}
		
	}
	
	public void onMouseDrag(Location l) {
		
		if(dragging) {
			
			laundry.move(l.getX() - start.getX(), l.getY()-start.getY());
			laundryBorder.move(l.getX() - start.getX(), l.getY()-start.getY());
			start = l;

		} 

	}
	
	public void onMouseRelease(Location l) {

		
		if (color.getRed() + color.getBlue() + color.getGreen() < 230) {
			
			if (dragging && darks.contains(l)) {
				
				numCorrect++;
				numTotal++;
				laundry.moveTo(175,200);
				laundryBorder.moveTo(175,200);

				color = setColor(laundry);

			}
			else if(colors.contains(l) || whites.contains(l)) {
				numTotal++;
				laundry.moveTo(175,200);
				laundryBorder.moveTo(175,200);
				color = setColor(laundry);
			} else {
				laundry.moveTo(175,200);
				laundryBorder.moveTo(175,200);
				
			}
			
		}
		if (color.getRed() + color.getBlue() + color.getGreen() > 600) {
			
			if (dragging && whites.contains(l)) {
				
				numCorrect++;
				numTotal++;
				laundry.moveTo(175,200);
				laundryBorder.moveTo(175,200);

				color = setColor(laundry);

			}
			else if(colors.contains(l) || darks.contains(l)) {
				numTotal++;
				laundry.moveTo(175,200);
				laundryBorder.moveTo(175,200);
				color = setColor(laundry);
			} else {
				
				laundry.moveTo(175,200);
				laundryBorder.moveTo(175,200);
			}
			
			
		}
		else if(color.getRed() + color.getBlue() + color.getGreen() < 600 && color.getRed() + color.getBlue() + color.getGreen() > 230){
			
			if (dragging && colors.contains(l)) {
				
				numCorrect++;
				numTotal++;
				laundry.moveTo(175,200);
				laundryBorder.moveTo(175,200);
				color = setColor(laundry);

				
			}
			else if(whites.contains(l) || darks.contains(l)) {
				numTotal++;
				laundry.moveTo(175,200);
				laundryBorder.moveTo(175,200);
				color = setColor(laundry);
			} else {
				
				laundry.moveTo(175,200);
				laundryBorder.moveTo(175,200);
			}
			
		}
		
		dragging = false;
		
		correctLabel.setText("Correct: " + numCorrect);
		wrongLabel.setText("Wrong: " + (numTotal - numCorrect));

		
		
		
	}
	
	public static void main(String[] args) {
		
		Sorter d = new Sorter();
		d.startController(400,800);
		
	}
	
}
