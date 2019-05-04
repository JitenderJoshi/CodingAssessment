package assessment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordValidator {

	//Test data containing Init_Password for validation
	static List<User> userlist = new ArrayList<>(Arrays.asList(
			new User("Susan Smith", "ssmith@comany1.com", "susan12%#?"), 
			new User("Alex O'Connor", "alexoconnor@univ1.edu", "itsuniv1"),
			new User("John J. Peterson", "john.p@comany2.com", "J.Pe1234!"),
			new User("Chen, Mei 陈梅", "chehmei12@123.com", "<:‐)>{;=0}")
			));

	public static void main(String [] args) {
		PasswordUtil passwordUtil = new PasswordUtil();

		//validate Init_Password for each user 
		//and If it does not meet password complexity rules, generate a new password
		for(int i = 0 ; i < userlist.size() ; i++) {
			User user = userlist.get(i);
			
			System.out.println("\nValidating Password ["+user.getPassword()+"] for User ["+ user.getUserName()+"].");
			
			boolean isPasswordValid = passwordUtil.validatePasswordString(user);
			
			if(isPasswordValid) {
				System.out.println("Init_Password for User ["+ user.getUserName()+"] is a valid password.");
			}
			else {
				System.out.println("Init_Password for User ["+ user.getUserName()+"] is not valid.");
				System.out.println("Generating new password ... ");
				String newpassword = passwordUtil.generateRandomPassword();
				
				System.out.println("Setting new password "+newpassword+"");
				user.setPassword(newpassword);
				userlist.set(i, user);
			}
		}	
	}
}
