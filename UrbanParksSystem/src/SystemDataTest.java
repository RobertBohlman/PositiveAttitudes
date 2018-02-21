import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SystemDataTest {
	private static SystemData testSystem = new SystemData();
	
	@Test
	public void setMaxJobs_SystemData_ZeroSpecified_false() {
		assertFalse(testSystem.setMaxJobs("0"));
	}
	
	@Test
	public void setMaxJobs_SystemData_NegativeIntegerSpecified_false() {
		assertFalse(testSystem.setMaxJobs("-2"));
	}
	
	@Test
	public void setMaxJobs_SystemData_NonIntegerSpecified_false() {
		assertFalse(testSystem.setMaxJobs("bad"));
	}
	
	@Test
	public void setMaxJobs_SystemData_PositiveIntegerSpecified_true() {
		assertTrue(testSystem.setMaxJobs("" + testSystem.getNumberOfJobs()));
	}
}
