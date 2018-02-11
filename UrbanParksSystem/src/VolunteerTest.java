import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VolunteerTest {
	
	// Business Rule 1) b.
	// Supporting classes Volunteer.java & Job.java
	
	Volunteer VolunteerMoreThanTwoDays = new Volunteer(null, 0, 0, null, 0);
	Job anyOldJob = new Job(null, 1, 1,
			1, null,
			0, null, null);
	
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

	@Test
	public void isMinDaysAfter_moreThanTwoDays_True() {
		assertTrue(VolunteerMoreThanTwoDays.isMinDaysAfter(anyOldJob, 3));  // i)   Much more than minimum
	}
	
	@Test
	public void isMinDaysAfter_exactlyTwoDays_True() {
		assertTrue(VolunteerMoreThanTwoDays.isMinDaysAfter(anyOldJob, 2));  // ii)  Exactly the minimum
	}
	
	@Test
	public void isMinDaysAfter_lessThanTwoDays_False() {
		assertFalse(VolunteerMoreThanTwoDays.isMinDaysAfter(anyOldJob, 1)); // iii) Less than the minimum
	}

}
