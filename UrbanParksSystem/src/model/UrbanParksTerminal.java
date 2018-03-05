package model;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import view.EditUserPanel;
import view.JobDisplayPanel;
import view.JobEntryPanel;
import view.UserLoginPanel;
import view.WelcomePanel;

/**
 * Main program. Urban parks terminal GUI
 * Responsible for all back end data and gui instantiation.
 * @author Jenna Hand, Kristi Anna Stageberg, Robert Bohlman, Jacob Reed, Aaron Hammers
 *
 */
public class UrbanParksTerminal extends JFrame{
	private static final long serialVersionUID = -5601671266866084219L;
	private static final int USER_LOG_IN = 0;
	public static final int VOLUNTEER = 1;
	public static final int PARK_MANAGER = 3;
	private static final int EMPLOYEE = 7;
	private static SystemData UrbanParksSystem;
	private static Scanner scan;
	private UrbanParksTerminal self;
	private JPanel currentPanel;
	private JMenuBar currentMenu;
	private JPanel userLoginPanel;
	private JPanel welcomePanel;
	private JPanel parkManagerJobPanel = new JPanel();
	private JPanel parkManagerSubmitPanel = new JPanel();
	private JPanel volunteerYourJobsPanel = new JPanel();
	private JPanel employeePanel = new JPanel();
	private JMenuBar userLoginMenu = new JMenuBar();
	private JMenuBar parkManagerMenu = new JMenuBar();
	private JMenuBar volunteerMenu = new JMenuBar();
	private JMenuBar employeeMenu = new JMenuBar();
	
	/**
	 * Constructor
	 */
	public UrbanParksTerminal() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Welcome to Urban Parks!");
		
		initializeMenus();
		initializePanels();
		
		self = this;
		
		changeState(USER_LOG_IN);
		pack();
		setVisible(true);
	}
	

	/**
	 * Main Method
	 * @param args Command Line Arguments
	 */
	public static void main(String[] args) {
		UrbanParksSystem = new SystemData();
		scan = new Scanner(System.in);
		

		/*
		 * Checks if the serialized data is existent
		 * if not then load defaults
		 * if it is then load serialized.
		 */
		if(!UrbanParksSystem.isUserMapNull()) {
			UrbanParksSystem.loadUsers();
		} else {
			UrbanParksSystem.addUser(new Employee("Carol", 0));
			UrbanParksSystem.addUser(new ParkManager("Frank", 1, "why@gmail.com"));
			UrbanParksSystem.addUser(new Volunteer("Billy", 2, 34, "umm@gmail.com", 3));
		}
		
		//GUI Calls
		UrbanParksTerminal terminal = new UrbanParksTerminal();
		//JobDisplayPanel JDP = new JobDisplayPanel(UrbanParksSystem); //Debug, to be deleted
		
		UrbanParksSystem.saveData(); //Serialize userMap and jobList
		scan.close();
	}
	
	/**
	 * Change state based on GUI inputs
	 * @param consoleState int
	 */
	public void changeState(int consoleState) {
		if (currentPanel != null && currentMenu != null) {
			remove(currentPanel);
			remove(currentMenu);
		}
		switch (consoleState) {
		case USER_LOG_IN:
			currentPanel = userLoginPanel;
			currentMenu = userLoginMenu;
			break;
			
		case VOLUNTEER:
			currentPanel = welcomePanel;
			currentMenu = volunteerMenu;
			break;
			
		case PARK_MANAGER:
			currentPanel = welcomePanel;
			currentMenu = parkManagerMenu;
			break;
			
		case EMPLOYEE:
			currentPanel = welcomePanel;
			currentMenu = employeeMenu;
			break;
			
		default:
			break;
		}
		
		add(currentPanel, BorderLayout.CENTER);
		setJMenuBar(currentMenu);
		pack();
		revalidate();
		repaint();
	}
	
	/**
	 * Change GUI panel based on panel state
	 * @param panelState int
	 */
	private void changePanel(int panelState) {
		if (currentPanel != null) {
			remove(currentPanel);
		}
		switch (panelState) {
		default:
			break;
		}

		add(currentPanel, BorderLayout.CENTER);
		pack();
		revalidate();
		repaint();
	}
	
	/**
	 * Initialize all panels
	 */
	private void initializePanels() {
		welcomePanel = new WelcomePanel();
		initializeUserLoginPanel();
		initializeParkManagerJobPanel();
		initializeParkManagerSubmitPanel();
		initializeVolunteerYourJobsPanel();
		initializeVolunteerAvailableJobsPanel();
		initializeEmployeePanel();

	}
	
	/**
	 * Initialize all menus
	 */
	private void initializeMenus() {
		initializeParkManagerMenu();
		initializeVolunteerMenu();
		initializeEmployeeMenu();

	}
	
	/**
	 * Initialize Park Manager Menu
	 */
	private void initializeParkManagerMenu() {
		JMenu viewJobs = new JMenu("View Jobs");
		JMenu submitJobs = new JMenu("Submit Jobs");
		
		parkManagerMenu.add(viewJobs);
		parkManagerMenu.add(submitJobs);
		
		JMenuItem allJobs = new JMenuItem("All Jobs");
		allJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(currentPanel);
				currentPanel = new JobDisplayPanel(UrbanParksSystem, self, false);
				add(currentPanel, BorderLayout.CENTER);
				pack();
			}});
		JMenuItem submitNewJob = new JMenuItem("New Job");
		submitNewJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JobEntryPanel JEP = new JobEntryPanel(UrbanParksSystem);
			}});
				
//		JMenuItem submitNewJob = new JMenu("New Job");
//		allJobs.addActionListener(makeMenuItemListener(PARK_MANAGER_JOB_SUBMIT));		
		viewJobs.add(allJobs);
		submitJobs.add(submitNewJob);
	}
	
	/**
	 * Initialize volunteer menu
	 */
	private void initializeVolunteerMenu() {
		JMenu viewJobs = new JMenu("View Jobs");
		JMenu settings = new JMenu("Settings");
		
		volunteerMenu.add(viewJobs);
		volunteerMenu.add(settings);
		
		JMenuItem yourJobs = new JMenuItem("Your Jobs");
		yourJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(currentPanel);
				currentPanel = new JobDisplayPanel(UrbanParksSystem, self, true);
				add(currentPanel, BorderLayout.CENTER);
				pack();
			}});
		
		JMenuItem availableJobs = new JMenuItem("Available Jobs");
		availableJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(currentPanel);
				currentPanel = new JobDisplayPanel(UrbanParksSystem, self, false);
				add(currentPanel, BorderLayout.CENTER);
				pack();
			}});
		
		JMenuItem editInfo = new JMenuItem("Edit Information");
		editInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(currentPanel);
				currentPanel = new EditUserPanel(UrbanParksSystem);
				add(currentPanel, BorderLayout.CENTER);
				pack();
			}});
		
		viewJobs.add(yourJobs);
		viewJobs.add(availableJobs);
		settings.add(editInfo);
	}
	
	/**
	 * Makes the menu item listeners
	 * @param panelState int
	 * @return ActionListener
	 */
	private ActionListener makeMenuItemListener(int panelState) { 
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePanel(panelState);
			}};
		
	}
	
	/**
	 * Initialize employee menus
	 */
	private void initializeEmployeeMenu() {
		JMenu jobs = new JMenu("Jobs");
		JMenu volunteers = new JMenu("Volunteers");
		JMenu managers = new JMenu("Park Managers");
		JMenu settings = new JMenu("Settings");
		
		employeeMenu.add(jobs);
		employeeMenu.add(volunteers);
		employeeMenu.add(managers);
		employeeMenu.add(settings);
		
	}
	
	/**
	 * Initialize user login panel
	 */
	private void initializeUserLoginPanel() {
		userLoginPanel = new UserLoginPanel(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = ((UserLoginPanel) userLoginPanel).getUserName();
				boolean pass = false;
				for(Integer i : UrbanParksSystem.getUserMap().keySet()) {
					if(user.hashCode() == i) {
						pass = true;
					}
				}
				if(pass) {
					UrbanParksSystem.setCurrentUser(user);
					if (UrbanParksSystem.getCurrentUser() instanceof Employee) {
						changeState(EMPLOYEE);
					} else if (UrbanParksSystem.getCurrentUser() instanceof ParkManager) {
						changeState(PARK_MANAGER);
					} else if (UrbanParksSystem.getCurrentUser() instanceof Volunteer) {
						changeState(VOLUNTEER);
					}
					
				} else {
					JOptionPane.showMessageDialog(null, ("No user named " + user + " exists!"), "No user found",
							JOptionPane.ERROR_MESSAGE);
				}
			}});
	}
	
	/**
	 * Initialize park manager job panel
	 */
	private void initializeParkManagerJobPanel() {
		parkManagerJobPanel.setSize(500, 500);
	}
	
	/**
	 * Initialize park manager submit panel
	 */
	private void initializeParkManagerSubmitPanel() {
		parkManagerSubmitPanel.setSize(500, 500);
	}
	
	/**
	 * Initialize volunteer job panel
	 */
	private void initializeVolunteerYourJobsPanel() {
		volunteerYourJobsPanel.setSize(500, 500);
	}
	
	/**
	 * Initialize volunteer available jobs panel
	 */
	private void initializeVolunteerAvailableJobsPanel() {
		volunteerYourJobsPanel.setSize(500, 500);
	}
	
	/**
	 * Initialize employee panel
	 */
	private void initializeEmployeePanel() {
		employeePanel.setSize(500, 500);
	}
	
}
