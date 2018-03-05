import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EmployeeSettingsPanel extends JPanel {

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
	}

}
