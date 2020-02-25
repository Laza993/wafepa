package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Activity;
import jwd.wafepa.web.DTO.ActivityDTO;

@Component
public class AtivityDTOtoActivity implements Converter<ActivityDTO, Activity> {

	@Override
	public Activity convert(ActivityDTO source) {
		Activity activity = new Activity();
		if(source.getId() == null) {
			activity.setName(source.getName());
		}else {
			activity.setId(source.getId());
			activity.setName(source.getName());
		}
		return activity;
	}

	public List<Activity> convert(List<ActivityDTO> dtos){
		List<Activity> activities = new ArrayList<Activity>();
		for(ActivityDTO dto : dtos) {
			Activity activity = convert(dto);
			activities.add(activity);
		}
		return activities;
	}

}
