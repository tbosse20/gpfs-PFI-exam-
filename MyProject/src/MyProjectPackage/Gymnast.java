package MyProjectPackage;

import static MyProjectPackage.Main.app;
import static MyProjectPackage.Main.groups;
import static MyProjectPackage.Main.gymnasts;
import static MyProjectPackage.Main.mousePos;
import static MyProjectPackage.Main.screenSize;
import static MyProjectPackage.Main.mouseHeld;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import processing.core.*;

public class Gymnast extends Brick {
	
	// Front-end variables
	private PVector globalListPos = new PVector(225, 550);
	private final int rows = 5;

	// Class static variables
	public static short gymnastSize;
	public static boolean isDragging = false;
	public static boolean editing = false;

	// Final variables
	private final short id;
	
	// Dynamic variables
	public LinkedHashMap<String, String> att = new LinkedHashMap<String, String>();
	public PVector pos = new PVector(0, 0);
	public PVector rel;
	public String status = "list";
	public PVector listPos;
	private boolean hover = false;
	private int mouseHeldStart;
	
	public Gymnast() {
		super(new PVector(0, 0));
		this.id = gymnastSize++;
		this.att.put("name", "");
		this.att.put("age", "");
		this.att.put("gender", "");
		this.att.put("team_side", "");
		this.att.put("group", "0");
		
		groups.get("0").updateSize(1);
	}

	// Class constructor
	public Gymnast(
			String name,
			String age,
			String gender,
			String team_side,
			String temp_group
			) {
		super(new PVector(0, 0));
		this.id = gymnastSize++;
		this.att.put("name", name);
		this.att.put("age", age);
		this.att.put("gender", gender);
		this.att.put("team_side", team_side);
		this.att.put("group", temp_group);
		this.name = name;

		println("Gymnast made:",
		this.id,
		name,
		age,
		gender,
		team_side,
		temp_group,
		"(" + this.pos.x + ", " +
		this.pos.y + ")");
		
		while (groups.size() <= Integer.parseInt(temp_group)) {
			Group newGroup = new Group();
			groups.put(String.valueOf(groups.size()), newGroup);
		}
		groups.get(temp_group).updateSize(1);
	}
	
	private void getMid() {
		float width = gymnasts.size() / 5 * (dim.x - 1);
		globalListPos = new PVector(screenSize.x / 2 - width / 2,
				Utility.editFormationBarBot);
	}

	public void run() {
		colorCode = groups.get(att.get("group")).getColor();
		getMid();

		// Set position of each gymnast on the list (one col)
		listPos = new PVector(globalListPos.x + (int) (id / rows ) * dim.x - dim.x / 2,
				globalListPos.y + (id % rows) * dim.y);
		display("list", listPos, null);

		if (status == "dragged") {
			pos = mousePos.sub(rel);
		} else if (status == "list") {
			pos = listPos;
		}
		display("gymnast", pos, status);
		name = att.get("name");
	}
	
	public void hovering() {
		if (Utility.mouseOver(pos, dim) && !isDragging) {
			if (!hover) {
				mouseHeldStart = mouseHeld;
				hover = true;
			}
			if (mouseHeld - mouseHeldStart >= 50) {
				showInfo();
			}
		} else {
			hover = false;
		}
	}
	
	private void showInfo() {
		PVector padding = new PVector(10, 10);
		int spacing = 5;
		int counter = 0;
		float textHeight = spacing + app.textAscent();

		float maxKeyWidth = 0;
		float maxValueWidth = 0;
		for (Entry<String, String> attr : att.entrySet()) {
			String key = Utility.keyFormat(attr.getKey());
			String value = attr.getValue();
			
			// Get the length of key text and the gap
			float infoTextWidth = app.textWidth(key + " ");
			if (infoTextWidth > maxKeyWidth) {
				maxKeyWidth = infoTextWidth;
			}
			
			// Get the length of the value text
			infoTextWidth = app.textWidth(value);
			if (infoTextWidth > maxValueWidth) {
				maxValueWidth = infoTextWidth;
			}
			counter++;
		}
		
		// Sum the to max text widths
		float maxInfoWidth = maxKeyWidth + maxValueWidth;
		int shadowOff = 4;

		// Make shadow
		app.noStroke();
		app.fill(0, 50);
		app.rect(mousePos.x + shadowOff,
				mousePos.y + shadowOff,
				maxInfoWidth + padding.x * 2,
				textHeight * counter + padding.y * 2);
		
		// Make rect
		app.stroke(0);
		app.fill(200);
		app.rect(mousePos.x,
				mousePos.y,
				maxInfoWidth + padding.x * 2,
				textHeight * counter + padding.y * 2);

		counter = 0;
		app.textAlign(LEFT, TOP);
		for (Entry<String, String> attr : att.entrySet()) {

			String key = Utility.keyFormat(attr.getKey());
			String value = attr.getValue();
			
			float keyTextWidth = app.textWidth("Team side");
			
			float x = mousePos.x + padding.x;
			float y = mousePos.y + textHeight * counter++ + padding.y;
			
			app.fill(0);
			app.text(key, x, y);
			x += keyTextWidth + app.textWidth("X");
			app.fill(0);
			app.text(value, x, y);
		}
	}
}