import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UrbanParksSystem {
	private static Scanner scan;
	public static Map<Integer, Job> myJobMap;
	private static Map<Integer, AbstractUser> myUserMap;
	private static int userHash;
	private static DataStore myStorage;
	
	private static int consoleState = 0;
	
	private static final int MAX_NUM_JOBS = 20;
	private static final int USER_LOG_IN = 0;
	private static final int VOLUNTEER_MAIN_MENU = 1;
	private static final int AVAILABLE_JOB_SCREEN = 2;
	private static final int PARK_MANAGER_MAIN_MENU = 3;
	private static final int PARK_MANAGER_JOB_SUBMIT = 4;
	private static final int VOLUNTEER_SIGNED_UP_JOBS = 5;
	private static final int PARK_MANAGER_JOB_VIEW = 6;
	private static final int EMPLOYEE_MAIN_MENU = 7;
	private static final int END = 99;
	
	public static void main(String[] args) {
		myJobMap = new HashMap<Integer, Job>();
		scan = new Scanner(System.in);
		
		//Added this for hard-coding users until we have to implement letting 
		//them set up accounts for themselves.
		//0 = Employee :: 1 = ParkManager :: 2 = Volunteer
		myUserMap = new HashMap<Integer, AbstractUser>();
		myStorage = new DataStore();
		myStorage.LoadJobs(myJobMap);
		myStorage.LoadUsers();

		/*
		 * Checks if the serialized data is existent
		 * if not then load defaults
		 * if it is then load serialized.
		 */
		if(!myStorage.isUserMapNull()) {
			myUserMap = myStorage.getUsers();
		} else {

			String user1 = "Carol";
			int user1num = user1.hashCode();
			myUserMap.put(user1num, new Employee("Carol", 0));
				
			String user2 = "Frank";
			int user2num = user2.hashCode();
			myUserMap.put(user2num, new ParkManager("Frank", 1, "why@gmail.com"));
				
				
			String user3 = "Billy";
			int user3num = user3.hashCode();
			myUserMap.put(user3num, new Volunteer("Billy", 2, 34, "umm@gmail.com", 3));
			
			myStorage.setUsers(myUserMap);
		}
		
		if(!myStorage.isJobListNull()) {
			myJobMap = myStorage.getJobs();
		}
	
		
		/* Main Console UI Loop*/
		while (consoleState != END) {
			
			switch (consoleState) {
				case USER_LOG_IN:
					displayUserLogIn();
					break;
					
				case VOLUNTEER_MAIN_MENU:
					displayVolunteerMainMenu();
					break;
					
				case AVAILABLE_JOB_SCREEN:
					displayAvailableJobs();
					break;
					
				case PARK_MANAGER_MAIN_MENU:
					displayParkManagerMainMenu();
					break;
					
				case PARK_MANAGER_JOB_SUBMIT:
					displaySubmitJobScreen();
					break;
					
				case VOLUNTEER_SIGNED_UP_JOBS:
					displayVolunteerJobs();
					break;
				
				case PARK_MANAGER_JOB_VIEW:
					displayParkManagerJobs();
					break;
					
				case EMPLOYEE_MAIN_MENU:
					displayEmployeeMainMenu();
					
				default:
					break;
			}
		}
		System.out.println("Shutting down");
		myStorage.Store(); //Serialize userMap and jobList
		scan.close();
	}
	
	
	private static void displayUserLogIn() {
		System.out.println("Hello! Welcome to Urban Parks.\n"
				+ "Login with your Username: (Enter 9 to shut down)");
		String selection = scan.nextLine();
		
		if (selection.charAt(0) != '9') {
			userHash = selection.hashCode();
			
			int selectionPermission;
			
			try {
				selectionPermission = myUserMap.get(userHash).getPermissionLevel();
				
			} catch (NullPointerException e) {
				System.out.println("Error, no such user exists!");
				selectionPermission = 5;
			}
			
			
			switch (selectionPermission) {
				case 0:
					consoleState = END;//Not yet implemented - reserved for employees
					break;
					
				case 1:
					consoleState = PARK_MANAGER_MAIN_MENU;
					break;
				
				case 2:
					consoleState = VOLUNTEER_MAIN_MENU;
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
	
	private static void displayAvailableJobs() {
		int i = 1;
		System.out.println("-------------------------------------------------------------------");
		for (Integer j: myJobMap.keySet()) {
			System.out.println("Job #" + i + "\nTitle: " + myJobMap.get(j).myTitle + "\n" 
								+ "Date: " + myJobMap.get(j).myDateString
								+ "\n" + "Requirements: " + myJobMap.get(j).myRequirements + "\n" 
								+ "Location: " + myJobMap.get(j).myLocation);
			System.out.println("-------------------------------------------------------------------");
			i++;
		}
		
		System.out.println("Select a job number to view information");
		String selection = scan.nextLine();
		int selectNumber = Integer.parseInt(selection);
		displayJobDetails((Job) myJobMap.values().toArray()[selectNumber - 1]);
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
		
		if (myUserMap.get(userHash).getPermissionLevel() == 2) {
			displayVolunteerJobOptions(j);
		} else if (myUserMap.get(userHash).getPermissionLevel() == 1) {
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
				+ "2. Back to available jobs\n"
				+ "3. Main menu");
		String selection = scan.nextLine();
		
		switch (selection.charAt(0)) {
		case '1':
			AbstractUser user = myUserMap.get(userHash);
			if (j.validStartDate() && j.hasDateConflicts((Volunteer) myUserMap.get(userHash))) {
				user.addJob(j.myTitle.hashCode());
				myStorage.setJobs(myJobMap);
				myStorage.Store();
				System.out.println("You have signed up"); 
			} else {
				System.out.println("Sorry, you cannot sign up for this job");
			}
			consoleState = VOLUNTEER_MAIN_MENU;
			break;
			
		case '2':
			consoleState = AVAILABLE_JOB_SCREEN;
			break;
		
		case '3':
			consoleState = VOLUNTEER_MAIN_MENU;
			break;
		
		default:
			break;
		}
	}
	
	private static void displayManagerJobOptions(Job j) {
		System.out.println("\nChoose an action: \n"
				+ "1. Back to available jobs\n"
				+ "2. Main menu");
		String selection = scan.nextLine();
		
		switch (selection.charAt(0)) {
		case '1':
			
			consoleState = AVAILABLE_JOB_SCREEN;
			break;
			
		case '2':
			consoleState = PARK_MANAGER_MAIN_MENU;
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
	
	private static void displayParkManagerMainMenu() {
		System.out.println("Welcome to Urban Parks, Park Manager!\n"
				+ "Actions: \n"
				+ "1. Submit a new job\n"
				+ "2. View jobs\n"
				+ "3. See your calendar\n"
				+ "4. Edit personal info\n"
				+ "5. Logout");
		System.out.print("Choose an action(Enter a number): ");
		String selection = scan.nextLine();
		
		switch (selection.charAt(0)) {
			case '1':
				if(notTooManyJobs()) {
					consoleState = PARK_MANAGER_JOB_SUBMIT;
				} else {
					System.out.println("Jobs are at maximum amount at the moment. Please try again later.");
				}
				break;
				
			case '2':
				consoleState = PARK_MANAGER_JOB_VIEW;
				break; //Not yet implemented
			
			case '3':
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
	
	public static void displayVolunteerJobs() {
		System.out.println("Your Current Jobs");
		System.out.println("-------------------------------------------------------------------");
		int i = 1;
		for (Integer id : myUserMap.get(userHash).getJobs()) {
				System.out.println(i + ". " + myJobMap.get(id).myTitle + "\n" 
									+ myJobMap.get(id).myDateString
									+ "\n" + myJobMap.get(id).myRequirements 
									+ "\n" + myJobMap.get(id).myLocation
									+ "\n\n");
			i++;
		}
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Select a job number to view information");
		String selection = scan.nextLine();
		int selectNumber = Integer.parseInt(selection);
		displayJobDetails((Job) myJobMap.values().toArray()[selectNumber - 1]);
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
			if (jobToSubmit.validDuration() && jobToSubmit.withinTimeFrame()) {
				myJobMap.put(jobToSubmit.myTitle.hashCode(), jobToSubmit);
				myStorage.setJobs(myJobMap);
				System.out.println("Thank you for submitting a job at Urban Parks!");
			} else {
				if (!jobToSubmit.validDuration()) {
					System.out.println("Can't submit this job, invalid duration.");
				} else {
					System.out.println("Can't submit this job, not within valid time frame.");
				}
			}
			consoleState = PARK_MANAGER_MAIN_MENU;
			
		} else if (selection.charAt(0) == '2') {
			consoleState = PARK_MANAGER_JOB_SUBMIT;
		}
	}

	static void addJob(Job theJob) {
		if (notTooManyJobs()) {
			myJobMap.put(theJob.myTitle.hashCode(), theJob);
			myStorage.setJobs(myJobMap);
			myStorage.Store();
		}
	}
	
	private static boolean notTooManyJobs() {
		return myJobMap.size() < MAX_NUM_JOBS;
	}
	
	public Job getJob(int theID) {
		return myJobMap.get(theID);
		
	}
	
	private void deleteJob() {
		//TODO allow manager to delete jobs, and also delete from user job map
	}
	
	private static void displayParkManagerJobs() {
		int i = 1;
		System.out.println("-------------------------------------------------------------------");
		for (Integer j: myJobMap.keySet()) {
			System.out.println("Job #" + i + "\nTitle: " + myJobMap.get(j).myTitle + "\n" 
								+ "Date: " + myJobMap.get(j).myDateString
								+ "\n" + "Requirements: " + myJobMap.get(j).myRequirements + "\n" 
								+ "Location: " + myJobMap.get(j).myLocation);
			System.out.println("-------------------------------------------------------------------");
			i++;
		}
		
		System.out.println("Select a job number to view information");
		String selection = scan.nextLine();
		int selectNumber = Integer.parseInt(selection);
		displayParkManagerJobDetails((Job) myJobMap.values().toArray()[selectNumber - 1]);
	}
	
	private static void displayParkManagerJobDetails(Job j) {
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Job information: ");
		System.out.println("Title: " + j.myTitle);
		System.out.println("Date: " + j.myDateString);
		System.out.println("Requirements: " + j.myRequirements + "," + j.myNoVolunteers + " volunteers");
		System.out.println("Location: " + j.myLocation);
		System.out.println("Description: " + j.myDescription);
		System.out.println("-------------------------------------------------------------------");
		
		System.out.println("\nChoose an action: \n"
				+ "1. Delete this job\n"
				+ "2. Back to available jobs\n"
				+ "3. Main menu");
		String selection = scan.nextLine();
		
		switch (selection.charAt(0)) {
		case '1':
			//TODO DELETE JOB
			break;
			
		case '2':
			consoleState = PARK_MANAGER_JOB_VIEW;
			break;
		
		case '3':
			consoleState = PARK_MANAGER_MAIN_MENU;
			break;
		
		default:
			break;
		}
	}
}
