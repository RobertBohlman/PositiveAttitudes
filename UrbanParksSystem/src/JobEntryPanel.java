import java.awt.BorderLayout;
import java.awt.Dimension;
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
	private JSpinner dateSpinner;
	private JSlider dayLengthSlider;
	private JTextField reqTextField;
	private JTextField numVolunteersTextField;
	private JTextField locationTextField;
	private JTextArea descriptionTextArea;
	
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
						JOptionPane.showMessageDialog(null, "You have signed up.");
						//TODO go back to previous menu
					} catch (NullPointerException e) {
						
					}
				}
			});
			
			cancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "Job entry cancelled.");
					//TODO clear entered values / go back to previous menu
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
		southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(320, 450));
		//jobDetails = new JTextArea();
		//jobDetails.setPreferredSize(new Dimension(300, 300));
		//centerPanel.add(jobDetails);
		
	}

	private void initEntryPanel() {
		jobEntryPanel = new JPanel();
		
		titlePanel = new JPanel(new BorderLayout());
		titleLabel = new JLabel("Title: ");
		titleTextField = new JTextField("", 15);
		
		datePanel = new JPanel(new BorderLayout());
		dateLabel = new JLabel("Date: ");
		dateSpinner = new JSpinner();
		
		lengthPanel = new JPanel(new BorderLayout());
		dayLengthLabel = new JLabel("Length in Days: ");
		dayLengthSlider = new JSlider();
		
		reqPanel = new JPanel(new BorderLayout());
		reqLabel = new JLabel("Requirements: ");
		reqTextField = new JTextField("", 15);
		
		volunteerPanel = new JPanel(new BorderLayout());
		numVolunteersLabel = new JLabel("Number of Volunteers: ");
		numVolunteersTextField = new JTextField("", 15);
		
		locationPanel = new JPanel(new BorderLayout());
		locationLabel = new JLabel("Location: ");
		locationTextField = new JTextField("", 15);
		
		descriptionPanel = new JPanel(new BorderLayout());
		descriptionLabel = new JLabel("Description: ");
		descriptionTextArea = new JTextArea();

		
		
		jobEntryPanel.setPreferredSize(new Dimension(400, 400));
		
		
		titlePanel.add(titleLabel, BorderLayout.WEST);
		titlePanel.add(titleTextField);
		jobEntryPanel.add(titlePanel);
		
		datePanel.add(dateLabel, BorderLayout.WEST);
		datePanel.add(dateSpinner);
		jobEntryPanel.add(datePanel);
		
		lengthPanel.add(dayLengthLabel, BorderLayout.WEST);
		lengthPanel.add(dayLengthSlider);
		jobEntryPanel.add(lengthPanel);
		
		reqPanel.add(reqLabel, BorderLayout.WEST);
		reqPanel.add(reqTextField);
		jobEntryPanel.add(reqPanel);
		
		volunteerPanel.add(numVolunteersLabel, BorderLayout.WEST);
		volunteerPanel.add(numVolunteersTextField);
		jobEntryPanel.add(volunteerPanel);
		
		locationPanel.add(locationLabel, BorderLayout.WEST);
		locationPanel.add(locationTextField);
		jobEntryPanel.add(locationPanel);
		
		descriptionPanel.add(descriptionLabel, BorderLayout.WEST);
		descriptionPanel.add(descriptionTextArea);
		jobEntryPanel.add(descriptionPanel);
		
		add(jobEntryPanel);
		
	}
}
