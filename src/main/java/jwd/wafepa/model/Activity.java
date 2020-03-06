package jwd.wafepa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	
	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Record> records = new ArrayList<Record>();

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}
	
	public void addRecord(Record record){
		if(record.getActivity() != this){
			record.setActivity(this);
		}
		records.add(record);
	}

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
