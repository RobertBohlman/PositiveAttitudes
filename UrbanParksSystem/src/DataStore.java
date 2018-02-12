import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/*
 * Class for storing persistent data
 * Username with permission level
 * Jobs
 */
public class DataStore implements Serializable{
	
	private static final long serialVersionUID = 6789836420905113195L;
	private HashMap<Integer, AbstractUser> myUsers;
	private HashMap<Integer, Job> myJobs;
	
	/**
	 * Constructor. Sets user map and jobs to null to begin with
	 * This is used to check later if there are serialized files or not first.
	 */
	public DataStore() {
		myUsers = null;
		myJobs = null;
	}
	
	/**
	 * Sets the data to be stored for users.
	 * @param userMap HashMap<Integer, AbstractUser> User Map
	 */
	public void setUsers(Map<Integer, AbstractUser> userMap) {
		myUsers = (HashMap<Integer, AbstractUser>) userMap;
	}
	
	/**
	 * Sets the data to be stored for jobs.
	 * @param jobList HashMap<Integer, Job> Job Map
	 */
	public void setJobs(Map<Integer, Job> jobList) {
		myJobs = (HashMap<Integer, Job>) jobList;
	}
	
	/**
	 * Takes the myUsers and myJobs maps
	 * and serializes them into two files
	 * jobs.ser
	 * and users.ser
	 */
	public void Store() {
		try {
			//User Storage
			FileOutputStream fileOutUser = new
					FileOutputStream("./users.ser");
			ObjectOutputStream outUser = new ObjectOutputStream(fileOutUser);
			outUser.writeObject(myUsers);
			fileOutUser.close();
			
			//Job Storage
			FileOutputStream fileOutJob = new
					FileOutputStream("./jobs.ser");
			ObjectOutputStream outJob = new ObjectOutputStream(fileOutJob);
			outJob.writeObject(myJobs);
			fileOutJob.close();
		}catch (IOException i) {
			//Do Nothing
		}
	}
	
	/**
	 * Loads userMap from serialized file
	 */
	@SuppressWarnings("unchecked")
	public void LoadUsers() {
		try {
			//User map load
			FileInputStream fileInUser = new FileInputStream("./users.ser");
			ObjectInputStream inUser = new ObjectInputStream(fileInUser);
			myUsers = (HashMap<Integer, AbstractUser>) inUser.readObject();
			inUser.close();
			fileInUser.close();
		} catch (Exception e) {
			//Do Nothing
		}
	}
	
	/**
	 * Loads job map from serialized file.
	 * @param jobList map to put the jobs into
	 */
	@SuppressWarnings("unchecked")
	public void LoadJobs(Map<Integer, Job> jobList) {
		try {

			//Job
			FileInputStream fileInJob = new FileInputStream("./jobs.ser");
			ObjectInputStream inJob = new ObjectInputStream(fileInJob);
			myJobs = (HashMap<Integer, Job>) inJob.readObject();
			inJob.close();
			fileInJob.close();
			for (int i:myJobs.keySet()) {
				jobList.put(i, myJobs.get(i));
			}
		} catch (Exception e) {
			//do nothing
		}
	}
	
	/**
	 * Getter function to get the hashmap of jobs
	 * @return HashMap of jobs
	 */
	public HashMap<Integer, Job> getJobs() {
		return myJobs;
	}
	
	/**
	 * Getter method to get the hashmap of users
	 * @return
	 */
	public HashMap<Integer, AbstractUser>  getUsers() {
		return myUsers;
	}
	
	/**
	 * Checks if user map is null,
	 * to be used with initial load of serialized files in system
	 * @return True if empty user map, false otherwise
	 */
	public boolean isUserMapNull() {
		if(myUsers == null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if job map is null,
	 * to be used with initial load of serialized files in system
	 * @return True if empty job map, false otherwise
	 */
	public boolean isJobListNull() {
		if(myJobs == null) {
			return true;
		} else {
			return false;
		}
	}
}
