import java.util.HashMap;
import java.util.Map;

public class SystemData {
	
	private final Map<Integer, Job> myJobMap = new HashMap<Integer, Job>(); //Initializing as final since these should never be overwritten.
	private final Map<Integer, AbstractUser> myUserMap = new HashMap<Integer, AbstractUser>();
	private int myUserHash;
	private final DataStore myStorage = new DataStore();
	
	public SystemData() {
		myStorage.LoadJobs(myJobMap);
		
	}
	
	public void addUser(AbstractUser user) {
		myUserMap.put(user.getUserName().hashCode(), user);
		myStorage.setUsers(myUserMap);
	}
	
	public boolean isUserMapNull() {
		return myStorage.isUserMapNull();
	}
	
	public boolean isJobListNull() {
		return myStorage.isJobListNull();
	}
	
	public int getNumberOfJobs() {
		return myJobMap.size();
	}
	
	public void addJob(Job j) {
		myJobMap.put(j.myTitle.hashCode(), j);
		saveData();
	}
	
	public AbstractUser getCurrentUser() {
		return myUserMap.get(myUserHash);
	}
	
	public void setCurrentUser(String name) {
		myUserHash = name.hashCode();
	}
	
	public AbstractUser LookupUser(int userHash) {
		return myUserMap.get(userHash);
	}
	
	public Map<Integer, Job> getJobMap() {
		return myJobMap;
	}
	
	public Map<Integer, AbstractUser> getUserMap() {
		return myUserMap;
	}
	
	public int getUserHash() {
		return myUserHash;
	}
	
	public void setUserHash(int hash) {
		myUserHash = hash;
	}

	public void loadUsers() {
		myStorage.LoadUsers(myUserMap);
		
	}

	public void saveData() {
		myStorage.setJobs(myJobMap);
		myStorage.setUsers(myUserMap);
		myStorage.Store();
	}
	

}
