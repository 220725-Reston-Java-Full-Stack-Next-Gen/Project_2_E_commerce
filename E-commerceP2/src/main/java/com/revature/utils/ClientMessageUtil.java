package com.revature.utils;

<<<<<<< HEAD
public class ClientMessageUtil {
=======
import com.revature.models.utilitymodels.ClientMessage;

public class ClientMessageUtil {
	public static final ClientMessage REGISTATION_SUCCESSFUL = new ClientMessage("Success! Your user account was successfully created!");
	public static final ClientMessage REGISTATION_FAILED = new ClientMessage("Something went wrong during registration.");
	public static final ClientMessage LOGIN_SUCCESSFUL = new ClientMessage("Login successful for given username/password combination.");
	public static final ClientMessage LOGIN_FAILED = new ClientMessage("Sorry, invalid username and/or password.");
	public static final ClientMessage LOGOUT_SUCCESSFUL = new ClientMessage("Success! User logout successful!");
	public static final ClientMessage LOGOUT_FAILED = new ClientMessage("Something went wrong while attempting to logout.");
	public static final ClientMessage UPDATE_SUCCESSFUL = new ClientMessage("Success! User update was successful");
	public static final ClientMessage UPDATE_FAILED = new ClientMessage("Something went wrong while attempting to update the user.");
	public static final ClientMessage DELETION_SUCCESSFUL = new ClientMessage("Success! User was deleted successfully.");
	public static final ClientMessage DELETION_FAILED = new ClientMessage("Something went wrong while attempting to delete the user.");
>>>>>>> main
}
