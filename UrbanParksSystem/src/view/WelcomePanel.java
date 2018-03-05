package view;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.SystemData;

/**
 * Welcome Panel for GUI class
 * @author Jenna Hand, Kristi Anna Stageberg, Robert Bohlman, Jacob Reed, Aaron Hammers
 */
public class WelcomePanel extends JPanel {
	private static final long serialVersionUID = -2567709177665924823L;
	JLabel welcomeMessage = new JLabel();
	SystemData urbanParksSystem;
	
	/**
	 * Constructor
	 * @param system SystemData
	 */
	public WelcomePanel(SystemData system) {
		urbanParksSystem = system;
		setLayout(new FlowLayout());
		welcomeMessage.setText("Welcome to Urban Parks!");
		add(welcomeMessage);
	}

}
