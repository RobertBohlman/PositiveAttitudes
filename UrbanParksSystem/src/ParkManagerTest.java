
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ParkManagerTest {

	private static final int MAX_NUM_JOBS = 20;
	
	private List<Job> fewerThanMaxJobs;
	private List<Job> maxJobs;
	private Job submittableValidJob;
	private Job submittableInvalidJob;
	private ParkManager managerUser;
	private SystemData UrbanParksSystem;
	
	@Before
	public void setUp() throws Exception {
		UrbanParksSystem = new SystemData();
		fewerThanMaxJobs = new ArrayList<Job>();
		maxJobs = new ArrayList<Job>();
		submittableValidJob = new Job("Test Job", "2018/02/26", "", 2, "", "", 1);
		submittableInvalidJob = new Job("Test Job", "2018/10/26", "", 2, "", "", 1);
		managerUser = new ParkManager("manager1", 1, "manager@parks.org");
		UrbanParksSystem.addUser(managerUser);
		UrbanParksSystem.setCurrentUser(managerUser.getUserName());
		
	}
	
	@Test
	public void submitValidJob_ManagerRemembersJob_returnTrue() {
		UrbanParksSystem.submitJob(submittableValidJob);
		assertTrue(managerUser.getJobs().contains(submittableValidJob.myTitle.hashCode()));
	}
	
	@Test
	public void submitInvalidJob_ManagerRemembersJob_returnFalse() {
		UrbanParksSystem.submitJob(submittableInvalidJob);
		assertFalse(managerUser.getJobs().contains(submittableInvalidJob.myTitle.hashCode()));
	}
	
//	@Test
//	void submitJob_tooManyJobs_returnFalse() { 
//		for (int i = 0; i < 30; i++) {
//			maxJobs.add(new Job());
//		}
//		UrbanParksTerminal.addJob(submittableJob);
//		assertFalse(managerUser.getJobs().contains(submittableJob));
//	}
//	
//	@Test
//	void submitJob_oneLessThanMax_returnTrue() { 
//		for (int i = 0; i < 29; i++) {
//			maxJobs.add(new Job());
//		}
//		UrbanParksTerminal.addJob(submittableJob);
//		assertTrue(managerUser.getJobs().contains(submittableJob));
//	}
//	
//	@Test
//	void submitJob_fewerThanMax_returnTrue() { 
//		for (int i = 0; i < 20; i++) {
//			fewerThanMaxJobs.add(new Job());
//		}
//		UrbanParksTerminal.addJob(submittableJob);
//		assertTrue(managerUser.getJobs().contains(submittableJob));
//	}

	@Test
	/*
	 * Tests return of true if job is less than maximum
	 * of 3 days, testing using one less (2)
	 */
	public void submitJob_durationLessThanMax_returnTrue() {
		//assertEquals(//TODO check job length, true);
	}
	
	@Test
	/*
	 * Tests return of true if job number of days
	 * is equal to the exact max
	 */
	public void submitJob_durationEqualsMax_returnTrue() {
		//assertEquals(//TODO checkJob Length, true);
	}
	
	@Test
	/*
	 * Tests return of false if job number of days is > max
	 * Using one greater than max (4)
	 */
	public void submitJob_durationGreaterThanMax_returnFalse() {
		//assertEquals(//TODO check job length, false);
	}
}
