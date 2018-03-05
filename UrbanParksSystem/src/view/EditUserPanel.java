package view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.AbstractUser;
import model.Employee;
import model.ParkManager;
import model.SystemData;
import model.Volunteer;

/**
 * Edit user panel class that allows users to edit their account info
 * @author Jenna Hand, Kristi Anna Stageberg, Robert Bohlman, Jacob Reed, Aaron Hammers
 *
 */
public class EditUserPanel extends JPanel{
	private static final long serialVersionUID = -8262220730250909489L;
	SystemData system;
	AbstractUser user;
	private static JLabel lbl_email = new JLabel("Email: ");
	private static JLabel lbl_age = new JLabel("Age: ");
	private static JLabel lbl_physicalLevel = new JLabel("Physical Level:");
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

	/**
	 * Constructor
	 * @param system SystemData 
	 */
	public EditUserPanel(SystemData system) {
		this.system = system;
		user = system.getCurrentUser();
		lbl_userName.setText("User details for:    " + user.getUserName());
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
		
		//Create GUI
		setupButtons();
		this.setLayout(new BorderLayout());
		createNorth();
		createCenter();
		createSouth();
		this.setVisible(true);
	}
	
	/**
	 * North panel setup
	 */
	private void createNorth() {
		JPanel northPanel = new JPanel(new FlowLayout());
		northPanel.add(lbl_userName);
		this.add(BorderLayout.NORTH, northPanel);
	}
	
	/**
	 * Center panel setup
	 */
	private void createCenter() {
		JPanel centerPanel = new JPanel(new GridLayout(1,3));
		
		JPanel leftPanel = new JPanel(new GridLayout(3,1));
		for (JLabel l : detailLabels) {
			JPanel p = new JPanel(new FlowLayout());
			p.add(l);
			leftPanel.add(p);
		}
		
		JPanel rightPanel = new JPanel(new GridLayout(3,1));
		for (JButton b : editButtons) {
			JPanel p = new JPanel(new FlowLayout());
			p.add(b);
			rightPanel.add(p);
		}
		
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new GridLayout(3,1));
		for (JLabel l : userInfoLabels) {
			JPanel p = new JPanel(new FlowLayout());
			p.add(l);
			middlePanel.add(p);
		}
		
		//Center panel startup
		centerPanel.add(leftPanel);
		centerPanel.add(middlePanel);
		centerPanel.add(rightPanel);
		this.add(BorderLayout.CENTER, centerPanel);
	}
	
	/**
	 * South panel setup
	 */
	private void createSouth() {
		JPanel southPanel = new JPanel(new FlowLayout());
		southPanel.add(btn_save);
		southPanel.add(btn_clear);
		this.add(BorderLayout.SOUTH, southPanel);
		btn_save.setEnabled(false);
		btn_clear.setEnabled(false);
	}
	
	/**
	 * Button setup for edit info panels
	 */
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
				system.saveData();
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

