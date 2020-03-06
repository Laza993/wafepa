package jwd.wafepa.web.DTO;



import java.util.Date;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


import jwd.wafepa.model.Intensity;


public class RecordDTO {

	private Long id;
	
	private Date time;
	
	@Min(value=0)
	@Max(value=200)
	private int duration;
	
	private Intensity intensity;
	
	private Long userId;
	private Long activityId;
	
	private String userName;
	private String activityName;
	
	public RecordDTO() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Intensity getIntensity() {
		return intensity;
	}
	public void setIntensity(Intensity intensity) {
		this.intensity = intensity;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
}
