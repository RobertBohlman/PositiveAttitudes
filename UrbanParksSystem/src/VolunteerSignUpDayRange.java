package urbanparks;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VolunteerSignUpDayRange {
	Job jobStartLate;
	Job jobStartIn2Days;
	Job jobStartTooEarly;
	
	@Before
	public void setUp() {
		jobStartLate = new Job("leaves", 8, 2, 2018, "Req", 3, "Park 2", "Rake Leaves");
		jobStartIn2Days = new Job("leaves", 7, 2, 2018, "Req", 3, "Park 2", "Rake Leaves");
		jobStartTooEarly = new Job("leaves", 5, 2, 2018, "Req", 3, "Park 2", "Rake Leaves");
	}
	

	@Test
	public void signUpStartsAfter2DaysTrue() {
		assertTrue(jobStartLate.checkJobStartDay());
	}

	@Test
	public void signUpStartsOn2DaysTrue() {
		assertTrue(jobStartIn2Days.checkJobStartDay());
	}
	
	@Test
	public void signUpStartsBefore2DaysFalse() {
		assertFalse(jobStartTooEarly.checkJobStartDay());
	}
}
