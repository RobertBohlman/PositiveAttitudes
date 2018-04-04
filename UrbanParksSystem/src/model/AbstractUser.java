package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Abstract User class to be extended by any type of user for the system.
 * 
 * @author Jenna Hand, Kristi Anna Stageberg, Robert Bohlman, Jacob Reed, Aaron Hammers
 */
public abstract class AbstractUser implements Serializable{
	



	/**Default Generated SUID**/
	private static final long serialVersionUID = -8519694891914710230L;
	private String myUserName;
	private int myPermissionLevel;
	private List<Integer> myJobs;
	
	/**
	 * 
	 * @param theUserName
	 * @param thePermissionLevel
	 * @precondition A non null username string and non negative permission level integer.
	 * @postcondition Creates a user based on the given username and permission level integer.
	 */
	public AbstractUser(String theUserName, int thePermissionLevel) {
		myUserName = theUserName;
		myPermissionLevel = thePermissionLevel;
		myJobs = new ArrayList<Integer>();
	}
	
	/**
	 * Username getter method
	 * @return String of username
	 */
	public String getUserName() {
		return myUserName;
	}
	
	/**
	 * Setter method for username
	 * @param theUserName String to set username to
	 */
	public void setUserName(String theUserName) {
		myUserName = theUserName;
	}
	
	/**
	 * Getter method for permission level.
	 * @return Integer of permission level.
	 */
	public int getPermissionLevel() {
		return myPermissionLevel;
	}
	
	/**
	 * Setter method for permission level
	 * @param thePermissionLevel Integer to set permission level.
	 */
	public void setPermissionLevel(int thePermissionLevel) {
		myPermissionLevel = thePermissionLevel;
	}
	
	/**
	 * Getter method for job list of integer codes.
	 * @return List<Integer> containing job codes
	 */
	public List<Integer> getJobs() {
		return myJobs;
	}
	
	/**
	 * Converts job title to hash code and stores it in List<Integer> 
	 * @param job Job to add
	 */
	public void addJob(Job job) {
		myJobs.add(job.myTitle.hashCode());
	}
	
	/**
	 * Removes job from List<Integer>
	 * @param j Job to remove.
	 */
	public void removeJob(Job j) {
		Integer jobCodeToRemove = j.myTitle.hashCode();
		myJobs.remove(jobCodeToRemove);
	}
	
}
