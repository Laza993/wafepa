package jwd.wafepa.web.DTO;

public class UserRegistrationDTO {
	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private String password1;
	private String password2;
	
	public UserRegistrationDTO() {
		super();
	}

	public UserRegistrationDTO(Long id, String email, String firstName, String lastName, String password1,
			String password2) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password1 = password1;
		this.password2 = password2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	@Override
	public String toString() {
		return "UserRegistrationDTO [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName="
				+ lastName + ", password1=" + password1 + ", password2=" + password2 + "]";
	}
	
	
}
