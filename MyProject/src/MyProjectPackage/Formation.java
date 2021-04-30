package MyProjectPackage;

import static MyProjectPackage.Main.app;
import static MyProjectPackage.Main.groups;

import java.awt.Color;
import java.util.LinkedHashMap;

import processing.core.*;

public abstract class Formation extends PApplet {
	
	protected float width;
	protected float height;
	
	public LinkedHashMap<String, String> att = new LinkedHashMap<String, String>();
	public boolean gaps;
	
	protected final PVector dim = Utility.brickDim;
	protected PVector brickMargin;
	protected PVector formationPadding;
	protected PVector pos;
	
	Formation(
			String row,
			String col,
			boolean gaps) {
		this.att.put("row", row);
		this.att.put("col", col);
		this.gaps = gaps;
	}

	public abstract void update();
	public abstract void display();
	
	protected void showGroups(PVector pos) {
		for (int i = 1; i < groups.size(); i++) {
			String strI = String.valueOf(i);
			Group group = groups.get(strI);
			float colDisSize = dim.x / 5;
			if (group != null) {
				Color colorCode = group.getColor();
				
				String groupText = strI + " (" + group.getSize() + ")";
				float groupTextHeight = app.textAscent();

				float x = pos.x + width + colDisSize * 2;
				float y = pos.y + i * groupTextHeight;

				app.fill(50);
				app.textAlign(LEFT, CENTER);
				app.text(groupText, x, y);

				app.fill(colorCode.getRed(), colorCode.getGreen(), colorCode.getBlue());
				app.rectMode(CENTER);
				app.rect(x - colDisSize, y + colDisSize / 3, colDisSize, colDisSize);
			}
		}
	}
}