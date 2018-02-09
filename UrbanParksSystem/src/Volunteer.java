import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * @author Jenna Hand, Kristi Anna Stageberg, Robert Bohlman, Jacob Reed, Aaron Hammers
 */
public class Volunteer extends AbstractUser {

	/** */
	private String myName;
	
	/** */
	private int myAge;
	
	/** */
	private String myEmail;
	
	/** */
	private int myPhysicalLevel;
	
	/** */
	Collection <Job> myJobs;
	
	
	/**
	 * 
	 * @param theUserName
	 * @param thePermissionLevel
	 * @param theAge
	 * @param theEmail
	 * @param thePhysicalLevel
	 */
	public Volunteer(String theUserName, int thePermissionLevel, 
				int theAge, String theEmail, int thePhysicalLevel) {
		super(theUserName, thePermissionLevel);
		myAge = theAge;
		myEmail = theEmail;
		myPhysicalLevel = thePhysicalLevel;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getVolunteerName() {
		return myName;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getVolunteerAge() {
		return myAge;
	}

	/**
	 * 
	 * @return
	 */
	public String getVolunteerEmail() {
		return myEmail;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getVolunteerPhysicalLevel() {
		return myPhysicalLevel;
	}
	
	/**
	 * 
	 * @param theName
	 */
	public void setVolunteerName(String theName) {
		myName = theName;
	}
	
	/**
	 * 
	 * @param theAge
	 */
	public void setVolunteerAge(int theAge) {
		myAge = theAge;
	}
	
	/**
	 * 
	 * @param theEmail
	 */
	public void setVolunteerEmail(String theEmail) {
		myEmail = theEmail;
	}
	
	/**
	 * 
	 * @param thePhysicalLevel
	 */
	public void setVolunteerPhysicalLevel(int thePhysicalLevel) {
		myPhysicalLevel = thePhysicalLevel;
	}
	
	/**
	 * Used for unitTest for 1B
	 * @param candidateJob
	 * @param daysAfter
	 * @return
	 */
	public boolean isMinDaysAfter(Job candidateJob, int daysAfter) {
		boolean isMin = false;
		boolean okDate = false;
		
		Calendar cal = Calendar.getInstance(); // starts with today's date and time
		cal.add(Calendar.DAY_OF_YEAR, daysAfter);  // advances day by 2
		Date date = cal.getTime(); // gets modified time
		
		
		

//		for(Job job : myJobs) {
//			int compDate = job.date.compareTo(date);
//			
//			if(compDate == 1) {
//				okDate = true;
//			} else {
//				okDate = false;
//			}
//			
//			isMin = okDate || isMin;
//		}
		return isMin;	
	}
}
