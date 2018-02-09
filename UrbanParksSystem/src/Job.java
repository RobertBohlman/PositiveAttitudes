import java.util.Date;

/**
 * 
 */

/**
 * @author aaron
 *
 */
public class Job {
	static int MAX_DATE = 75;
	static int MAX_DURATION = 3;
	static long ONE_DAY_IN_MILI = 86400000;
	static int MIN_DAYS_TO_JOB = 2;
	Date date_start;
	Date date_end;
	int jobDuration = 0;
	
	public Job(long dateInMili, int duration) {
			jobDuration = duration;
			date_start = new Date(dateInMili);
			date_end = new Date(dateInMili);
			for(int i = 0; i < duration; i++) {
				date_end.setTime(date_end.getTime() + ONE_DAY_IN_MILI);
			}
	}
	
	public boolean validDuration() {
		return(!(jobDuration > MAX_DURATION));
	}
	
	public boolean withinTimeFrame() {
		Boolean valid = false;
		Date max = new Date(System.currentTimeMillis());
		for(int i = 0; i< MAX_DATE; i++) {
			max.setTime(max.getTime() + ONE_DAY_IN_MILI);
		}
		if(!date_end.after(max)){
			valid = true;
		}
		return valid;
	}
	
	public boolean validStartDate() {
		Date min = new Date(System.currentTimeMillis());
		for(int i = 0; i < MIN_DAYS_TO_JOB; i++) {
			min = new Date(min.getTime() + ONE_DAY_IN_MILI);
		}
		return(!date_start.before(min));
	}
	
}
