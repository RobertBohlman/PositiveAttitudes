import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserLoginPanel extends JPanel{
	
	private JLabel usernameText;
	private JTextField username;
	private JButton loginButton;

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

	
	public String getUserName() {
		return username.getText();
	}

}
