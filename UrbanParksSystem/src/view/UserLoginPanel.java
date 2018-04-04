package view;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * User login panel for GUI
 * @author Jenna Hand, Kristi Anna Stageberg, Robert Bohlman, Jacob Reed, Aaron Hammers
 *
 */
public class UserLoginPanel extends JPanel{
	private static final long serialVersionUID = -1674106957194712051L;
	private JLabel usernameText;
	private JTextField username;
	private JButton loginButton;

	/**
	 * Contructor
	 * @param listener ActionListener
	 */
	public UserLoginPanel(ActionListener listener) {
		setLayout(new FlowLayout());
		
		usernameText = new JLabel("Username:");
		username = new JTextField();
		username.setPreferredSize(new Dimension(300,25));
		loginButton = new JButton("Login");
		loginButton.addActionListener(listener);
		
		add(usernameText);
		add(username);
		add(loginButton);
		setSize(500, 100);
		setFocusable(true);
		//this.getRootPane().setDefaultButton(loginButton);
	}

	/**
	 * Getter method for username text field.
	 * @return String
	 */
	public String getUserName() {
		return username.getText();
	}

}
