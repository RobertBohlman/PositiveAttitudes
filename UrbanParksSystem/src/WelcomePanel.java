import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class WelcomePanel extends JPanel {

	JLabel welcomeMessage = new JLabel();
	
	public WelcomePanel() {
		setLayout(new FlowLayout());
		Border border = welcomeMessage.getBorder();
		Border margin = new EmptyBorder(25,50,25,50);
		welcomeMessage.setBorder(new CompoundBorder(border, margin));
		welcomeMessage.setText("<html><font size='6'>Welcome to Urban Parks!</font></html>");
		add(welcomeMessage);
	}

}
