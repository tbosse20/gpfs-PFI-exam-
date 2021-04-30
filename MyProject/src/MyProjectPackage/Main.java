package MyProjectPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import processing.core.*;

public class Main extends EventListener {

	// Front-/backend variables
	
	// Final variables
	
	// Semi final variables
	public static PApplet app;
	public static PVector screenSize;
	public static Button editFormationButton;
	public static Button addGymnastButton;
	
	// Dynamic variables
	public static Map<String, Group> groups = new HashMap<String, Group>() {{
		put("0", new Group());
	}};
	public static PVector mousePos = new PVector(0, 0);
	public static int mouseHeld = 0;
	public static Square_formation formation;
	public static ArrayList<Gymnast> gymnasts = new ArrayList<>();
	public static ArrayList<Brick> gymnastBricks = new ArrayList<>();

	// The argument passed to main must match the class name
	public static void main(String[] args) {   
		PApplet.main("MyProjectPackage.Main");
	}

	// method used only for setting the size of the window
	@Override
	public void settings() {
		size(displayWidth, displayHeight);
		screenSize = new PVector(displayWidth, displayHeight);
		app = this;
	}

	private void setupWindow() {
		String getGymnastsN = JOptionPane.showInputDialog("Number of gymnasts on the team");
		if (getGymnastsN.matches(".*[a-z].*") || getGymnastsN == null || getGymnastsN.length() == 0) getGymnastsN = "0";
		int gymnastsN = Integer.parseInt(getGymnastsN);
		for (int i = 0; i < gymnastsN; i++) {
			gymnasts.add(new Gymnast());
		}
	}

	// identical use to setup in Processing IDE except for size()
	@Override
	public void setup() {		
		setupWindow();

		// Make as standard square formation
		formation = new Square_formation(
				"5", // Rows (int)
				"8", // Columns (int)
				true // Gaps (true)
				);
		formation.update();
	}

	// identical use to draw in Processing IDE
	@Override
	public void draw() {
		background(255, 255, 255);

		mousePos = new PVector(mouseX, mouseY);
		mouseHeld = frameCount;

		editFormationButton();
		formation.display();

		for (Gymnast gymnast : gymnasts) if (gymnast != null) gymnast.run();
		for (Gymnast gymnast : gymnasts) if (gymnast != null) gymnast.hovering();
		
		addGymnast();
	}

	private void editFormationButton() {
		editFormationButton = new Button(
				"Edit formation",
				(int) (screenSize.x / 2),
				(int) (Utility.formationBotPos + Utility.formationEditBarMarginTop));
		Utility.editFormationBarBot = (int) (Utility.formationBotPos + Utility.formationEditBarMarginTop + Utility.editFormationBarDim.y);
		editFormationButton.run();
	}

	private void addGymnast() {
		addGymnastButton = new Button(
				"Add gymnast",
				(int) (screenSize.x / 2),
				(int) (Utility.editFormationBarBot + Utility.editFormationBarDim.y + Utility.brickDim.y * 5));
		addGymnastButton.run();
	}
}