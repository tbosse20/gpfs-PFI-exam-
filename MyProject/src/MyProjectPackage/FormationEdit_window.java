package MyProjectPackage;

import java.awt.*;
import javax.swing.*;

public class FormationEdit_window extends PopupWindow {
	
	private Formation formation;
	private boolean gap;
	private JCheckBox gapCheckBox;
	
	public FormationEdit_window(Formation formation) {
		super(formation.att, new JFrame());
		this.formation = formation;
		this.gap = formation.gaps;
	}

	public void run() {
		System.out.println(this);
		addAtt();
		
		JPanel panel = new JPanel();
		// Create and add key label
		JLabel keyLabel = new JLabel("Gaps");
		keyLabel.setMinimumSize(new Dimension(70, 20));
		keyLabel.setPreferredSize(new Dimension(70, 20));
		keyLabel.setMaximumSize(new Dimension(70, 20));
		panel.add(keyLabel);
		
		gapCheckBox = new JCheckBox();
		gapCheckBox.setSelected(gap);
		panel.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
		panel.add(gapCheckBox);
		frame.add(panel, gbc);
		
		addSaveBut();
		makeFrame();
	}

	@Override
	protected void childUpdate() {
		update();
		formation.gaps = gapCheckBox.isSelected();
		formation.update();
	}
}
