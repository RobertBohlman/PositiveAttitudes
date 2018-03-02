import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
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
	
	private JPanel jobListPanel;
	private JPanel centerPanel;
	private JPanel buttonSuitePanel;
	
	private JButton mainMenuButton;
	private JButton submitJobButton;
	private JButton cancelButton;
	
	private JTextField titleTextField;
	private JSpinner dateSpinner;
	private JSlider dayLengthSlider;
	private JTextField reqTextField;
	private JTextField numVolunteersTextField;
	private JTextField locationTextField;
	private JTextArea descriptionTextArea;
	
	private JTextArea jobDetails;
	
	private JList<String> jobList;

	public JobEntryPanel(SystemData urbanParksSystem) {
		this.UrbanParksSystem = urbanParksSystem;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Add a New Job");
		
		initDetailsPanel();
		setUpButtons();
		initListPanel();
		
		add(centerPanel, BorderLayout.CENTER);
		
		
		
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
						if (UrbanParksSystem.signUpForJob(newJob)) {
							JOptionPane.showMessageDialog(null, "You have signed up");     
						} else {
							JOptionPane.showMessageDialog(null, "Sorry, you cannot sign up for this job."); 
						}
					} catch (NullPointerException e) {
						
					}
				}
			});
			
			cancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//Todo
				}
			});
			
			buttonSuitePanel.add(submitJobButton);
			buttonSuitePanel.add(cancelButton);
			
		} 
		
		mainMenuButton = new JButton("Main menu");
		
		mainMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UrbanParksSystem.saveData();
				dispose();
			}
		});
		
		buttonSuitePanel.add(mainMenuButton);
		
		centerPanel.add(buttonSuitePanel);
		
	}

	private void initDetailsPanel() {
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(320, 450));
		//jobDetails = new JTextArea();
		//jobDetails.setPreferredSize(new Dimension(300, 300));
		//centerPanel.add(jobDetails);
		
	}

	private void initListPanel() {
		jobListPanel = new JPanel();
		jobList = new JList<String>(UrbanParksSystem.seeJobs());
		jobList.setPreferredSize(new Dimension(200, 400));
		jobList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jobList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				Map<Integer, Job> jobMap = UrbanParksSystem.getJobMap(); 
				int selectNumber = jobList.getSelectedIndex();
				Job displayJob = (Job) jobMap.values().toArray()[selectNumber];
				
				jobDetails.setText("Job Details: " 
				+ "\nTitle: " + displayJob.myTitle
				+ "\nDate: " + displayJob.myDateString
				+ "\nRequirements: " + displayJob.myRequirements + ", " + displayJob.myNoVolunteers + " volunteers"
				+ "\nLocation: " + displayJob.myLocation
				+ "\nDescription: " + displayJob.myDescription);
				
				newJob = displayJob;
			}
			
		});
		jobListPanel.add(jobList);
		add(jobListPanel, BorderLayout.WEST);
		
	}
}
