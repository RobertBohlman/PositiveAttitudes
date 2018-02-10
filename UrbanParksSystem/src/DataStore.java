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

	/**Generated SUID**/
	private static final long serialVersionUID = -7839681454166039293L;
	private HashMap myUsers;
	private HashMap myJobs;
	
	
	/**
	 * Constructor.
	 * @param userMap HashMap of user data
	 * @param jobList HashMap of jobs
	 */
	public DataStore(Map<Integer, AbstractUser> userMap, Map<Integer, Job> jobList) {
		myUsers = (HashMap) userMap;
		myJobs = (HashMap) jobList;
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
			outJob.writeObject(myUsers);
			fileOutJob.close();
		}catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	/**
	 * Loads data object
	 * @param theUsername
	 */
	public HashMap LoadUsers() {
		try {
			//User
			FileInputStream fileInUser = new FileInputStream("./users.ser");
			ObjectInputStream inUser = new ObjectInputStream(fileInUser);
			myUsers = (HashMap) inUser.readObject();
			inUser.close();
			fileInUser.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return myUsers;
	}
	
	public HashMap LoadJobs() {
		try {

			//Job
			FileInputStream fileInJob = new FileInputStream("./users.ser");
			ObjectInputStream inJob = new ObjectInputStream(fileInJob);
			myUsers = (HashMap) inJob.readObject();
			inJob.close();
			fileInJob.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return myJobs;
	}
	
	public HashMap getJobs() {
		return myJobs;
	}
	
	public HashMap getUsers() {
		return myUsers;
	}
}
