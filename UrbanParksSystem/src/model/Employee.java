package model;

/*
 * Employee class that extends AbstractUser
 * @author Jenna Hand, Kristi Anna Stageberg, Robert Bohlman, Jacob Reed, Aaron Hammers
 */
public class Employee extends AbstractUser {
	private static final long serialVersionUID = -2406803988183738422L;

	/**
	 * 
	 * @param theUserName
	 * @param thePermissionLevel
	 */
	public Employee(String theUserName, int thePermissionLevel) {
		super(theUserName, thePermissionLevel);
	}

	@Override
	public void removeJob(Job j) {
		// TODO Auto-generated method stub
	}

}
