package model;
/**
 * Volunteer class that extends Abstract User
 * @author Jenna Hand, Kristi Anna Stageberg, Robert Bohlman, Jacob Reed, Aaron Hammers
 */
public class Volunteer extends AbstractUser {
	private static final long serialVersionUID = -6373369023222173100L;
	private String myName;
	private int myAge;
	private String myEmail;
	private int myPhysicalLevel;
	
	
	/**
	 * Constructor
	 * @param theUserName String
	 * @param thePermissionLevel int
	 * @param theAge int
	 * @param theEmail String
	 * @param thePhysicalLevel int
	 */
	public Volunteer(String theUserName, int thePermissionLevel, 
				int theAge, String theEmail, int thePhysicalLevel) {
		super(theUserName, thePermissionLevel);
		myAge = theAge;
		myEmail = theEmail;
		myPhysicalLevel = thePhysicalLevel;
	}
	
	/**
	 * Getter method for volunteer name.
	 * @return String
	 */
	public String getVolunteerName() {
		return myName;
	}
	
	/**
	 * Getter method for volunteer age
	 * @return int
	 */
	public int getVolunteerAge() {
		return myAge;
	}

	/**
	 * Getter method for volunteer email.
	 * @return String
	 */
	public String getVolunteerEmail() {
		return myEmail;
	}
	
	/**
	 * Getter method for volunteer physical level.
	 * @return int
	 */
	public int getVolunteerPhysicalLevel() {
		return myPhysicalLevel;
	}
	
	/**
	 * Setter method for volunteer name
	 * @param theName String
	 */
	public void setVolunteerName(String theName) {
		myName = theName;
	}
	
	/**
	 * Setter method for volunteer age
	 * @param theAge int
	 */
	public void setVolunteerAge(int theAge) {
		myAge = theAge;
	}
	
	/**
	 * Setter method for volunteer email
	 * @param theEmail String
	 */
	public void setVolunteerEmail(String theEmail) {
		myEmail = theEmail;
	}
	
	/**
	 * Setter method for volunteer physical level.
	 * @param thePhysicalLevel
	 */
	public void setVolunteerPhysicalLevel(int thePhysicalLevel) {
		myPhysicalLevel = thePhysicalLevel;
	}
	
}
