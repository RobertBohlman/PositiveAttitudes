import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author aaron
 *
 */
public class JobTest {
	
	@Test
	public void withinTimeFrame_JobDateBeforeLimit_true() {
		Job job1 = new Job(System.currentTimeMillis(), 1);
		assertTrue(job1.withinTimeFrame());
	}
	
	@Test
	public void withinTimeFrame_JobDateOnLimit_true() {
		Date end = new Date(System.currentTimeMillis());
		for(int i = 0; i< Job.MAX_DATE - 1; i++) {
			end.setTime(end.getTime() + 86400000);
		}
		Job job1 = new Job(end.getTime(), 1);
		assertTrue(job1.withinTimeFrame());
	}
	
	@Test
	public void withinTimeFrame_JobDateAfterLimit_true() {
		Date end = new Date(System.currentTimeMillis());
		for(int i = 0; i< Job.MAX_DATE + 1; i++) {
			end.setTime(end.getTime() + 86400000);
		}
		Job job1 = new Job(end.getTime(), 1);
		assertFalse(job1.withinTimeFrame());
	}
	
	
	@Test
	public void validStartDate_JobDateAfterLimit_true() {
		Job job1 = new Job(System.currentTimeMillis(), 1);
		for(int i = 0; i< Job.MIN_DAYS_TO_JOB + 3; i++) {
			job1.date_start.setTime(job1.date_start.getTime() + 86400000);
		}
		assertTrue(job1.validStartDate());
	}
	
	@Test
	public void validStartDate_JobDateOnLimit_true() {
		Job job1 = new Job(System.currentTimeMillis(), 1);
		for(int i = 0; i< Job.MIN_DAYS_TO_JOB; i++) {
			job1.date_start.setTime(job1.date_start.getTime() + 86400000);
		}
		assertTrue(job1.validStartDate());
	}
	
	@Test
	public void validStartDate_JobDateBeforeLimit_false() {
		Job job1 = new Job(System.currentTimeMillis(), 1);
		for(int i = 0; i< 1; i++) {
			job1.date_start.setTime(job1.date_start.getTime() + 86400000);
		}
		assertFalse(job1.validStartDate());
	}
	
	
	@Test
	public void validDuration_JobDurationShorterThanLimit_true() {
		Job job1 = new Job(System.currentTimeMillis(), 1);
		assertTrue(job1.validDuration());
	}
	
	@Test
	public void validDuration_JobDurationEqualToLimit_true() {
		Job job1 = new Job(System.currentTimeMillis(), 3);
		assertTrue(job1.validDuration());
	}
	
	@Test
	public void validDuration_JobDurationLongerThanLimit_true() {
		Job job1 = new Job(System.currentTimeMillis(), 4);
		assertFalse(job1.validDuration());
	}
}
