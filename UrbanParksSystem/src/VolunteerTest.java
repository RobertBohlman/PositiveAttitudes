import static org.junit.Assert.*;

import org.junit.Test;

public class VolunteerTest {
	
	// Business Rule 1) b.
	// Supporting classes Volunteer.java & Job.java
	
	Volunteer VolunteerMoreThanTwoDays = new Volunteer(null, 0, 0, null, 0);
	Job anyOldJob = new Job(null, 1, 1,
			1, null,
			0, null, null);

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
