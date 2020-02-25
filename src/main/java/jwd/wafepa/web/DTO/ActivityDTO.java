package jwd.wafepa.web.DTO;



public class ActivityDTO {
	private Long id;
	private String name;
	
	
	@Override
	public String toString() {
		return "ActivityDTO [id=" + id + ", name=" + name + "]";
	}
	public ActivityDTO() {
		super();
	}
	public ActivityDTO(Long id, String name) {
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
	
	
	
}
