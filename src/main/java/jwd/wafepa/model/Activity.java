package jwd.wafepa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tblActivity")
public class Activity {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "adminComment")
	private String adminComment = "dummy data!";
	

	public Activity() {
		super();
	}

	public Activity(String name) {
		super();
		this.name = name;
	}
	
	public Activity(Long id, String name) {
		super();
		this.id = id;
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
}
