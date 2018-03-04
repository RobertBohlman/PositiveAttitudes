import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel {

	JLabel welcomeMessage = new JLabel();
	SystemData urbanParksSystem;
	
	public WelcomePanel(SystemData system) {
		urbanParksSystem = system;
		setLayout(new FlowLayout());
		welcomeMessage.setText("Welcome to Urban Parks!");
		add(welcomeMessage);
	}

}
