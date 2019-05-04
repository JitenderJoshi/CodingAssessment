package assessment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Password Utility class to generate random passowrd & validate its complexity.  
 * 
 * @author JitenderJoshi
 *
 */
public class PasswordUtil {
	
	/**
	 * Method to validate password complexity. Method takes the User object 
	 * and returns true or false based on password validity
	 * 
	 * @param user
	 * @return
	 */
	public boolean validatePasswordString(User user) {
		String password = user.getPassword();
		
		//password must be at least 8 characters long
		if(validPasswordLength(password)) 
		{
			String userName = user.getUserName();
			
			//password must not contain the user's entire name, or token value
			if(validNameinPassword(userName, password)) 
			{
				String email = user.getEmail();

				//password must not contain the local part and domain part of email address
				if(validEmailinPassword(email, password)) 
				{
					//password must contain characters from 3 out of 5 defined character sets
					if(validCharacterSet(password)) 
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Method to generate random password
	 * Logic- create a string of 8-15 characters randomly selected from 5 defined character sets;
	 * 
	 * @return
	 */
	public String generateRandomPassword() {
		StringBuffer newPasswordbuffer = new StringBuffer();
		
		List<String> passwordCharList = new ArrayList<>();

		//alphanumeric characters are added twice so to add more of alphanumeric character, so the password is more readable.
		passwordCharList.add("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		passwordCharList.add("ZYXWVUTSRQPONMLKJIHGFEDCBA");
		passwordCharList.add("abcdefghijklmnopqrstuvwxyz");
		passwordCharList.add("zyxwvutsrqponmlkjihgfedcba");
		passwordCharList.add("1234567890");
		passwordCharList.add("9876543210");
		passwordCharList.add("!'?\\\"-:,;()[]{}");
		passwordCharList.add("~@#$%^&*+=|<>/\\\\");
		
		//string of 8-15 characters
		int passwordLengthtobe = new Random().nextInt(15-8+1) +8;

		//this is to allow password to start with an alphabet, just for password's readability
		int randomListIndex1 = new Random().nextInt(4);
		String strToProcess1 = passwordCharList.get(randomListIndex1);
		newPasswordbuffer.append(strToProcess1.charAt(new Random().nextInt(strToProcess1.length())));			

		for(int i = 1 ; i < passwordLengthtobe ; i++ ) {
			int randomListIndex = new Random().nextInt(passwordCharList.size());
			String strToProcess = passwordCharList.get(randomListIndex);
			
			newPasswordbuffer.append(strToProcess.charAt(new Random().nextInt(strToProcess.length())));			
		}
		
		String generatedPassword = newPasswordbuffer.toString(); 
		
		return generatedPassword;
	}
	
	
	/**
	 * validate the password length
	 * password must be at least 8 characters long
	 *  
	 * @param password
	 * @return
	 */
	private boolean validPasswordLength(String password) {
		int MIN_PASSWORD_LENGTH = 8;
		
		if (password.trim().length() < MIN_PASSWORD_LENGTH) {
			System.out.println("Password Validation Failed - password must be of at least 8 characters");
			return false;
		}
		return true;
	}

	/**
	 * validate the username part in password
	 * password must not contain the user's entire name, or token value
	 *  
	 * @param userName
	 * @param password
	 * @return
	 */
	private boolean validNameinPassword(String userName, String password) {
		if(password.toLowerCase().contains(userName.trim().toLowerCase())) {
			System.out.println("Password Validation Failed - password must not contain the user's name");
			return false;
		}

		//user's name has mentioned delimiters: commas, periods, dashes or hyphens, underscores, spaces, pound signs and tabs.
		String [] userNameTokens = userName.split("[\\s#_\\.,-]+");
		for(String token : userNameTokens) {
			//process only the token having length greater than or equal to 3
			if(token.length() >= 3 && password.toLowerCase().contains(token.toLowerCase())){
				System.out.println("Password Validation Failed - password must not contain the user name tokens");
				return false;
			}			
		}

		return true;
	}

	/**
	 * validate the emailaddress part in password
	 * password must not contain the local part and domain part of email address
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	private boolean validEmailinPassword(String email, String password) {
		String [] emailTokens = email.split("@");
		if(password.toLowerCase().contains(emailTokens[0].toLowerCase()) ||
				password.toLowerCase().contains(emailTokens[1].toLowerCase())){
			System.out.println("Password Validation Failed - password must not local part and domain part of user's email address");
			return false;
		}			

		return true;
	}

	/**
	 * validate the password characterset
	 * password must contain characters from 3 out of 5 defined character sets
	 * 
	 * 
	 * @param password
	 * @return
	 */
	private boolean validCharacterSet(String password) {
		int charsetMatchCount = 0;

		Pattern pattern1 = Pattern.compile(".*[A-Z].*");
		Pattern pattern2 = Pattern.compile(".*[a-z].*");
		Pattern pattern3 = Pattern.compile(".*\\d.*");
		
		if(pattern1.matcher(password).matches()) {
			charsetMatchCount++;
		}
		if(pattern2.matcher(password).matches()) {
			charsetMatchCount++;
		}
		if(pattern3.matcher(password).matches()) {
			charsetMatchCount++;
		}
		
		//if first 3 set are matched(upper, lower & number), no need to check further.		
		if(charsetMatchCount >= 3) {
			return true;
		}
		
		//Punctuation Characters: !’?”‐:,;()[]{}
		Pattern pattern4 = Pattern.compile(".*[!'?\"\\-:,;()\\[\\]{}].*");
		if(pattern4.matcher(password).matches()) {
			charsetMatchCount++;
		}
		if(charsetMatchCount >= 3) {
			return true;
		}
		
		//Symbols: ~@#$%^&*+=|<>/\
		Pattern pattern5 = Pattern.compile(".*[~@#$%^&*+=\\|<>/\\\\].*");
		if(pattern5.matcher(password).matches()) {
			charsetMatchCount++;
		}

		if(charsetMatchCount >= 3) {
			return true;
		}

		System.out.println("Password Validation Failed - password must contain characters from atleast 3 out of 5 defined character sets");
		return false;
	}
}
