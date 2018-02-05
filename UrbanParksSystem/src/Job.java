/**
 * Stores Jobs and info pertaining to them
 * @author Jacob
 *
 */
public class Job {
	public String myTitle;
	public String myDay;
	public String myMonth;
	public String myYear;
	public String myRequirements;
	public String myLocation;
	public String myDescription;
	
	public Job(final String theTitle, final String theDay, final String theMonth,
					final String theYear, final String theRequirements,
					final String theLocation, final String theDescription) {
		myTitle = theTitle;
		myDay = theDay;
		myYear = theYear;
		myRequirements = theRequirements;
		myLocation = theLocation;
		myDescription = theDescription;
	}
}
