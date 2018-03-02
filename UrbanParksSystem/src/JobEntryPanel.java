import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
//import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JobEntryPanel extends JFrame {
	private SystemData UrbanParksSystem;
	
	private Job newJob;
	
	private JPanel jobEntryPanel;
	private JPanel southPanel;
	private JPanel buttonSuitePanel;
	private JPanel titlePanel;
	private JPanel datePanel;
	private JPanel lengthPanel;
	private JPanel reqPanel;
	private JPanel volunteerPanel;
	private JPanel locationPanel;
	private JPanel descriptionPanel;
	
	private JButton mainMenuButton;
	private JButton submitJobButton;
	private JButton cancelButton;
	
	private JLabel titleLabel;
	private JLabel dateLabel;
	private JLabel dayLengthLabel;
	private JLabel reqLabel;
	private JLabel numVolunteersLabel;
	private JLabel locationLabel;
	private JLabel descriptionLabel;
	
	private JTextField titleTextField;
	private JTextField dateTextField;
	private JTextField dayLengthTextField;
	private JTextField reqTextField;
	private JTextField numVolunteersTextField;
	private JTextField locationTextField;
	private JTextField descriptionTextField;
	
	//private JTextArea jobDetails;
	
	private JList<String> jobList;

	public JobEntryPanel(SystemData urbanParksSystem) {
		this.UrbanParksSystem = urbanParksSystem;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Add a New Job");
		
		initDetailsPanel();
		setUpButtons();
		initEntryPanel();
		
		add(southPanel, BorderLayout.SOUTH);
		
		
		
		pack();
		setVisible(true);
	}

	private void setUpButtons() {
		buttonSuitePanel = new JPanel();
		if (UrbanParksSystem.getCurrentUser() instanceof ParkManager) {
			submitJobButton = new JButton("Submit Job");
			cancelButton = new JButton("Cancel");
			
			submitJobButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						Job j = new Job(titleTextField.getText(), dateTextField.getText(), reqTextField.getText(), Integer.parseInt(numVolunteersTextField.getText()), locationTextField.getText(), descriptionTextField.getText(), Integer.parseInt(dayLengthTextField.getText()));
						UrbanParksSystem.submitJob(j);
						JOptionPane.showMessageDialog(null, "Job Submitted.");
						//TODO go back to previous menu
					} catch (NullPointerException e) {
						
					}
				}
			});
			
			cancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			
			buttonSuitePanel.add(submitJobButton);
			buttonSuitePanel.add(cancelButton);
			
		} 
		
		mainMenuButton = new JButton("Main menu");
		
		//TODO what about clicking main menu in middle of job entry
		mainMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UrbanParksSystem.saveData();
				dispose();
			}
		});
		
		buttonSuitePanel.add(mainMenuButton);
		
		southPanel.add(buttonSuitePanel);
		
	}

	private void initDetailsPanel() {
		southPanel = new JPanel(new GridLayout(7, 2));
		southPanel.setPreferredSize(new Dimension(320, 450));
	}

	private void initEntryPanel() {
		//jobEntryPanel = new JPanel();
		jobEntryPanel = new JPanel(new GridLayout(7, 2));
		
		titleLabel = new JLabel("Title: ");
		titleTextField = new JTextField("", 15);
		
		dateLabel = new JLabel("Date: ");
		dateTextField = new JTextField("", 5);
		
		dayLengthLabel = new JLabel("Length in Days: ");
		dayLengthTextField = new JTextField("", 5);
		
		reqLabel = new JLabel("Requirements: ");
		reqTextField = new JTextField("", 15);
		
		numVolunteersLabel = new JLabel("Number of Volunteers: ");
		numVolunteersTextField = new JTextField("", 15);
		
		locationLabel = new JLabel("Location: ");
		locationTextField = new JTextField("", 15);
		
		descriptionLabel = new JLabel("Description: ");
		descriptionTextField = new JTextField("", 15);

		
		
		jobEntryPanel.setPreferredSize(new Dimension(400, 400));
		
		
		jobEntryPanel.add(titleLabel);
		jobEntryPanel.add(titleTextField);
		
		jobEntryPanel.add(dateLabel);
		jobEntryPanel.add(dateTextField);
		
		jobEntryPanel.add(dayLengthLabel);
		jobEntryPanel.add(dayLengthTextField);
		
		jobEntryPanel.add(reqLabel);
		jobEntryPanel.add(reqTextField);
		
		jobEntryPanel.add(numVolunteersLabel);
		jobEntryPanel.add(numVolunteersTextField);
		
		jobEntryPanel.add(locationLabel);
		jobEntryPanel.add(locationTextField);
		
		jobEntryPanel.add(descriptionLabel);
		jobEntryPanel.add(descriptionTextField);
		
		add(jobEntryPanel);
		
	}
	
	
	
	public String getTitle() {
		return titleTextField.getText();
	}
	
	public String getDate() {
		return dateTextField.getText();
	}
	
	public String getLength() {
		return dayLengthTextField.getText();
	}
	
	public String getReq() {
		return reqTextField.getText();
	}
	
	public String getNumVol() {
		return numVolunteersTextField.getText();
	}
	
	public String getLoc() {
		return locationTextField.getText();
	}
	
	public String getDescription() {
		return descriptionTextField.getText();
	}
}
