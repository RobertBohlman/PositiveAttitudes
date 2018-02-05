import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParkManagerTest {

	private static final int MAX_NUM_JOBS = 20;
	
	private List<Job> fewerThanMaxJobs;
	private List<Job> maxJobs;
	private Job submittableJob;
	private ParkManager user;
	
	@BeforeEach
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

}
