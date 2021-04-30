package MyProjectPackage;

import java.awt.Color;

public class Group {
	
	private Color col;
	private int size;
	
	public Group() {
		this.col = Color.LIGHT_GRAY;
		this.size = 0;
	}
	
	public Group(
			Color col,
			int size) {
		this.col = col;
		this.size = size;
	}
	
	public void updateSize(int i) {
		size += i;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setColor(Color col) {
		this.col = col;
	}
	
	public Color getColor() {
		return col;
	}

}
