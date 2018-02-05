

/**
 * Stores Jobs and info pertaining to them
 * @author Jacob
 *
 */
public class Job {
	public String myTitle;
	public int myDay;
	public int myMonth;
	public int myYear;
	public String myRequirements;
	public Integer myNoVolunteers;
	public String myLocation;
	public String myDescription;
	
	public Job(final String theTitle, final int theDay, final int theMonth,
					final int theYear, final String theRequirements,
					final Integer theNoVolunteers, final String theLocation, final String theDescription) {
		myTitle = theTitle;
		myDay = theDay;
		myYear = theYear;
		myRequirements = theRequirements;
		myNoVolunteers = theNoVolunteers;
		myLocation = theLocation;
		myDescription = theDescription;
	}
	
	public boolean checkJobStartDay() {
		if (myDay - System.currentDay >= 2) {
			return true;
			
		}
		return false;
	}
}