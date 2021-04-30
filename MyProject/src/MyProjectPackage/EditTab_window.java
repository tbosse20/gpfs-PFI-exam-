package MyProjectPackage;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import static MyProjectPackage.Main.groups;

public class EditTab_window extends PopupWindow {
	
	private Group group;
	
	public EditTab_window(LinkedHashMap<String, String> att) {
		super(att, new JFrame());
	}

	public void run() {
		addAtt();
		
		group = groups.get(att.get("group"));
		
		//JLabel colorLabel = new JLabel("");
		//colorLabel.setMinimumSize(new Dimension(70, 20));
		//colorLabel.setBackground(currentColor);
		//panel.add(colorLabel);
		
		JPanel colorPanel = new JPanel();
		Color currentColor = group.getColor();
		JButton colorPick = new JButton("Change group color");
		colorPick.setBackground(currentColor);
		colorPick.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	Color newColor = JColorChooser.showDialog(null, "Choose a Color for group " + att.get("group"), currentColor);
		    	if (newColor == null) return;
		    	colorPick.setBackground(newColor);
		    	group.setColor(newColor);
		    }
		});
		colorPanel.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
		colorPanel.add(colorPick);
		frame.add(colorPanel);
		
		addSaveBut();
		makeFrame();
	}

	@Override
	protected void childUpdate() {
		group.updateSize(-1);
		String oldGroupId = att.get("group");
		
		update();
		
		//Check group number
		if (groups.get(att.get("group")) == null) {
			Group newGroup = new Group();
			groups.put(String.valueOf(groups.size()), newGroup);
			att.replace("group", String.valueOf(groups.size() - 1));
		}
		
		group = groups.get(att.get("group"));
		group.updateSize(1);
		
		Utility.updateGroups(oldGroupId);
		
		Gymnast.editing = false;
		System.out.println("att update:" + att);
	}
}
