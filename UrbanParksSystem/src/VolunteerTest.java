import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VolunteerTest {
	
	// Business Rule 1) b.
	// Supporting classes Volunteer.java & Job.java
	
	Volunteer VolunteerMoreThanTwoDays = new Volunteer(null, 0, 0, null, 0);

	
	Job jobStartLate;
	Job jobStartIn2Days;
	Job jobStartTooEarly;
	
	@Before
	public void setUp() {
		jobStartLate = new Job("leaves", "2018/08/02", "Req", 3, "Park 2", "Rake Leaves", 2);
		jobStartIn2Days = new Job("leaves", "2018/07/02", "Req", 3, "Park 2", "Rake Leaves", 2);
		jobStartTooEarly = new Job("leaves", "2018/05/02", "Req", 3, "Park 2", "Rake Leaves", 2);
	}

}
