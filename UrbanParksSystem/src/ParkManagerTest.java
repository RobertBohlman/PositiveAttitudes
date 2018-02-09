

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

class ParkManagerTest {

	private static final int MAX_NUM_JOBS = 20;
	
	private List<Job> fewerThanMaxJobs;
	private List<Job> maxJobs;
	private Job submittableJob;
	private ParkManager user;
	
	@Before
	void setUp() throws Exception {
		fewerThanMaxJobs = new ArrayList<Job>();
		maxJobs = new ArrayList<Job>();
		submittableJob = new Job();
		user = new ParkManager("manager1", 1, "manager@parks.org");
		
	}
	
	@Test
	void submitJob_tooManyJobs_returnFalse() { 
		for (int i = 0; i < 30; i++) {
			maxJobs.add(new Job());
		}
		user.submitJob(submittableJob, maxJobs, MAX_NUM_JOBS);
		assertFalse(user.getJobs().contains(submittableJob));
	}
	
	@Test
	void submitJob_oneLessThanMax_returnTrue() { 
		for (int i = 0; i < 29; i++) {
			maxJobs.add(new Job());
		}
		user.submitJob(submittableJob, maxJobs, MAX_NUM_JOBS);
		assertTrue(user.getJobs().contains(submittableJob));
	}
	
	@Test
	void submitJob_fewerThanMax_returnTrue() { 
		for (int i = 0; i < 29; i++) {
			fewerThanMaxJobs.add(new Job());
		}
		user.submitJob(submittableJob, maxJobs, MAX_NUM_JOBS);
		assertTrue(user.getJobs().contains(submittableJob));
	}

	@Test
	void testParkManager() {
		fail("Not yet implemented");
	}

	@Test
	void testGetManagerEmail() {
		fail("Not yet implemented");
	}

	@Test
	void testSetManagerEmail() {
		fail("Not yet implemented");
	}

	@Test
	/*
	 * Tests return of true if job is less than maximum
	 * of 3 days, testing using one less (2)
	 */
	void submitJob_durationLessThanMax_returnTrue() {
		//assertEquals(//TODO check job length, true);
	}
	
	@Test
	/*
	 * Tests return of true if job number of days
	 * is equal to the exact max
	 */
	void submitJob_durationEqualsMax_returnTrue() {
		//assertEquals(//TODO checkJob Length, true);
	}
	
	@Test
	/*
	 * Tests return of false if job number of days is > max
	 * Using one greater than max (4)
	 */
	void submitJob_durationGreaterThanMax_returnFalse() {
		//assertEquals(//TODO check job length, false);
	}
}
