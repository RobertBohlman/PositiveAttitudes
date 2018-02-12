import java.io.Serializable;
import java.text.ParseException;
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
		
		//Date
		String dateFormat = "yyyy/MM/dd";
		SimpleDateFormat startDate = new SimpleDateFormat(dateFormat);
		try {
			myDateStart = startDate.parse(theDate);
		} catch (ParseException e) {
			//DO Nothing
		}
		try {
			myDateEnd = startDate.parse(theDate);
		} catch (ParseException e) {
			//Do Nothing
		}
		for(int i = theJobLength; i > 0; i--) {
			myDateEnd.setTime(myDateEnd.getTime() + ONE_DAY_IN_MILI);
		}
	}
	
	/*
	 * Blank Overloaded Constructor
	 */
	public Job() {
		myTitle = null;
		myDateString = "0000/00/00";
		myRequirements = null;
		myNoVolunteers = 0;
		myLocation = null;
		myDescription = null;
		
	}
	
	public boolean validDuration() {
		return(!((myJobLength) > MAX_DURATION));
	}
	
	public boolean withinTimeFrame() {
		Boolean valid = false;
		Date max = new Date(System.currentTimeMillis());
		for(int i = 0; i< MAX_DATE; i++) {
			max.setTime(max.getTime() + ONE_DAY_IN_MILI);
		}
		if(!myDateEnd.after(max)){
			valid = true;
		}
		return valid;
	}
	
	public boolean validStartDate() {
		Date min = new Date(System.currentTimeMillis());
		for(int i = 0; i < MIN_DAYS_TO_JOB; i++) {
			min.setTime(min.getTime() + ONE_DAY_IN_MILI);
		}
		return(!myDateStart.before(min));
	}
}



