import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 
 * @author Jenna Hand, Kristi Anna Stageberg, Robert Bohlman, Jacob Reed, Aaron Hammers
 */
public abstract class AbstractUser {
	
	/** */
	private String myUserName;
	
	/** */
	private int myPermissionLevel;
	
	/** */
	private List<Job> myJobs;
	
	/**
	 * 
	 * @param theUserName
	 * @param thePermissionLevel
	 */
	public AbstractUser(String theUserName, int thePermissionLevel) {
		myUserName = theUserName;
		myPermissionLevel = thePermissionLevel;
		myJobs = new ArrayList<Job>();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUserName() {
		return myUserName;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getPermissionLevel() {
		return myPermissionLevel;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Job> getJobs() {
		return myJobs;
	}
}
