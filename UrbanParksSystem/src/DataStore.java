import java.io.Serializable;

/*
 * Class for storing persistent data
 * Username with permission level
 * Jobs
 */
public class DataStore implements Serializable{

	/**Generated SUID**/
	private static final long serialVersionUID = -7839681454166039293L;
	public String myUsername;
	public int myPermission;
	//public List<Job> = new List<Job>();
}
