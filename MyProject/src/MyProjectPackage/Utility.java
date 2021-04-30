package MyProjectPackage;

import processing.core.*;
import static MyProjectPackage.Main.mousePos;
import static MyProjectPackage.Main.groups;

public class Utility {
	
	// Position attributes
	public static final int formationMarginTop = 100;
	public static int formationBotPos = 0;
	public static final int formationEditBarMarginTop = 50;
	public static int editFormationBarBot = 0;
	public static final int addMarginTop = 50;
	
	// Size attributes
	public static final PVector brickDim = new PVector(75, 45);
	public static final PVector editFormationBarDim = new PVector(65, 50);

	public static boolean mouseOver(PVector pos, PVector dim) {
		if (mousePos.x >= pos.x &&
			mousePos.x <= pos.x + dim.x &&
			mousePos.y >= pos.y &&
			mousePos.y <= pos.y + dim.y)
			return true;
			return false;
	}
	
	public static boolean mouseOverCenter(PVector pos, PVector dim) {
		if (mousePos.x >= pos.x - dim.x / 2 &&
			mousePos.x <= pos.x + dim.x / 2 &&
			mousePos.y >= pos.y - dim.y / 2 &&
			mousePos.y <= pos.y + dim.y / 2)
			return true;
			return false;
	}
	
	public static boolean boxCollider(PVector pos1, PVector dim1, PVector pos2, PVector dim2) {
		if (pos1.x < pos2.x + dim2.x &&
		   pos1.x + dim1.x > pos2.x &&
		   pos1.y < pos2.y + dim2.y &&
		   pos1.y + dim1.y > pos2.y)
			return true;
			return false;
	}
	
	public static void updateGroups(String strI) {
		Group group = groups.get(strI);
		System.out.println("Grp. " + strI + " has " + group.getSize());
		if (group.getSize() == 0) {
			System.out.println("=> Deleted");
			groups.remove(strI);
		}
	}
	
	public static String keyFormat(String dexKey) {
		String key = Character.toUpperCase(dexKey.charAt(0)) + dexKey.substring(1);
		key = key.replace("_", " ");
		key += ":";
		return key;
	}
}

