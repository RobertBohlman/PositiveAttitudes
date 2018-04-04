package tests;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.Job;
import model.Volunteer;

public class VolunteerTest {
	
	Volunteer VolunteerMoreThanTwoDays = new Volunteer(null, 0, 0, null, 0);
	Volunteer UnvolunteerVolunteer = new Volunteer(null, 0, 0, null, 0);
	Job jobStartLate;
	Job jobStartIn2Days;
	Job jobStartTooEarly;
	Job jobOnCurDay;
	Job jobBeforeCurDay;
	Job jobAtMinFuture;
	Job jobMoreMinFuture;
	
	@Before
	public void setUp() {
		
		jobStartLate = new Job("leaves", "2018/08/02", "Req", 3, "Park 2", "Rake Leaves", 2);
		jobStartIn2Days = new Job("leaves", "2018/07/02", "Req", 3, "Park 2", "Rake Leaves", 2);
		jobStartTooEarly = new Job("leaves", "2018/05/02", "Req", 3, "Park 2", "Rake Leaves", 2);
		jobOnCurDay = new Job("Title", "2018/02/20", "Req", 3, "Park", "Rake Leaves", 2);
		jobBeforeCurDay = new Job("Title", "2018/02/18", "Req", 3, "Park", "Rake Leaves", 2);
		jobMoreMinFuture = new Job("Title", "2018/02/25", "Req", 3, "Park", "Rake Leaves", 2);
		jobAtMinFuture = new Job("Title", "2018/02/23", "Req", 3, "Park", "Rake Leaves", 2);
	}

	@Test
	public void unvolunteerOnCurrentDay_JobDateOnCurrentDate_false() {
		UnvolunteerVolunteer.addJob(jobOnCurDay);
		assertFalse(jobOnCurDay.isMinDaysInFuture());
	}
	
	@Test
	public void unvolunteerForMultiDayJob_JobDatePriorToCurrentDate_false() {
		UnvolunteerVolunteer.addJob(jobBeforeCurDay);
		assertFalse(jobBeforeCurDay.isMinDaysInFuture());
	}
	
	@Test
	public void unvolunteerForJob_JobDateAfterMinDaysInFuture_true() {
		UnvolunteerVolunteer.addJob(jobMoreMinFuture);
		assertTrue(jobMoreMinFuture.isMinDaysInFuture());
	}
	
	@Test
	public void unvolunteerForJob_JobDateAtExactMinInFuture_true() {
		UnvolunteerVolunteer.addJob(jobAtMinFuture);
		assertTrue(jobAtMinFuture.isMinDaysInFuture());
	}
}
