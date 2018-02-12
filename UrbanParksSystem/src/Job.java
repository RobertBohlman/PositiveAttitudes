import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Stores Jobs and info pertaining to them
 * @author Jacob
 *
 */
public class Job implements Serializable {
	private static final long serialVersionUID = -2453550104876763925L;
	static int MAX_DATE = 75;
	static int MAX_DURATION = 3;
	static long ONE_DAY_IN_MILI = 86400000;
	static int MIN_DAYS_TO_JOB = 2;

	public int myID;
	public String myTitle;
	public String myDateString;
	public String myRequirements;
	public Integer myNoVolunteers;
	public String myLocation;
	public String myDescription;
	public Date myDateStart;
	public Date myDateEnd;
	public Integer myJobLength;

	
	public Job(final String theTitle, String theDate, final String theRequirements,
					final Integer theNoVolunteers, final String theLocation, final String theDescription, final Integer theJobLength) {
		myTitle = theTitle;
		myDateString= theDate;
		myRequirements = theRequirements;
		myNoVolunteers = theNoVolunteers;
		myLocation = theLocation;
		myDescription = theDescription;
		myJobLength = theJobLength;
		long startDateMilli = 0;
	    long endDateMilli = 0;
		myDateStart = new Date(startDateMilli);
		myDateEnd = new Date(endDateMilli);
		//myDate = myYear, myMonth, myDay
	}
	
	/*
	 * Blank Overloaded Constructor
	 */
	public Job() {
		myTitle = null;
		myDateString = "0000.00.00";
		myRequirements = null;
		myNoVolunteers = 0;
		myLocation = null;
		myDescription = null;
		
	}
	public boolean checkJobStartDay() {
		//if number of days from now until job are >= 2 return true else false
		return false;
	}
}



