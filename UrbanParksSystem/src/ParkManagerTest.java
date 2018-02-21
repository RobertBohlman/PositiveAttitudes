
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
	
	ParkManager parkManagerMoreThanTwoDays = new ParkManager(null, 0, null);
	ParkManager removeJobParkManager = new ParkManager(null, 0, null);
	Job jobStartLate;
	Job jobStartIn2Days;
	Job jobStartTooEarly;
	Job jobOnCurDay;
	Job jobBeforeCurDay;
	Job jobAtMinFuture;
	Job jobMoreMinFuture;
	
	int currentDayDate = 21;
	
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
		
		jobStartLate = new Job("leaves", "2018/08/02", "Req", 3, "Park 2", "Rake Leaves", 2);
		jobStartIn2Days = new Job("leaves", "2018/07/02", "Req", 3, "Park 2", "Rake Leaves", 2);
		jobStartTooEarly = new Job("leaves", "2018/05/02", "Req", 3, "Park 2", "Rake Leaves", 2);
		jobOnCurDay = new Job("Title", "2018/02/" + currentDayDate, "Req", 3, "Park", "Rake Leaves", 2);
		jobBeforeCurDay = new Job("Title", "2018/02/" + (currentDayDate - 1), "Req", 3, "Park", "Rake Leaves", 2);
		jobMoreMinFuture = new Job("Title", "2018/02/" + (currentDayDate + 5), "Req", 3, "Park", "Rake Leaves", 2);
		jobAtMinFuture = new Job("Title", "2018/02/" + (currentDayDate + 4), "Req", 3, "Park", "Rake Leaves", 2);
		
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

	@Test
	public void removeJobOnCurrentDay_JobDateOnCurrentDate_false() {
		removeJobParkManager.addJob(jobOnCurDay);
		assertFalse(jobOnCurDay.isMinDaysInFuture());
	}
	
	@Test
	public void removeJobForMultiDayJob_JobDatePriorToCurrentDate_false() {
		removeJobParkManager.addJob(jobBeforeCurDay);
		assertFalse(jobBeforeCurDay.isMinDaysInFuture());
	}
	
	@Test
	public void removeJobForJob_JobDateAfterMinDaysInFuture_true() {
		removeJobParkManager.addJob(jobMoreMinFuture);
		assertTrue(jobMoreMinFuture.isMinDaysInFuture());
	}
	
	 
	@Test
	public void removeJobForJob_JobDateAtExactMinInFuture_true() {
		removeJobParkManager.addJob(jobAtMinFuture);
		assertTrue(jobAtMinFuture.isMinDaysInFuture());
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
