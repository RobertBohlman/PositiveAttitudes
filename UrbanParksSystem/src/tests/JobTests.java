package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import model.Job;


public class JobTests {
	Job job1;

	@Before
	public void setUp() {
		Job job1 = new Job("Test Job", "0000/00/00", null, null, null, null, 2);
	}
	
	@Test
	public void withinTimeFrame_JobDateBeforeLimit_true() {
		job1.myDateEnd.setTime(System.currentTimeMillis());
		assertTrue(job1.withinTimeFrame());
	}
	
	@Test
	public void withinTimeFrame_JobDateOnLimit_true() {
		
		Date end = new Date(System.currentTimeMillis());
		for(int i = 0; i< Job.MAX_DATE - 2; i++) {
			end.setTime(end.getTime() + 86400000);
		}
		job1.myDateEnd.setTime(end.getTime());
		assertTrue(job1.withinTimeFrame());
	}
	
	@Test
	public void withinTimeFrame_JobDateAfterLimit_true() {
		Date end = new Date(System.currentTimeMillis());
		for(int i = 0; i< Job.MAX_DATE + 1; i++) {
			end.setTime(end.getTime() + 86400000);
		}
		job1.myDateEnd.setTime(end.getTime());
		assertFalse(job1.withinTimeFrame());
	}
	
	
	@Test
	public void validStartDate_JobDateAfterLimit_true() {
		job1.myDateStart.setTime(System.currentTimeMillis());
		for(int i = 0; i< Job.MIN_DAYS_TO_JOB + 3; i++) {
			job1.myDateStart.setTime(job1.myDateStart.getTime() + 86400000);
		}
		assertTrue(job1.validStartDate());
	}
	
	@Test
	public void validStartDate_JobDateOnLimit_true() {
		job1.myDateStart.setTime(System.currentTimeMillis());
		for(int i = 0; i< Job.MIN_DAYS_TO_JOB; i++) {
			job1.myDateStart.setTime(job1.myDateStart.getTime() + 86400000);
		}
		assertTrue(job1.validStartDate());
	}
	
	@Test
	public void validStartDate_JobDateBeforeLimit_false() {
		job1.myDateStart.setTime(System.currentTimeMillis());
		for(int i = 0; i< 1; i++) {
			job1.myDateStart.setTime(job1.myDateStart.getTime() + 86400000);
		}
		assertFalse(job1.validStartDate());
	}
	
	
	@Test
	public void validDuration_JobDurationShorterThanLimit_true() {
		job1.myJobLength = 1;
		assertTrue(job1.validDuration());
	}
	
	@Test
	public void validDuration_JobDurationEqualToLimit_true() {
		job1.myJobLength = 2;
		assertTrue(job1.validDuration());
	}
	
	@Test
	public void validDuration_JobDurationLongerThanLimit_true() {
		job1.myJobLength = 4;
		assertFalse(job1.validDuration());
	}
}
