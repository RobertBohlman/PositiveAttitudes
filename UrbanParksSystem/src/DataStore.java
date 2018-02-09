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
	public AbstractUser myUser;
	
	
	/**
	 * Constructor.
	 * @param theUsername Username
	 * @param thePermission Permission Rank (1-3)
	 * @param theJobs List of jobs pertaining to user
	 */
	public DataStore(final AbstractUser theUser) {
		myUser = theUser;
	}
	
	/**
	 * Stores DataStore object
	 */
	public void Store() {
		try {
			FileOutputStream fileOut = new
					FileOutputStream("/tmp/" + myUser.getUserName() + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(myUser);
			fileOut.close();
			System.out.println("Serliazed data is saved to /tmp/" + myUser.getUserName() + ".ser");
		}catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	/**
	 * Loads data object
	 * @param theUsername
	 */
	public void Load(final String theUsername) {
		try {
			FileInputStream fileIn = new FileInputStream("/tmp/" + theUsername + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			myUser = (AbstractUser) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("Object has been deserialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
