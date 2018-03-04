import java.awt.BorderLayout;
import java.awt.Component;
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
	JLabel lbl_name = new JLabel();
	JLabel lbl_email = new JLabel();
	JLabel lbl_age = new JLabel();
	JLabel lbl_physicalLevel = new JLabel();
	JLabel lbl_saveChanges = new JLabel();
	
	ArrayList<JLabel> detailLabels = new ArrayList<JLabel>(); 
	
	JButton btn_editEmail = new JButton("Edit");
	JButton btn_editAge = new JButton("Edit");
	JButton btn_editPhysicalLevel = new JButton("Edit");

	ArrayList<JButton> editButtons = new ArrayList<JButton>(); 
	
	JButton btn_save = new JButton("save");
	JButton btn_clear = new JButton("clear");

	public EditUserPanel(AbstractUser passedUser) {
		user = passedUser;
		lbl_name.setText(user.getUserName());
		if(user instanceof Volunteer) {
			
			lbl_email.setText(((Volunteer) user).getVolunteerEmail());
			editButtons.add(btn_editEmail);
			
			
			lbl_age.setText("" + ((Volunteer) user).getVolunteerAge());
			editButtons.add(btn_editAge);
			
			lbl_physicalLevel.setText("" + ((Volunteer) user).getVolunteerPhysicalLevel());
			editButtons.add(btn_editPhysicalLevel);
			
		} else if(user instanceof ParkManager) {
			
			lbl_email.setText(((ParkManager) user).getManagerEmail());
			lbl_email.setAlignmentX(Component.RIGHT_ALIGNMENT);
			editButtons.add(btn_editEmail);
			
		} else if(user instanceof Employee) {
			lbl_name.setText(user.getUserName());
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
		northPanel.add(lbl_name);
		this.add(BorderLayout.NORTH, northPanel);
	}
	
	private void createCenter() {
		JPanel centerPanel = new JPanel(new GridLayout(1,2));
		
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
		for (JButton b : editButtons) {
			eastPanel.add(b);
		}
		
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		for (JLabel l : detailLabels) {
			westPanel.add(l);
		}
		centerPanel.add(westPanel);
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
						lbl_email.setText(input);
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
							lbl_age.setText("" + Integer.parseInt(input));
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
							lbl_physicalLevel.setText("" + Integer.parseInt(input));
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
						lbl_email.setText(input);
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
					((Volunteer) user).setVolunteerEmail(lbl_email.getText());
					((Volunteer) user).setVolunteerAge(Integer.parseInt(lbl_age.getText()));
					((Volunteer) user).setPermissionLevel(Integer.parseInt(lbl_physicalLevel.getText()));
				} else if(user instanceof ParkManager) {
					((ParkManager) user).setManagerEmail(lbl_email.getText());
				}
				btn_save.setEnabled(false);
				btn_clear.setEnabled(false);
			}
		});
		
		btn_clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(user instanceof Volunteer) {
					lbl_email.setText(((Volunteer) user).getVolunteerEmail());
					lbl_age.setText("" + ((Volunteer) user).getVolunteerAge());
					lbl_physicalLevel.setText("" + ((Volunteer) user).getVolunteerPhysicalLevel());
					
				} else if(user instanceof ParkManager) {
					lbl_email.setText(((ParkManager) user).getManagerEmail());
					
				}
				btn_save.setEnabled(false);
				btn_clear.setEnabled(false);
			}
		});

	}
}

