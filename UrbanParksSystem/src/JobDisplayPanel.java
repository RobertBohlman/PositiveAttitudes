import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JobDisplayPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4544875371839943574L;

	private SystemData UrbanParksSystem;
	
	private Job selectedJob;
	
	private JPanel jobListPanel;
	private JPanel centerPanel;
	private JPanel buttonSuitePanel;
	
	private JButton mainMenuButton;
	private JButton deleteJobButton;
	private JButton editJobButton;
	private JButton volunteerButton;
	private JButton unvolunteerButton;
	
	private JTextArea jobDetails;
	
	private JList<String> jobList;

	public JobDisplayPanel(SystemData urbanParksSystem) {
		this.UrbanParksSystem = urbanParksSystem;
		
		initDetailsPanel();
		setUpButtons();
		initListPanel();
		
		add(centerPanel, BorderLayout.CENTER);
		
		
		setVisible(true);
	}

	private void setUpButtons() {
		buttonSuitePanel = new JPanel();
		if (UrbanParksSystem.getCurrentUser() instanceof Volunteer) {
			volunteerButton = new JButton("Volunteer");
			unvolunteerButton = new JButton("Un-Volunteer");
			
			volunteerButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						if (UrbanParksSystem.signUpForJob(selectedJob)) {
							JOptionPane.showMessageDialog(null, "You have signed up");     
						} else {
							JOptionPane.showMessageDialog(null, "Sorry, you cannot sign up for this job."); 
						}
					} catch (NullPointerException e) {
						
					}
				}
			});
			
			unvolunteerButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//Todo
				}
			});
			
			buttonSuitePanel.add(volunteerButton);
			buttonSuitePanel.add(unvolunteerButton);
			
		} else if (UrbanParksSystem.getCurrentUser() instanceof ParkManager) {
			deleteJobButton = new JButton("Delete Job");
			editJobButton = new JButton("Edit Job");
			
			deleteJobButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//Deletes the selected job
				}
			});
			
			editJobButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//Opens window to edit job info
				}
			});
			
			buttonSuitePanel.add(deleteJobButton);
			buttonSuitePanel.add(editJobButton);
		}
		
		mainMenuButton = new JButton("Main menu");
		
		mainMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UrbanParksSystem.saveData();
			}
		});
		
		buttonSuitePanel.add(mainMenuButton);
		
		centerPanel.add(buttonSuitePanel);
		
	}

	private void initDetailsPanel() {
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(320, 450));
		jobDetails = new JTextArea();
		jobDetails.setPreferredSize(new Dimension(300, 300));
		centerPanel.add(jobDetails);
		
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
				
				selectedJob = displayJob;
			}
			
		});
		jobListPanel.add(jobList);
		add(jobListPanel, BorderLayout.WEST);
		
	}
}
