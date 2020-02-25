package jwd.wafepa.model;


public class Activity {
	private Long id;
	private String name;
	private String adminComment = "dummy data!";
	
	
	
	@Override
	public String toString() {
		return "Activity [id=" + id + ", name=" + name + ", adminComment=" + adminComment + "]";
	}


	public String getAdminComment() {
		return adminComment;
	}


	public void setAdminComment(String adminComment) {
		this.adminComment = adminComment;
	}


	public Activity() {
		super();
	}


	public Activity(String name) {
		super();
		this.name = name;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
