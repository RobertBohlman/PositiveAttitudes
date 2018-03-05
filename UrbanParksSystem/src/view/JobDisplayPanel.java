package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DateFormatter;

import model.Employee;
import model.Job;
import model.ParkManager;
import model.SystemData;
import model.UrbanParksTerminal;
import model.Volunteer;

public class JobDisplayPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4544875371839943574L;

	private SystemData UrbanParksSystem;
	private UrbanParksTerminal myParentFrame;
	
	/**
	 * Boolean flag for whether or not this panel is being used for the personal job display
	 */
	private boolean personal;
	
	private Job selectedJob;
	
	private JPanel jobListPanel;
	private JPanel centerPanel;
	private JPanel buttonSuitePanel;
	
	private JButton mainMenuButton;
	private JButton deleteJobButton;
	private JButton editJobButton;
	private JButton volunteerButton;
	private JButton unvolunteerButton;
	private JButton jobByDate;
	
	private JTextArea jobDetails;
	
	private JList<String> jobList;

	public JobDisplayPanel(SystemData urbanParksSystem, UrbanParksTerminal frame, boolean isPersonal) {
		this.UrbanParksSystem = urbanParksSystem;
		myParentFrame = frame;
		personal = isPersonal;
		
		setLayout(new BorderLayout());
		
		initDetailsPanel();
		setUpButtons();
		initListPanel();
		
		add(centerPanel, BorderLayout.CENTER);
		
		
		setVisible(true);
	}

	private void setUpButtons() {
		buttonSuitePanel = new JPanel();
		buttonSuitePanel.setPreferredSize(new Dimension(350,100));
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
					try {
						if (UrbanParksSystem.removeUserJob(selectedJob)) {
							initListPanel();
							jobDetails.setText("");
							jobListPanel.repaint();
							JOptionPane.showMessageDialog(null, "You have un-volunteered.");
							
						} else {
							JOptionPane.showMessageDialog(null, "Could not remove you from this job!");
						}
						
					} catch (NullPointerException e) {
						
					}
				}
			});
			
			//Only add the volunteer button if this is NOT the personal job list
			//(Doesn't make sense to volunteer for jobs you're already signed up for).
			if (!personal) {
				buttonSuitePanel.add(volunteerButton);
			}
			buttonSuitePanel.add(unvolunteerButton);
			
		} else if (UrbanParksSystem.getCurrentUser() instanceof ParkManager) {
			deleteJobButton = new JButton("Delete Job");
			editJobButton = new JButton("Edit Job");
			
			deleteJobButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (UrbanParksSystem.removeJob(selectedJob)) {
						initListPanel();
						jobDetails.setText("");
						jobListPanel.repaint();
						JOptionPane.showMessageDialog(null, "Job has been removed.");
						
					} else {
						JOptionPane.showMessageDialog(null, "Could not remove job!");
					}
					
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
		} else if (UrbanParksSystem.getCurrentUser() instanceof Employee) {
			
			JLabel from = new JLabel("From:");
			JLabel to = new JLabel("To:");
			
			SpinnerDateModel model = new SpinnerDateModel();
			JSpinner spinnerStart = new JSpinner(model);

			JSpinner.DateEditor editorStart = new JSpinner.DateEditor(spinnerStart, "yyyy.MM.dd");
			DateFormatter formatter = (DateFormatter)editorStart.getTextField().getFormatter();
			formatter.setAllowsInvalid(false); 
			formatter.setOverwriteMode(true);
			
			JSpinner spinnerEnd = new JSpinner(model);

			JSpinner.DateEditor editorEnd = new JSpinner.DateEditor(spinnerEnd, "yyyy.MM.dd");
			formatter.setAllowsInvalid(false); 
			formatter.setOverwriteMode(true);

			jobByDate = new JButton("View By Date");
			jobByDate.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					jobListPanel = new JPanel();
					
					jobList = new JList<String>(UrbanParksSystem.seeJobsByDate(spinnerStart.getValue(), spinnerEnd.getValue()));
					
				}
				
			});
			
			buttonSuitePanel.add(from);
			buttonSuitePanel.add(spinnerStart);
			buttonSuitePanel.add(to);
			buttonSuitePanel.add(spinnerEnd);
			buttonSuitePanel.add(jobByDate);
			
		}
		
		
		mainMenuButton = new JButton("Main menu");
		
		mainMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (UrbanParksSystem.getCurrentUser() instanceof Volunteer) {
					myParentFrame.changeState(UrbanParksTerminal.VOLUNTEER);
					
				} else if (UrbanParksSystem.getCurrentUser() instanceof ParkManager) {
					myParentFrame.changeState(UrbanParksTerminal.PARK_MANAGER);
				}
				UrbanParksSystem.saveData();
			}
		});
		
		add(mainMenuButton, BorderLayout.SOUTH);
		
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
		if (jobListPanel != null) {
			remove(jobListPanel);
		}
		jobListPanel = new JPanel();
		if (personal) {
			jobList = new JList<String>(UrbanParksSystem.seeMyJobs());
			
		} else {
			jobList = new JList<String>(UrbanParksSystem.seeJobs());
		}
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
