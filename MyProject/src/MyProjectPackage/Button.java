package MyProjectPackage;

import static MyProjectPackage.Main.app;

import processing.core.*;

public class Button extends PApplet {
	
	// Front-/backend variables
	private static final int textSize = 15;

	public String label;
	public PVector pos;
	public PVector dim;
	
	Button(String label, int x, int y, int width, int height) {
		this.label = label;
		this.pos = new PVector(x, y);
		this.dim = new PVector(width, height);
	}
	
	Button(String label, int x, int y) {
		this.label = label;
		app.textSize(textSize);
		this.pos = new PVector(x, y);
		this.dim = new PVector(
				(int) (app.textWidth(label) * 1.2),
				(int) (app.textAscent() * 1.5));
	}
	
	public void run() {
		display();
	}
	
	private void display() {
		// Drawing the background for the gymnast tile
		app.rectMode(CENTER);
		app.strokeWeight(1);
		app.stroke(0);
		app.fill(200);
		app.rect(pos.x, pos.y, dim.x, dim.y);
		
		// Writing the gymnasts name
		app.textSize(textSize);
		app.textAlign(CENTER, CENTER);
		app.fill(0);
		app.text(label, pos.x, pos.y);
	}
}
