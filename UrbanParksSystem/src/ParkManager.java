/**
 * 
 */

/**
 * 
 * @author Jenna Hand, Kristi Anna Stageberg, Robert Bohlman, Jacob Reed, Aaron Hammers
 */
public class ParkManager extends AbstractUser {

	private String myEmail;
	
	/**
	 * 
	 * @param theUserName
	 * @param thePermissionLevel
	 * @param theEmail
	 */
	public ParkManager(String theUserName, int thePermissionLevel, String theEmail) {
		super(theUserName, thePermissionLevel);
		myEmail = theEmail;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getManagerEmail() {
		return myEmail;
	}
	
	/**
	 * 
	 * @param theEmail
	 */
	public void setManagerEmail(String theEmail) {
		myEmail = theEmail;
	}

}
