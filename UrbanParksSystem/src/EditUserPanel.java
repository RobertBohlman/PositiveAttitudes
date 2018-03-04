import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EditUserPanel extends JPanel{
	AbstractUser user;
	private static JLabel lbl_email = new JLabel();
	private static JLabel lbl_age = new JLabel();
	private static JLabel lbl_physicalLevel = new JLabel();
	private static JLabel lbl_userName = new JLabel();
	private static JLabel lbl_userEmail = new JLabel();
	private static JLabel lbl_userAge = new JLabel();
	private static JLabel lbl_userPhysicalLevel = new JLabel();
	
	ArrayList<JLabel> detailLabels = new ArrayList<JLabel>();
	ArrayList<JLabel> userInfoLabels = new ArrayList<JLabel>(); 
	
	JButton btn_editEmail = new JButton("Edit");
	JButton btn_editAge = new JButton("Edit");
	JButton btn_editPhysicalLevel = new JButton("Edit");

	ArrayList<JButton> editButtons = new ArrayList<JButton>(); 
	
	JButton btn_save = new JButton("save");
	JButton btn_clear = new JButton("clear");

	public EditUserPanel(SystemData system) {
		user = system.getCurrentUser();
		lbl_userName.setText(user.getUserName());
		if(user instanceof Volunteer) {
			
			detailLabels.add(lbl_email);
			lbl_userEmail.setText(((Volunteer) user).getVolunteerEmail());
			userInfoLabels.add(lbl_userEmail);
			editButtons.add(btn_editEmail);
			
			detailLabels.add(lbl_age);
			lbl_userAge.setText("" + ((Volunteer) user).getVolunteerAge());
			userInfoLabels.add(lbl_userAge);
			editButtons.add(btn_editAge);
			
			detailLabels.add(lbl_physicalLevel);
			lbl_userPhysicalLevel.setText("" + ((Volunteer) user).getVolunteerPhysicalLevel());
			userInfoLabels.add(lbl_userPhysicalLevel);
			editButtons.add(btn_editPhysicalLevel);
			
		} else if(user instanceof ParkManager) {
			
			detailLabels.add(lbl_email);
			lbl_userEmail.setText(((ParkManager) user).getManagerEmail());
			userInfoLabels.add(lbl_userEmail);
			editButtons.add(btn_editEmail);
			
		} else if(user instanceof Employee) {
			lbl_userName.setText(user.getUserName());
		}
		
		setupButtons();
		this.setLayout(new BorderLayout());
		createNorth();
		createCenter();
		createSouth();
		this.setVisible(true);
	}
	
	private void createNorth() {
		JPanel northPanel = new JPanel(new FlowLayout());
		northPanel.add(lbl_userName);
		this.add(BorderLayout.NORTH, northPanel);
	}
	
	private void createCenter() {
		JPanel centerPanel = new JPanel(new GridLayout(1,2));
		
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
		for (JButton b : editButtons) {
			eastPanel.add(b);
		}
		
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
		for (JLabel l : userInfoLabels) {	
			middlePanel.add(l);
			l.setAlignmentX(RIGHT_ALIGNMENT);
		}
		
		centerPanel.add(middlePanel);
		centerPanel.add(eastPanel);
		this.add(BorderLayout.CENTER, centerPanel);
	}
	
	private void createSouth() {
		JPanel southPanel = new JPanel(new FlowLayout());
		southPanel.add(btn_save);
		southPanel.add(btn_clear);
		this.add(BorderLayout.SOUTH, southPanel);
		
		btn_save.setEnabled(false);
		btn_clear.setEnabled(false);
	}
	
	private void setupButtons() {
		
		if(user instanceof Volunteer) {
			btn_editEmail.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String input = JOptionPane.showInputDialog(null, "Enter new Email:");
					if(input != null) {
						lbl_userEmail.setText(input);
						btn_save.setEnabled(true);
						btn_clear.setEnabled(true);
					}
				}
			});
			btn_editAge.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String input = JOptionPane.showInputDialog(null, "Enter new Age:");
					if(input != null) {
						try {
							lbl_userAge.setText("" + Integer.parseInt(input));
							btn_save.setEnabled(true);
							btn_clear.setEnabled(true);
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Error: must enter number", "Input error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			btn_editPhysicalLevel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String input = JOptionPane.showInputDialog(null, "Enter new Age:");
					if(input != null) {
						try {
							lbl_userPhysicalLevel.setText("" + Integer.parseInt(input));
							btn_save.setEnabled(true);
							btn_clear.setEnabled(true);
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Error: must enter number", "Input error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		} else if(user instanceof ParkManager) {
			btn_editEmail.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String input = JOptionPane.showInputDialog(null, "Enter new Email:");
					if(input != null) {
						lbl_userEmail.setText(input);
						btn_save.setEnabled(true);
						btn_clear.setEnabled(true);
					}
				}
			});
		}
		
		btn_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(user instanceof Volunteer) {
					((Volunteer) user).setVolunteerEmail(lbl_userEmail.getText());
					((Volunteer) user).setVolunteerAge(Integer.parseInt(lbl_userAge.getText()));
					((Volunteer) user).setPermissionLevel(Integer.parseInt(lbl_userPhysicalLevel.getText()));
				} else if(user instanceof ParkManager) {
					((ParkManager) user).setManagerEmail(lbl_userEmail.getText());
				}
				btn_save.setEnabled(false);
				btn_clear.setEnabled(false);
			}
		});
		
		btn_clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(user instanceof Volunteer) {
					lbl_userEmail.setText(((Volunteer) user).getVolunteerEmail());
					lbl_userAge.setText("" + ((Volunteer) user).getVolunteerAge());
					lbl_userPhysicalLevel.setText("" + ((Volunteer) user).getVolunteerPhysicalLevel());
					
				} else if(user instanceof ParkManager) {
					lbl_userEmail.setText(((ParkManager) user).getManagerEmail());
					
				}
				btn_save.setEnabled(false);
				btn_clear.setEnabled(false);
			}
		});

	}
}

