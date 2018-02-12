import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UrbanParksSystemTests {

	private static final int MAX_NUM_JOBS = 20;
	
	private List<Job> fewerThanMaxJobs;
	private List<Job> maxJobs;
	private Job submittableJob;
	private ParkManager managerUser;
	
	@Before
	public void setUp() throws Exception {
		fewerThanMaxJobs = new ArrayList<Job>();
		maxJobs = new ArrayList<Job>();
		submittableJob = new Job();
		managerUser = new ParkManager("manager1", 1, "manager@parks.org");
		
	}
	
	@Test
	public void submitJob_tooManyJobs_returnFalse() { 
		for (int i = 0; i < 30; i++) {
			maxJobs.add(new Job());
		}
		UrbanParksSystem.addJob(submittableJob);
		assertFalse(managerUser.getJobs().contains(submittableJob));
	}
	
	@Test
	public void submitJob_oneLessThanMax_returnTrue() { 
		for (int i = 0; i < 29; i++) {
			maxJobs.add(new Job());
		}
		UrbanParksSystem.addJob(submittableJob);
		assertTrue(managerUser.getJobs().contains(submittableJob));
	}
	
	@Test
	public void submitJob_fewerThanMax_returnTrue() { 
		for (int i = 0; i < 20; i++) {
			fewerThanMaxJobs.add(new Job());
		}
		UrbanParksSystem.addJob(submittableJob);
		assertTrue(managerUser.getJobs().contains(submittableJob));
	}


}