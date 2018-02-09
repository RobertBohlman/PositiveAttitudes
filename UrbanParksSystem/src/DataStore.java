import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

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
	 * @param theUsers HashMap of user data
	 * @param theJobs HashMap of jobs
	 */
	public DataStore(HashMap theUsers, HashMap theJobs) {
		myUsers = theUsers;
		myJobs = theJobs;
	}
	
	/**
	 * Stores DataStore object
	 */
	public void Store() {
		try {
			//Users
			FileOutputStream fileOutUser = new
					FileOutputStream("/tmp/users.ser");
			ObjectOutputStream outUser = new ObjectOutputStream(fileOutUser);
			outUser.writeObject(myUsers);
			fileOutUser.close();
			System.out.println("Serliazed user map is saved to /tmp/users.ser");
			//Jobs
			FileOutputStream fileOutJob = new
					FileOutputStream("/tmp/jobs.ser");
			ObjectOutputStream outJob = new ObjectOutputStream(fileOutJob);
			outJob.writeObject(myUsers);
			fileOutJob.close();
			System.out.println("Serliazed user map is saved to /tmp/users.ser");
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
			FileInputStream fileInUser = new FileInputStream("/tmp/users.ser");
			ObjectInputStream inUser = new ObjectInputStream(fileInUser);
			myUsers = (HashMap) inUser.readObject();
			inUser.close();
			fileInUser.close();
			System.out.println("User map has been deserialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return myUsers;
	}
	
	public HashMap LoadJobs() {
		try {

			//Job
			FileInputStream fileInJob = new FileInputStream("/tmp/users.ser");
			ObjectInputStream inJob = new ObjectInputStream(fileInJob);
			myUsers = (HashMap) inJob.readObject();
			inJob.close();
			fileInJob.close();
			System.out.println("User map has been deserialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return myJobs;
	}
	
}
