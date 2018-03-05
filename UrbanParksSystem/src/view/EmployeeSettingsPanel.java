<<<<<<< HEAD:UrbanParksSystem/src/view/EmployeeSettingsPanel.java
package view;
=======
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
>>>>>>> 0b8d4c1ec4419a06c1d7dab5a34b120a4c9c0add:UrbanParksSystem/src/EmployeeSettingsPanel.java
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EmployeeSettingsPanel extends JPanel {

<<<<<<< HEAD:UrbanParksSystem/src/view/EmployeeSettingsPanel.java
	private static final long serialVersionUID = 3553928680296437397L;

	public EmployeeSettingsPanel() {
		// TODO Auto-generated constructor stub
=======
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
				urbanParksSystem.set
			}});
		add(parkJobs);
		add(maxNum);
>>>>>>> 0b8d4c1ec4419a06c1d7dab5a34b120a4c9c0add:UrbanParksSystem/src/EmployeeSettingsPanel.java
	}

}
