package assessment;

public class User {
	
	private String userName;
	private String email;
	private String password;
	private String role;
	private String accessReason;
	
	
	public User(String userName, String email, String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAccessReason() {
		return accessReason;
	}
	public void setAccessReason(String accessReason) {
		this.accessReason = accessReason;
	}

	public String toString() {
		return "[" + userName + "] [" + email + "] " + password;
	}
}
