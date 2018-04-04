package model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SystemData {

	private final Map<Integer, Job> myJobMap = new HashMap<Integer, Job>(); //Initializing as final since these should never be overwritten.
	private final Map<Integer, AbstractUser> myUserMap = new HashMap<Integer, AbstractUser>();
	private int myUserHash;
	private final DataStore myStorage = new DataStore();
	
	private int MAX_NUM_JOBS = 15;

	public SystemData() {
		myStorage.LoadJobs(myJobMap);

	}
	
	/**
	 * Retrieves the personal job list for the current user
	 * @return jobs The user's list of jobs
	 */
	public String[] seeMyJobs() {
		AbstractUser user = getCurrentUser();
		String[] jobs = new String[user.getJobs().size()];
		int i = 0;
		int k = 1;
		
		for (Integer j: myJobMap.keySet()) {
			for (Integer id: user.getJobs()) {
				if (id == j) {
					jobs[i] = k + ". " + myJobMap.get(j).myTitle + " (" + myJobMap.get(j).myDateString + ")";
					k++;
				}
			}
			i++;
		}
		
		return jobs;
		
	}
	public String[] seeJobs() {
		AbstractUser user = getCurrentUser();
		String[] jobs = new String[myJobMap.size()];
		int i = 0;
		int k = 1;
		for (Integer j: myJobMap.keySet()) {
			if (user instanceof Volunteer) {
				if (myJobMap.get(j).validStartDate() && !myJobMap.get(j).hasDateConflicts((Volunteer) user)) {
					jobs[i] = k + ". " + myJobMap.get(j).myTitle + " (" + myJobMap.get(j).myDateString + ")";
					k++;
				}
			} else {
				jobs[i] = k + ". " + myJobMap.get(j).myTitle + " (" + myJobMap.get(j).myDateString + ")";
				k++;
			}
			i++;
		}
		return jobs;
	}
	
	public String[] seeJobsByDate(Object startDate, Object endDate) {

		
		AbstractUser user = getCurrentUser();
		String[] jobs = new String[myJobMap.size()];
		int i = 0;
		int k = 1;
		for (Integer j: myJobMap.keySet()) {
			if (user instanceof Employee) {
				if (myJobMap.get(j).myDateStart.after((Date)startDate) && myJobMap.get(j).myDateEnd.before((Date)endDate)) {
					jobs[i] = k + ". " + myJobMap.get(j).myTitle + " (" + myJobMap.get(j).myDateString + ")";
					k++;
				}
			}
			i++;
		}
		return jobs;
	}
	
	public int getMaxJobs() {
		return MAX_NUM_JOBS;
	}
	
	public boolean setMaxJobs(String input) {
		int val = MAX_NUM_JOBS;
		try {
			val = Integer.parseInt(input);
		} catch(NumberFormatException e){
			return false;
		}
		
		if(val > 0){
			MAX_NUM_JOBS = val;
			return true;
		}
		return false;
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

	public boolean removeUserJob(Job j) {
		boolean result = false;
		if(j.isMinDaysInFuture()) {
			//remove int from array list of volunteer
			getCurrentUser().removeJob(j);
			saveData();
	        result = true;
			System.out.println("Removed Job.\n");
		} else {
			System.out.println("Could not remove job!\n");
		}
		return result;
	}
	
	public boolean removeJob(Job j) {
		boolean result = false;
		
		if(j.isMinDaysInFuture()) {
			Integer jobToRemove = j.myTitle.hashCode();
			myJobMap.remove(jobToRemove);
			
			//remove the job from each user
			for(Integer user : myUserMap.keySet()) {
				removeUserJobByUser(j, user);
			}

			saveData();
			result = true;
			
		} 
		return result;
	}
	
	// TODO I would like this method to replace the one called
	// removeUserJob();
	public boolean removeUserJobByUser(Job j, int user) {
		boolean result = false;
		if(j.isMinDaysInFuture()) {
			LookupUser(user).removeJob(j);
			saveData();
	        result = true;
		} else {
			System.out.println("Could not remove job!\n");
		}
		return result;
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

	/**
	 * Saves to serializeable files.
	 */
	public void saveData() {
		myStorage.setJobs(myJobMap);
		myStorage.setUsers(myUserMap);
		myStorage.Store();
	}


}
