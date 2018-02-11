import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 
 * @author Jenna Hand, Kristi Anna Stageberg, Robert Bohlman, Jacob Reed, Aaron Hammers
 */
public abstract class AbstractUser implements Serializable{
	



	/**
	 * 
	 */
	private static final long serialVersionUID = -8519694891914710230L;

	/** */
	private String myUserName;
	
	/** */
	private int myPermissionLevel;
	
	/** */
	private List<Integer> myJobs;
	
	/**
	 * 
	 * @param theUserName
	 * @param thePermissionLevel
	 */
	public AbstractUser(String theUserName, int thePermissionLevel) {
		myUserName = theUserName;
		myPermissionLevel = thePermissionLevel;
		myJobs = new ArrayList<Integer>();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUserName() {
		return myUserName;
	}
	
	public void setUserName(String theUserName) {
		myUserName = theUserName;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getPermissionLevel() {
		return myPermissionLevel;
	}
	
	public void setPermissionLevel(int thePermissionLevel) {
		myPermissionLevel = thePermissionLevel;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Integer> getJobs() {
		return myJobs;
	}
	
	public void addJob(Integer theJobID) {
		myJobs.add(theJobID);
	}
	
	public void removeJob(Integer theJobID) {
		myJobs.remove(theJobID);
	}
	
}
