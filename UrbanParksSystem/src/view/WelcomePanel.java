package view;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import model.SystemData;

/**
 * Welcome Panel for GUI class
 * @author Jenna Hand, Kristi Anna Stageberg, Robert Bohlman, Jacob Reed, Aaron Hammers
 */
public class WelcomePanel extends JPanel {
	private static final long serialVersionUID = -2567709177665924823L;
	JLabel welcomeMessage = new JLabel();
	
<<<<<<< HEAD:UrbanParksSystem/src/view/WelcomePanel.java
	/**
	 * Constructor
	 * @param system SystemData
	 */
	public WelcomePanel(SystemData system) {
		urbanParksSystem = system;
=======
	public WelcomePanel() {
>>>>>>> 0b8d4c1ec4419a06c1d7dab5a34b120a4c9c0add:UrbanParksSystem/src/WelcomePanel.java
		setLayout(new FlowLayout());
		Border border = welcomeMessage.getBorder();
		Border margin = new EmptyBorder(25,50,25,50);
		welcomeMessage.setBorder(new CompoundBorder(border, margin));
		welcomeMessage.setText("<html><font size='6'>Welcome to Urban Parks!</font></html>");
		add(welcomeMessage);
	}

}
