package MyProjectPackage;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.*;

@SuppressWarnings("serial")
public abstract class PopupWindow extends JFrame {

	// Front-/backend variables
	private final String placeHolderText = "Insert";
	
	// Final variables
	protected final GridBagConstraints gbc = new GridBagConstraints();
	
	// Semi final variables
	public static JFrame frame;
	protected LinkedHashMap<JLabel, JTextField> panels = new LinkedHashMap<JLabel, JTextField>();
	
	// Dynamic variables
	protected LinkedHashMap<String, String> att;

	// Constructor
	public PopupWindow(LinkedHashMap<String, String> att, JFrame frame) {
		this.att = att;
		this.frame = frame;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
	}

	public abstract void run();
	protected abstract void childUpdate();

	protected void makeFrame() {
		//Display the window.
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void makeLabelAndTextField(JPanel panel, String key, String value) {
		// Create and add key label
		JLabel keyLabel = new JLabel();
		keyLabel.setText(key);
		keyLabel.setPreferredSize(new Dimension(70, 20));
		panel.add(keyLabel);

		// Create and add value text field
		JTextField valueTextField = new JTextField();
		valueTextField.setText(value);
		valueTextField.setPreferredSize(new Dimension(70, 20));

		if (valueTextField.getText().isEmpty()) {
			valueTextField.setForeground(Color.GRAY);
			valueTextField.setText(placeHolderText);
		}

		valueTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (valueTextField.getText().equals(placeHolderText)) {
					valueTextField.setText("");
					valueTextField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (valueTextField.getText().isEmpty()) {
					valueTextField.setForeground(Color.GRAY);
					valueTextField.setText(placeHolderText);
				}
			}
		});
		panel.add(valueTextField);

		panels.put(keyLabel, valueTextField);
	}

	protected void addAtt() {
		frame.setLayout(new GridBagLayout());

		// Insert all the attributes to the frame
		System.out.println(att);
		for (Entry<String, String> attr : att.entrySet()) {
			JPanel panel = new JPanel();

			// Update key word to fit to GUI
			String key = Utility.keyFormat(attr.getKey());
			String value = attr.getValue();

			makeLabelAndTextField(panel, key, value);

			panel.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
			frame.add(panel, gbc);
		}
	}

	protected void addSaveBut() {
		JPanel updatePanel = new JPanel();
		JButton updateButton = new JButton("Save");
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				childUpdate();
				frame.dispose();
			}
		});
		updatePanel.add(updateButton);
		frame.add(updatePanel, gbc);
		frame.getRootPane().setDefaultButton(updateButton);
	}

	protected void update() {
		for (Entry<JLabel, JTextField> panel : panels.entrySet()) {
			JLabel label = panel.getKey();
			JTextField textField = panel.getValue();

			String key = label.getText();
			key = key.toLowerCase();
			key = key.replace(" ", "_");
			key = key.replace(":", "");

			String temp_value = textField.getText();
			if (temp_value.equals("Insert")) temp_value = "";
			att.replace(key, temp_value);
		}
	}
}
