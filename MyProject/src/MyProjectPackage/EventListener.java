package MyProjectPackage;

import processing.core.*;

import static MyProjectPackage.Main.gymnasts;
import static MyProjectPackage.Main.gymnastBricks;
import static MyProjectPackage.Main.mousePos;
import static MyProjectPackage.Main.editFormationButton;
import static MyProjectPackage.Main.addGymnastButton;
import static MyProjectPackage.Main.formation;
import static MyProjectPackage.Utility.*;

public class EventListener extends PApplet {
	public void mousePressed() {
		println("User pressed.");
		for (Gymnast gymnast : gymnasts) {
			if (gymnast != null &&
					!Gymnast.isDragging &&
					Utility.mouseOver(gymnast.pos, gymnast.dim)) {
				gymnast.rel = mousePos.sub(gymnast.pos);
			}
		}
	}

	public void mouseDragged() {
		for (Brick brick : gymnastBricks) {
			brick.colliding = false;
		}

		for (Gymnast gymnast : gymnasts) {
			if (gymnast != null) {
				if (!Gymnast.isDragging && Utility.mouseOver(gymnast.pos, gymnast.dim)) {
					println("Dragging:", gymnast.att.get("name"));
					Gymnast.isDragging = true;
					gymnast.status = "dragged";
				}

				if (gymnast.status == "dragged") {
					if (Utility.boxCollider(
							gymnast.pos, gymnast.dim,
							gymnast.listPos, gymnast.dim)) {
						gymnast.colliding = true;
					} else {
						gymnast.colliding = false;
					}
				}

				for (Brick brick : gymnastBricks) {
					if (gymnast.status == "dragged" &&
							boxCollider(gymnast.pos, gymnast.dim, brick.pos, brick.dim)) {
						brick.colliding = true;
						gymnast.colliding = true;
						return;
					}
				}
			}
		}
	}

	public void mouseReleased() {
		println("User released button.");
		for (Gymnast gymnast : gymnasts) {
			if (gymnast != null) {
				gymnast.colliding = false;
				if (gymnast.status == "dragged") {
					if (Utility.boxCollider(
							gymnast.pos, gymnast.dim,
							gymnast.listPos, gymnast.dim)) {
						println("User released", gymnast.att.get("name"), "on list.");
						gymnast.status = "list";
					} else {
						println("User released", gymnast.att.get("name"), "in void.");
						gymnast.status = "void";
					}
					Gymnast.isDragging = false;

					for (Brick brick : gymnastBricks) {
						if (Utility.boxCollider(
								gymnast.pos, gymnast.dim,
								brick.pos, brick.dim)) {
							println("User released", gymnast.att.get("name"), "on brick.");
							brick.colliding = false;

							gymnast.status = "brick";
							gymnast.pos = brick.pos;
						}
					}

				} else if (gymnast.status != "dragged") {
					if ((Utility.mouseOver(gymnast.pos, gymnast.dim) ||
							Utility.mouseOver(gymnast.listPos, gymnast.dim)) &&
							!Gymnast.isDragging) {
						println("User is editing", gymnast.att.get("name"));
						Gymnast.editing = true;
						if (EditTab_window.frame != null) {
							EditTab_window.frame.dispose();
						}
						new EditTab_window(gymnast.att).run();
					}
				}
			}
		}

		if (Utility.mouseOverCenter(editFormationButton.pos, editFormationButton.dim) && !Gymnast.isDragging) {
			if (FormationEdit_window.frame != null) {
				FormationEdit_window.frame.dispose();
			}
			new FormationEdit_window(formation).run();
		}

		if (Utility.mouseOverCenter(addGymnastButton.pos, addGymnastButton.dim) && !Gymnast.isDragging) {
			gymnasts.add(new Gymnast());
		}
	}

	public void keyPressed() { 
		Gymnast temp_gymnast = new Gymnast(
				"John", //Name (String)
				"Boy", //Gender (String)
				"16", //Age (String)
				"Tumbler", //Team side (String)
				"1" //Group (String)
				);
		gymnasts.add(temp_gymnast);

		temp_gymnast = new Gymnast(
				"Sara", //Name (String)
				"Girl", //Gender (String)
				"14", //Age (String)
				"Rhythmical", //Team side (String)
				"1" //Group (String)
				);
		gymnasts.add(temp_gymnast);

		temp_gymnast = new Gymnast(
				"Adam", //Name (String)
				"Boy", //Gender (String)
				"15", //Age (String)
				"Rhythmical", //Team side (String)
				"2" //Group (String)
				);
		gymnasts.add(temp_gymnast);

		temp_gymnast = new Gymnast(
				"Lone", //Name (String)
				"Girl", //Gender (String)
				"16", //Age (String)
				"Rhythmical", //Team side (String)
				"2" //Group (String)
				);
		gymnasts.add(temp_gymnast);

		temp_gymnast = new Gymnast(
				"Carl", //Name (String)
				"Boy", //Gender (String)
				"15", //Age (String)
				"Tumbler", //Team side (String)
				"3" //Group (String)
				);
		gymnasts.add(temp_gymnast);
	}
}