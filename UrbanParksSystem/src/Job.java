import java.util.Date;

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
	public Date myDate;

	
	public Job(final String theTitle, final int theDay, final int theMonth,
					final int theYear, final String theRequirements,
					final Integer theNoVolunteers, final String theLocation, final String theDescription) {
		myTitle = theTitle;
		myDay = theDay;
		myMonth = theMonth;
		myYear = theYear;
		myRequirements = theRequirements;
		myNoVolunteers = theNoVolunteers;
		myLocation = theLocation;
		myDescription = theDescription;

	}
	
	/*
	 * Blank Overloaded Constructor
	 */
	public Job() {
		myTitle = null;
		myDay = 0;
		myMonth = 0;
		myYear = 0;
		myRequirements = null;
		myNoVolunteers = 0;
		myLocation = null;
		myDescription = null;
		
	}
	public boolean checkJobStartDay() {
		/*if (myDay - System.currentDay >= 2) {
			return true;
			
		}*/
		return false;
	}
}



