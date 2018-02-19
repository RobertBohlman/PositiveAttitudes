import java.util.HashMap;
import java.util.Map;

public class SystemData {

	private final Map<Integer, Job> myJobMap = new HashMap<Integer, Job>(); //Initializing as final since these should never be overwritten.
	private final Map<Integer, AbstractUser> myUserMap = new HashMap<Integer, AbstractUser>();
	private int myUserHash;
	private final DataStore myStorage = new DataStore();
	
	private static final int MAX_NUM_JOBS = 15;

	public SystemData() {
		myStorage.LoadJobs(myJobMap);

	}

	public boolean atMaxJobs() {
		return getNumberOfJobs() >= MAX_NUM_JOBS;
	}
	
	public int submitJob(Job j) {
		int result = 3;
		
		if (j.validDuration() && j.withinTimeFrame()) {
			myJobMap.put(j.myTitle.hashCode(), j);
			getCurrentUser().addJob(j);
			saveData();
			result = 0;
			
		} else {
			if (!j.validDuration()) {
				result = 1;
				
			} else {
				result = 2;
			}
		}
		
		return result;
	}
	
	/**
	 * Signs the current user up for the job.
	 * Need to consider adding specific feedback for types of error
	 * i.e. valid start date failure vs conflict failure
	 * 
	 * @param j the job
	 * @return true if sign up was successful, false otherwise
	 */
	public boolean signUpForJob(Job j) {
		boolean result = false;
		AbstractUser user = getCurrentUser();

		if (j.validStartDate() && !j.hasDateConflicts((Volunteer) user)) {
			user.addJob(j);
			saveData();
			result = true;
		}

		return result;
	}

	public void addUser(AbstractUser user) {
		myUserMap.put(user.getUserName().hashCode(), user);
		myStorage.setUsers(myUserMap);
	}

	public boolean isUserMapNull() {
		return myStorage.isUserMapNull();
	}

	public boolean isJobListNull() {
		return myStorage.isJobListNull();
	}

	public int getNumberOfJobs() {
		return myJobMap.size();
	}

//	public void addJob(Job j) {
//		myJobMap.put(j.myTitle.hashCode(), j);
//		saveData();
//	}

	public void removeVolunteerJob(Job j) {
		if(j.isMinDaysInFuture()) {
			//remove int from array list of volunteer
			getCurrentUser().removeJob(j);
			saveData();
			System.out.println("Removed Job.\n");
		} else {
			System.out.println("Could not remove job!\n");
		}
	}

	public AbstractUser getCurrentUser() {
		return myUserMap.get(myUserHash);
	}

	public void setCurrentUser(String name) {
		myUserHash = name.hashCode();
	}

	public AbstractUser LookupUser(int userHash) {
		return myUserMap.get(userHash);
	}

	public Map<Integer, Job> getJobMap() {
		return myJobMap;
	}

	public Map<Integer, AbstractUser> getUserMap() {
		return myUserMap;
	}

	public int getUserHash() {
		return myUserHash;
	}

	public void setUserHash(int hash) {
		myUserHash = hash;
	}

	public void loadUsers() {
		myStorage.LoadUsers(myUserMap);

	}

	public void saveData() {
		myStorage.setJobs(myJobMap);
		myStorage.setUsers(myUserMap);
		myStorage.Store();
	}


}
