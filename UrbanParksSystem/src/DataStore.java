import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * Class for storing persistent data
 * Username with permission level
 * Jobs
 */
public class DataStore implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 6789836420905113195L;
	private HashMap<Integer, AbstractUser> myUsers;
	private HashMap<Integer, Job> myJobs;
	
	
	public DataStore() {
		myUsers = null;
		myJobs = null;
	}
	
	
	public void setUsers(Map<Integer, AbstractUser> userMap) {
		myUsers = (HashMap<Integer, AbstractUser>) userMap;
	}
	
	public void setJobs(Map<Integer, Job> jobList) {
		myJobs = (HashMap<Integer, Job>) jobList;
	}
	
	/**
	 * Stores DataStore object
	 */
	public void Store() {
		try {
			//Users
			FileOutputStream fileOutUser = new
					FileOutputStream("./users.ser");
			ObjectOutputStream outUser = new ObjectOutputStream(fileOutUser);
			outUser.writeObject(myUsers);
			fileOutUser.close();
			
			//Jobs
			FileOutputStream fileOutJob = new
					FileOutputStream("./jobs.ser");
			ObjectOutputStream outJob = new ObjectOutputStream(fileOutJob);
			outJob.writeObject(myJobs);
			fileOutJob.close();
		}catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	/**
	 * Loads data object
	 * @param theUsername
	 */
	public void LoadUsers() {
		try {
			//User
			FileInputStream fileInUser = new FileInputStream("./users.ser");
			ObjectInputStream inUser = new ObjectInputStream(fileInUser);
			myUsers = (HashMap<Integer, AbstractUser>) inUser.readObject();
			inUser.close();
			fileInUser.close();
		} catch (Exception e) {
			//do nothing
		}
	}
	
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
	
	public HashMap<Integer, Job> getJobs() {
		return myJobs;
	}
	
	public HashMap<Integer, AbstractUser>  getUsers() {
		return myUsers;
	}
	
	public boolean isUserMapNull() {
		if(myUsers == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isJobListNull() {
		if(myJobs == null) {
			return true;
		} else {
			return false;
		}
	}
}
