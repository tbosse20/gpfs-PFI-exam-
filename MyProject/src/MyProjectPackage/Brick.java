package MyProjectPackage;

import processing.core.*;
import static MyProjectPackage.Main.app;

import java.awt.Color;

public class Brick extends PApplet {
	
	public boolean active;
	public PVector pos;
	public final PVector dim = Utility.brickDim;
	public Gymnast gymnast = null;
	public boolean colliding = false;
	protected Color colorCode = Color.GRAY;
	protected String name = "";
	
	Brick (PVector pos) {
		this.pos = pos;
	}
	
	public void run() {
		displayColliding();
		display("brick", pos, null);
	}
	
	private void displayColliding() {
		// Make border for brick
		if (colliding) {
			app.strokeWeight(2);
			app.stroke(200, 200, 0);
		} else {
			app.strokeWeight(1);
			app.stroke(0, 50);
		}
	}
	
	protected void display(String type, PVector pos, String status) {
		// Empty display
		int emptyCol = 255;
		if (type == "list" || type == "brick") {
			emptyCol = 50;
		}
		
		// Shadow
		else if (type == "gymnast" && status == "dragged") {
			int shadowOff = 3;
			app.strokeWeight(1);
			app.noStroke();
			app.fill(0, 50);
			app.rect(pos.x + shadowOff, pos.y + shadowOff, dim.x, dim.y);
		}
		
		// Drawing the background for the gymnast tile
		app.rectMode(CORNER);
		displayColliding();
		app.fill(colorCode.getRed(), colorCode.getGreen(), colorCode.getBlue(), emptyCol);
		app.rect(pos.x, pos.y, dim.x, dim.y);
		
		// Writing the gymnasts name
		app.textSize(map(name.length(), 0, 20, 17, 5));
		app.textAlign(CENTER, CENTER);
		app.fill(0, emptyCol);
		app.text(name, pos.x + dim.x / 2, pos.y + dim.y / 2);
	}
}
