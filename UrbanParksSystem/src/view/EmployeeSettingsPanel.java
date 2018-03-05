
package view;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.SystemData;

public class EmployeeSettingsPanel extends JPanel {

	private static final long serialVersionUID = 3553928680296437397L;

	SystemData urbanParksSystem;
	
	JLabel parkJobs = new JLabel();
	JTextField maxNum = new JTextField();
	JButton enterNum = new JButton();
	
	
	public EmployeeSettingsPanel(SystemData system) {
		urbanParksSystem = system;
		setLayout(new FlowLayout());
		parkJobs.setText("Maximum # of park jobs:");
		enterNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				urbanParksSystem.setMaxJobs(maxNum.getText());
			}});
		add(parkJobs);
		add(maxNum);
	}

}
