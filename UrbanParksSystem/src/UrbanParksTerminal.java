import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UrbanParksTerminal extends JFrame{
	private static final long serialVersionUID = -5601671266866084219L;
	private static SystemData UrbanParksSystem;
	private static Scanner scan;
	
	
	private static int consoleState = 0;
	
	private static final int USER_LOG_IN = 0;
	private static final int VOLUNTEER = 1;
	private static final int AVAILABLE_JOB_SCREEN = 2;
	private static final int PARK_MANAGER = 3;
	private static final int PARK_MANAGER_JOB_SUBMIT = 4;
	private static final int VOLUNTEER_SIGNED_UP_JOBS = 5;
	private static final int PARK_MANAGER_JOB_VIEW = 6;
	private static final int EMPLOYEE = 7;
	private static final int MANAGER_SUBMITTED_JOBS = 8;
	private static final int VOLUNTEER_EDIT_INFO = 9;
	
	private static final int END = 99;
	
	
	private JPanel currentPanel;
	private JMenuBar currentMenu;
	
	private JPanel userLoginPanel;
	private JPanel welcomePanel;
	private JPanel parkManagerJobPanel = new JPanel();
	private JPanel parkManagerSubmitPanel = new JPanel();
	private JPanel volunteerYourJobsPanel = new JPanel();
	private JPanel volunteerAvailableJobsPanel = new JPanel();
	private JPanel employeePanel = new JPanel();

	private JMenuBar userLoginMenu = new JMenuBar();
	private JMenuBar parkManagerMenu = new JMenuBar();
	private JMenuBar volunteerMenu = new JMenuBar();
	private JMenuBar employeeMenu = new JMenuBar();
	
	public UrbanParksTerminal() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Welcome to Urban Parks!");
		
		initializeMenus();
		initializePanels();
		
		changeState(USER_LOG_IN);
		setVisible(true);
	}
	

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
	

	private void changeState(int consoleState) {
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
			currentPanel = volunteerYourJobsPanel;
			currentMenu = volunteerMenu;
			break;
			
		case PARK_MANAGER:
			currentPanel = parkManagerJobPanel;
			currentMenu = parkManagerMenu;
			break;
			
		case EMPLOYEE:
			currentPanel = employeePanel;
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
	
	private void initializePanels() {
		welcomePanel = new WelcomePanel(UrbanParksSystem);
		initializeUserLoginPanel();
		initializeParkManagerJobPanel();
		initializeParkManagerSubmitPanel();
		initializeVolunteerYourJobsPanel();
		initializeVolunteerAvailableJobsPanel();
		initializeEmployeePanel();

	}
	
	private void initializeMenus() {
		initializeParkManagerMenu();
		initializeVolunteerMenu();
		initializeEmployeeMenu();

	}
	
	private void initializeParkManagerMenu() {
		JMenu viewJobs = new JMenu("View Jobs");
		JMenu submitJobs = new JMenu("Submit Jobs");
		
		parkManagerMenu.add(viewJobs);
		parkManagerMenu.add(submitJobs);
		
		JMenuItem allJobs = new JMenuItem("All Jobs");
		allJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPanel.add(new JobDisplayPanel(UrbanParksSystem));
			}});
		
		//krizirk
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
	
	private void initializeVolunteerMenu() {
		JMenu viewJobs = new JMenu("View Jobs");
		JMenu settings = new JMenu("Settings");
		
		volunteerMenu.add(viewJobs);
		volunteerMenu.add(settings);
		
		JMenuItem yourJobs = new JMenuItem("Your Jobs");
		yourJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}});
		
		JMenuItem availableJobs = new JMenuItem("Available Jobs");
		availableJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JobDisplayPanel JDP = new JobDisplayPanel(UrbanParksSystem);
			}});
		
		JMenuItem editInfo = new JMenuItem("Edit Information");
		editInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}});
		
		viewJobs.add(yourJobs);
		viewJobs.add(availableJobs);
		settings.add(editInfo);
	}
	
	private ActionListener makeMenuItemListener(int panelState) { 
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePanel(panelState);
			}};
		
	}
	
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
	
	private void initializeParkManagerJobPanel() {
		parkManagerJobPanel.setSize(500, 500);
	}
	
	private void initializeParkManagerSubmitPanel() {
		parkManagerSubmitPanel.setSize(500, 500);
	}
	
	private void initializeVolunteerYourJobsPanel() {
		volunteerYourJobsPanel.setSize(500, 500);
	}
	
	private void initializeVolunteerAvailableJobsPanel() {
		volunteerYourJobsPanel.setSize(500, 500);
	}
	
	private void initializeEmployeePanel() {
		employeePanel.setSize(500, 500);
	}


	
	private static void displayUserLogIn() {
		System.out.println("Hello! Welcome to Urban Parks.\n"
				+ "Login with your Username: (Enter 9 to shut down)");
		String selection = scan.nextLine();
		
		if (selection.charAt(0) != '9') {
			UrbanParksSystem.setCurrentUser(selection);
			
			int selectionPermission;
			
			try {
				selectionPermission = UrbanParksSystem.getCurrentUser().getPermissionLevel();
				
			} catch (NullPointerException e) {
				System.out.println("Error, no such user exists!");
				selectionPermission = 5;
			}
			
			
			switch (selectionPermission) {
				case 0:
					consoleState = END;//Not yet implemented - reserved for employees
					break;
					
				case 1:
					//consoleState = PARK_MANAGER_MAIN_MENU;
					break;
				
				case 2:
					//consoleState = VOLUNTEER_MAIN_MENU;
					break;
					
				default:
					consoleState = USER_LOG_IN;
					break;
			}
			
		}
		
		if (selection.charAt(0) == '9') {
			consoleState = END;
		}
		
	}
	
	private static void displayEmployeeMainMenu() {
		System.out.println("Welcome to Urban Parks, Employee!\n"
				+ "Actions: \n"
				+ "1. See available jobs\n"
				+ "2. Logout");
		System.out.print("Choose an action(Enter a number): ");
		String selection = scan.nextLine();
		
		switch (selection.charAt(0)) {
			case '1':
				consoleState = AVAILABLE_JOB_SCREEN;
				break;
			case '2':
				consoleState = USER_LOG_IN;
				System.out.println("Goodbye!");
				break;
			
			default:
				break;
		}
	}
		
	private static void displayVolunteerMainMenu() {
		System.out.println("Welcome to Urban Parks, Volunteer!\n"
				+ "Actions: \n"
				+ "1. See available jobs\n"
				+ "2. View your jobs\n"
				+ "3. Edit personal info\n"
				+ "4. Logout");
		System.out.print("Choose an action(Enter a number): ");
		String selection = scan.nextLine();
		
		switch (selection.charAt(0)) {
			case '1':
				consoleState = AVAILABLE_JOB_SCREEN;
				break;
				
			case '2':
				consoleState = VOLUNTEER_SIGNED_UP_JOBS;
				break; 
			
			case '3':
				break; //Not yet implemented
				
			case '4':
				consoleState = USER_LOG_IN;
				System.out.println("Goodbye!");
				break;
			
			default:
				break;
		}
	}
	
	private static void displayParkManagerMainMenu() {
		System.out.println("Welcome to Urban Parks, Park Manager!\n"
				+ "Actions: \n"
				+ "1. Submit a new job\n"
				+ "2. View jobs\n"
				+ "3. See your submitted jobs\n"
				+ "4. Edit personal info\n"
				+ "5. Logout");
		System.out.print("Choose an action(Enter a number): ");
		String selection = scan.nextLine();
		
		switch (selection.charAt(0)) {
			case '1':
				if(!UrbanParksSystem.atMaxJobs()) {
					consoleState = PARK_MANAGER_JOB_SUBMIT;
				} else {
					System.out.println("Jobs are at maximum amount at the moment. Please try again later.");
				}
				break;
				
			case '2':
				consoleState = AVAILABLE_JOB_SCREEN;
				break; //Not yet implemented
			
			case '3':
				consoleState = MANAGER_SUBMITTED_JOBS;
				break; //Not yet implemented
				
			case '4':
				break;
			
			case '5':
				consoleState = USER_LOG_IN;
				System.out.println("Goodbye!");
				break;
				
			default:
				break;
		}
	}
	
	private static void displayAvailableJobs() {
		Map<Integer, Job> jobMap = UrbanParksSystem.getJobMap(); 
		int i = 1;
		System.out.println("-------------------------------------------------------------------");
		for (Integer j: jobMap.keySet()) {
			System.out.println("Job #" + i + "\nTitle: " + jobMap.get(j).myTitle + "\n" 
								+ "Date: " + jobMap.get(j).myDateString
								+ "\n" + "Requirements: " + jobMap.get(j).myRequirements + "\n" 
								+ "Location: " + jobMap.get(j).myLocation);
			System.out.println("-------------------------------------------------------------------");
			i++;
		}
		
		System.out.println("Select a job number to view information");
		String selection = scan.nextLine();
		int selectNumber = Integer.parseInt(selection);
		displayJobDetails((Job) jobMap.values().toArray()[selectNumber - 1]);
	}
	
	private static void displayJobDetails(Job j) {
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Job information: ");
		System.out.println("Title: " + j.myTitle);
		System.out.println("Date: " + j.myDateString);
		System.out.println("Requirements: " + j.myRequirements + "," + j.myNoVolunteers + " volunteers");
		System.out.println("Location: " + j.myLocation);
		System.out.println("Description: " + j.myDescription);
		System.out.println("-------------------------------------------------------------------");
		
		if (UrbanParksSystem.getCurrentUser().getPermissionLevel() == 2) {
			displayVolunteerJobOptions(j);
		} else if (UrbanParksSystem.getCurrentUser().getPermissionLevel() == 1) {
			displayManagerJobOptions(j);	
			// park manager options
				
		} else {
			displayEmployeeJobOptions(j);
		}
				// urban parks staff options
		
		
	}
	
	private static void displayVolunteerJobOptions(Job j) {
		System.out.println("\nChoose an action: \n"
				+ "1. Sign up for this job\n"
				+ "2. Unvolunteer for this job.\n"
				+ "3. Back to available jobs\n"
				+ "4. Main menu");
		String selection = scan.nextLine();
		
		switch (selection.charAt(0)) {
		case '1':
			if (UrbanParksSystem.signUpForJob(j)) {
				System.out.println("You have signed up");     
			} else {
				System.out.println("Sorry, you cannot sign up for this job");
			}
			//consoleState = VOLUNTEER_MAIN_MENU;
			break;
		case '2':
			UrbanParksSystem.removeUserJob(j);
			break;
		case '3':
			consoleState = AVAILABLE_JOB_SCREEN;
			break;
		
		case '4':
			//consoleState = VOLUNTEER_MAIN_MENU;
			break;
		
		default:
			break;
		}
	}
	
	private static void displayManagerJobOptions(Job j) {
		System.out.println("\nChoose an action: \n"
				+ "1. Delete this job\n"
				+ "2. Back to available jobs\n"
				+ "3. Main menu");
		String selection = scan.nextLine();
		
		switch (selection.charAt(0)) {
		case '1':
			UrbanParksSystem.removeJob(j);
			break;
			
		case '2':
			consoleState = PARK_MANAGER_JOB_VIEW;
			break;
		
		case '3':
			//consoleState = PARK_MANAGER_MAIN_MENU;
			break;
		
		default:
			break;
		}
	}
	
	private static void displayEmployeeJobOptions(Job j) {
		System.out.println("\nChoose an action: \n"
				
				/*
				+ "1. Sign up for this job\n"
				+ "2. Back to available jobs\n"
				*/
				+ "3. Main menu");
		String selection = scan.nextLine();
		
		switch (selection.charAt(0)) {
		case '1':
			
			//consoleState = VOLUNTEER_MAIN_MENU;
			break;
			
		case '2':
			//consoleState = VOLUNTEER_JOB_SCREEN;
			break;
		
		case '3':
			//consoleState = VOLUNTEER_MAIN_MENU;
			break;
		
		default:
			break;
		}
	}

	public static void displayVolunteerJobs() {
		Map<Integer, Job> jobMap = UrbanParksSystem.getJobMap();
		System.out.println("Your Current Jobs");
		System.out.println("-------------------------------------------------------------------");
		int i = 1;
		for (Integer id : UrbanParksSystem.getCurrentUser().getJobs()) {
				System.out.println(i + ". " + jobMap.get(id).myTitle + "\n" 
									+ jobMap.get(id).myDateString
									+ "\n" + jobMap.get(id).myRequirements 
									+ "\n" + jobMap.get(id).myLocation
									+ "\n\n");
			i++;
		}
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Select a job number to view information");
		String selection = scan.nextLine();
		int selectNumber = Integer.parseInt(selection);
		displayJobDetails((Job) jobMap.values().toArray()[selectNumber - 1]);
	}
	
	public static void displaySubmittedManagerJobs() {
		Map<Integer, Job> jobMap = UrbanParksSystem.getJobMap();
		System.out.println("Your Submitted Jobs");
		System.out.println("-------------------------------------------------------------------");
		int i = 1;
		for (Integer id : UrbanParksSystem.getCurrentUser().getJobs()) {
				System.out.println(i + ". " + jobMap.get(id).myTitle + "\n" 
									+ jobMap.get(id).myDateString
									+ "\n" + jobMap.get(id).myRequirements 
									+ "\n" + jobMap.get(id).myLocation
									+ "\n\n");
			i++;
		}
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Select a job number to view information");
		String selection = scan.nextLine();
		int selectNumber = Integer.parseInt(selection);
		displayJobDetails((Job) jobMap.values().toArray()[selectNumber - 1]);
	}
	
	private static void displaySubmitJobScreen() {
		System.out.println("Job Form");
		System.out.println("Enter job title:");
		String title = scan.nextLine();
		
		System.out.println("Enter job day (dd): ");  
		String day = scan.nextLine();
		
		System.out.println("Enter job month (mm): ");   
		String month = scan.nextLine();
		
		System.out.println("Enter job year (yyyy): ");   
		String year = scan.nextLine();
		
		System.out.println("Enter job length in days: ");
		int lengthOfJob = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Enter the job requirements: ");
		String req = scan.nextLine();
		
		System.out.println("Number of volunteers required: ");
		int numVolunteers = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Enter the job location: ");
		String location = scan.nextLine();
		
		System.out.println("Enter the job description: ");
		String desc = scan.nextLine();
		
		System.out.println("Review job info:"
				+ "\nTitle: " + title
				+ "\nDate: " + month + '/' + day + '/' + year
				+ "\nRequirements: " + req
				+ "\nLocation: " + location
				+ "\nDescription: " + desc);
		
		System.out.println("\nIs this correct? (1:yes, 2:no)");
		String selection = scan.nextLine();
		
		if (selection.charAt(0) == '1') {
			String date = year + "/" + month + "/" + day;
			Job jobToSubmit = new Job(title, date, req, numVolunteers, location, desc, lengthOfJob);
			int userFeedback = UrbanParksSystem.submitJob(jobToSubmit);
			
			if (userFeedback == 0) {
				System.out.println("Thank you for submitting a job at Urban Parks!");
				
			} else {
				if (userFeedback == 1) {
					System.out.println("Can't submit this job, invalid duration.");
				} else if (userFeedback == 2){
					System.out.println("Can't submit this job, not within valid time frame.");
				} else {
					System.out.println("Can't submit this job, unknown error.");
				}
			}
			//consoleState = PARK_MANAGER_MAIN_MENU;
			
		} else if (selection.charAt(0) == '2') {
			consoleState = PARK_MANAGER_JOB_SUBMIT;
		}
	}
	
}
