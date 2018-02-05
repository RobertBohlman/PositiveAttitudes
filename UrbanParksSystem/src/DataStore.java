import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

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
	public ArrayList<Job> myJobs = new ArrayList<Job>();
	
	/**
	 * Constructor.
	 * @param theUsername Username
	 * @param thePermission Permission Rank (1-3)
	 * @param theJobs List of jobs pertaining to user
	 */
	public DataStore(final String theUsername, final int thePermission,
						final ArrayList<Job> theJobs) {
		myUsername = theUsername;
		myPermission = thePermission;
		myJobs = theJobs;
	}
	
	/**
	 * Stores DataStore object
	 */
	public void Store() {
		try {
			FileOutputStream fileOut = new
					FileOutputStream("/tmp/" + myUsername + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(myUsername);
			out.writeObject(myPermission);
			out.writeObject(myJobs);
			fileOut.close();
			System.out.println("Serliazed data is saved to /tmp/" + myUsername + ".ser");
		}catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	public void Load(final String theUsername) {
		try {
			FileInputStream fileIn = new FileInputStream("/tmp/" + theUsername + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			//TODO
			
		}
	}
	
	
}
