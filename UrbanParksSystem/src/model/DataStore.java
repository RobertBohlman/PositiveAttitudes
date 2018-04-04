package model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/*
 * Class for storing persistent serialized data
 * 
 * @author Jenna Hand, Kristi Anna Stageberg, Robert Bohlman, Jacob Reed, Aaron Hammers
 */
public class DataStore implements Serializable{
	
	/**Default Generated SUID **/
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
	 * Loads user map from serialized file
	 * @param userMap User map to load into
	 */
	@SuppressWarnings("unchecked")
	public void LoadUsers(Map<Integer, AbstractUser> userMap) {
		try {
			//User map load
			FileInputStream fileInUser = new FileInputStream("./users.ser");
			ObjectInputStream inUser = new ObjectInputStream(fileInUser);
			myUsers = (HashMap<Integer, AbstractUser>) inUser.readObject();
			inUser.close();
			fileInUser.close();
			for (int i:myUsers.keySet()) {
				userMap.put(myUsers.get(i).getUserName().hashCode(), myUsers.get(i));
			}
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
	 * Getter function to get the hash map of jobs
	 * @return HashMap of jobs
	 */
	public HashMap<Integer, Job> getJobs() {
		return myJobs;
	}
	
	/**
	 * Getter method to get the hash map of users
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
		File tmpDir = new File("./users.ser");
		boolean exists = tmpDir.exists();
		return !exists;
	}
	
	/**
	 * Checks if job map is null,
	 * to be used with initial load of serialized files in system
	 * @return True if empty job map, false otherwise
	 */
	public boolean isJobListNull() {
		File tmpDir = new File("./jobs.ser");
		boolean exists = tmpDir.exists();
		return !exists;
	}
}
