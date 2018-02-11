import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UrbanParksSystem {
	private static Scanner scan;
	private static Map<Integer, Job> jobList;
	private static Map<Integer, AbstractUser> userMap;
	private static int userHash;
	private static DataStore myStorage;
	
	private static int consoleState = 0;
	
	private static final int MAX_NUM_JOBS = 20;
	private static final int USER_LOG_IN = 0;
	private static final int VOLUNTEER_MAIN_MENU = 1;
	private static final int VOLUNTEER_JOB_SCREEN = 2;
	private static final int PARK_MANAGER_MAIN_MENU = 3;
	private static final int PARK_MANAGER_JOB_SUBMIT = 4;
	private static final int END = 99;
	
	public static void main(String[] args) {
		jobList = new HashMap<Integer, Job>();
		scan = new Scanner(System.in);
		
		
		
		//Added this for hard-coding users until we have to implement letting 
		//them set up accounts for themselves.
		//0 = Employee :: 1 = ParkManager :: 2 = Volunteer
		userMap = new HashMap<Integer, AbstractUser>();
		
		String user1 = "Carol";
		int user1num = user1.hashCode();
		userMap.put(user1num, new Employee("Carol", 0));
		
		String user2 = "Frank";
		int user2num = user2.hashCode();
		userMap.put(user2num, new ParkManager("Frank", 1, "why@gmail.com"));
		
		String user3 = "Billy";
		int user3num = user3.hashCode();
		userMap.put(user3num, new Volunteer("Billy", 2, 34, "umm@gmail.com", 3));
		
		
		
		
		while (consoleState != END) {
			
			switch (consoleState) {
				case USER_LOG_IN:
					displayUserLogIn();
					break;
					
				case VOLUNTEER_MAIN_MENU:
					displayVolunteerMainMenu();
					break;
					
				case VOLUNTEER_JOB_SCREEN:
					displayAvailableJobs();
					break;
					
				case PARK_MANAGER_MAIN_MENU:
					displayParkManagerMainMenu();
					break;
					
				case PARK_MANAGER_JOB_SUBMIT:
					displaySubmitJobScreen();
					break;
					
				default:
					break;
			}
		}
		
		scan.close();
	}
	
	
	private static void displayUserLogIn() {
		System.out.println("Hello! Welcome to Urban Parks.\n"
				+ "Login with your Username: ");
		String selection = scan.nextLine();
		
		int userHash = selection.hashCode();
		
		
		int selectionPermission = userMap.get(userHash).getPermissionLevel();
		
		switch (selectionPermission) {
			case 0:
				//Not yet implemented - reserved for employees
				break;
				
			case 1:
				consoleState = PARK_MANAGER_MAIN_MENU;
				break;
			
			case 2:
				consoleState = VOLUNTEER_MAIN_MENU;
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
				consoleState = VOLUNTEER_JOB_SCREEN;
				break;
				
			case '2':
				break; //Not yet implemented
			
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
		System.out.println("Title:\n Date:\n Requirements:\n Location:");
		System.out.println("-------------------------------------------------------------------");
		for (Integer j: jobList.keySet()) {
			System.out.println(i + ". " + jobList.get(j).myTitle + "\n" + jobList.get(j).myMonth + "/" + jobList.get(j).myDay + "/" + jobList.get(j).myYear 
					+ "\n" + jobList.get(j).myRequirements + "\n" + jobList.get(j).myLocation);
			System.out.println("-------------------------------------------------------------------");
			i++;
		}
		
		System.out.println("Select a job number to view information");
		String selection = scan.nextLine();
		int selectNumber = Integer.parseInt(selection);
		displayJobDetails(jobList.get(selectNumber - 1));
	}
	
	private static void displayJobDetails(Job j) {
		System.out.println("Job information: ");
		System.out.println("Title: " + j.myTitle);
		System.out.println("Date: " + j.myMonth + "/" + j.myDay + "/" + j.myYear);
		System.out.println("Requirements: " + j.myRequirements + "," + j.myNoVolunteers + " volunteers");
		System.out.println("Location: " + j.myLocation);
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Description: " + j.myDescription);
		
		System.out.println("\nChoose an action: \n"
				+ "1. Sign up for this job\n"
				+ "2. Back to available jobs\n"
				+ "3. Main menu");
		String selection = scan.nextLine();
		
		switch (selection.charAt(0)) {
		case '1':
			AbstractUser user = userMap.get(userHash);
			user.getJobs().add(j.myTitle.hashCode());
			System.out.println("You have signed up"); 
			consoleState = VOLUNTEER_MAIN_MENU;
			break;
			
		case '2':
			consoleState = VOLUNTEER_JOB_SCREEN;
			break;
		
		case '3':
			consoleState = VOLUNTEER_MAIN_MENU;
			break;
		
		default:
			break;
		}
		
	}
	
	private static void displayParkManagerMainMenu() {
		System.out.println("Welcome to Urban Parks, Park Manager!\n"
				+ "Actions: \n"
				+ "1. Submit a new job\n"
				+ "2. See your calendar\n"
				+ "3. Edit personal info\n"
				+ "4. Logout");
		System.out.print("Choose an action(Enter a number): ");
		String selection = scan.nextLine();
		
		switch (selection.charAt(0)) {
			case '1':
				consoleState = PARK_MANAGER_JOB_SUBMIT;
				break;
				
			case '2':
				break; //Not yet implemented
			
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
	
	public static void displayVolunteerJobs() {
		System.out.println("Your Current Jobs");
		System.out.println("-------------------------------------------------------------------");
		int i = 1;
		for (Integer id : userMap.get(userHash).getJobs()) {
				System.out.println(i + ". " + jobList.get(id).myTitle + "\n" + jobList.get(id).myMonth + "/" + jobList.get(id).myDay + "/" + jobList.get(id).myYear 
						+ "\n" + jobList.get(id).myRequirements + "\n" + jobList.get(id).myLocation);
			i++;
		}
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Select a job number to view information");
		String selection = scan.nextLine();
		int selectNumber = Integer.parseInt(selection);
		displayJobDetails(jobList.get(selectNumber - 1));
	}
	
	private static void displaySubmitJobScreen() {
		System.out.println("Job Form");
		System.out.println("Enter job title:");
		String title = scan.nextLine();
		
		System.out.println("Enter job day: ");  
		int day = Integer.parseInt(scan.nextLine());
		
		System.out.println("Enter job month: ");   
		int month = Integer.parseInt(scan.nextLine());
		
		System.out.println("Enter job year: ");   
		int year = Integer.parseInt(scan.nextLine());
		
		System.out.println("Enter the job requirements: ");
		String req = scan.nextLine();
		
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
			addJob(new Job(title, day, month, year, req, 0, location, desc));
			System.out.println("Thank you for submitting a job at Urban Parks!");
			consoleState = PARK_MANAGER_MAIN_MENU;
			
		} else if (selection.charAt(0) == '2') {
			consoleState = PARK_MANAGER_JOB_SUBMIT;
		}
	}

	private static void addJob(Job theJob) {
		if (notTooManyJobs()) {
			jobList.put(theJob.myTitle.hashCode(), theJob);
		}
	}
	
	private static boolean notTooManyJobs() {
		return jobList.size() < MAX_NUM_JOBS;
	}
	
	public Job getJob(int theID) {
		return jobList.get(theID);
		
	}

}
