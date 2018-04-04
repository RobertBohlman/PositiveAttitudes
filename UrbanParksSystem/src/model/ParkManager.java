package model;
/**
 * ParkManager class that extends Abstract User
 * @author Jenna Hand, Kristi Anna Stageberg, Robert Bohlman, Jacob Reed, Aaron Hammers
 */
public class ParkManager extends AbstractUser {

	private static final long serialVersionUID = -8773862348990413031L;
	private String myEmail;
	
	/**
	 * Constructor.
	 * @param theUserName
	 * @param thePermissionLevel
	 * @param theEmail
	 */
	public ParkManager(String theUserName, int thePermissionLevel, String theEmail) {
		super(theUserName, thePermissionLevel);
		myEmail = theEmail;
	}
	
	/**
	 * Getter method for manager email.
	 * @return String 
	 */
	public String getManagerEmail() {
		return myEmail;
	}
	
	/**
	 * Setter method for manager email.
	 * @param theEmail String
	 */
	public void setManagerEmail(String theEmail) {
		myEmail = theEmail;
	}

	@Override
	public void removeJob(Job j) {
		// TODO Auto-generated method stub
	}
}
