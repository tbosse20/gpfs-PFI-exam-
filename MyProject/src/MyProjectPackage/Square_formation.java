package MyProjectPackage;

import processing.core.*;
import static MyProjectPackage.Main.screenSize;

import java.util.ArrayList;

import static MyProjectPackage.Main.gymnastBricks;
import static MyProjectPackage.Main.app;

public class Square_formation extends Formation {

	Square_formation(
			String row,
			String col,
			boolean gaps) {
		super(row, col, gaps);
		
		this.formationPadding = PVector.div(dim, 2);
		this.formationPadding.x /= 2;
		this.brickMargin = PVector.div(dim, 2);
	}

	@Override
	public void update() {
		gymnastBricks = new ArrayList<>();
		width = parseInt(att.get("col")) * (dim.x + brickMargin.x);
		height = parseInt(att.get("row")) * (dim.y + brickMargin.y - 1) + formationPadding.y * 1;
		pos = new PVector(screenSize.x / 2 - width / 2,
				Utility.formationMarginTop);
		Utility.formationBotPos = (int) (height + pos.y);
		
		int counter = 0;
		for (int i = 0; i < parseInt(att.get("col")); i++) {
			for (int j = 0; j < parseInt(att.get("row")); j++) {

			    float x = i * (dim.x + brickMargin.x) + pos.x + formationPadding.x;
			    float y = j * (dim.y + brickMargin.y) + pos.y + formationPadding.y;
				
				if ((gaps && counter % 2 == 0) || (!gaps)) {
					Brick brick = new Brick(new PVector(x, y));
					gymnastBricks.add(brick);
				}
				counter++;
			}
		}
	}
	
	@Override
	public void display() {		
		// Display formation border
		app.fill(255);
		app.stroke(0);
		app.rectMode(CORNER);
		app.rect(pos.x,
				pos.y,
				width,
				height);
		
		for (Brick brick : gymnastBricks) { brick.run(); }

	    app.stroke(0);
	    app.fill(50);
	    app.textSize(15);
		for (int i = 0; i < parseInt(att.get("col")); i++) {
			for (int j = 0; j < parseInt(att.get("row")); j++) {
				
				float textWidth = app.textWidth(String.valueOf(i));
				float textHeight = app.textAscent();

			    float x = i * (dim.x + brickMargin.x) + pos.x;
			    float y = j * (dim.y + brickMargin.y) + pos.y;
				if (i == 0) {
					char intToChar = (char) (1 + j + 64);
					app.text(intToChar,
							x - dim.x / 2 - textWidth + formationPadding.x,
							y + dim.y / 2 + formationPadding.y);
				}
				if (j == 0) {
					app.text(1 + i,
							x + dim.x / 2 + formationPadding.x,
							y - dim.y / 2 - textHeight + formationPadding.y);
				}
			}
		}
		showGroups(pos);
	}
}
