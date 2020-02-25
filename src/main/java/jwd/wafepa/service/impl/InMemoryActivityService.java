package jwd.wafepa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.ActivityService;

@Service
public class InMemoryActivityService implements ActivityService {
	
	private Map<Long, Activity> activities = new HashMap<>();
	
	private long nextID = 1l;
	
	
	@Override
	public List<Activity> findAll() {
//		List<Activity> activit = new ArrayList<Activity>();
//			for(Activity a : activities.values()) {
//				activit.add(a);
//			}			
//		return activit;
		return new ArrayList<Activity>(activities.values());
	}

	@Override
	public Activity findOne(Long id) {
		return activities.get(id);		
	}

	@Override
	public Activity save(Activity activity) {
		if(activity.getId() == null) {
			activity.setId(nextID);
			nextID += 1l;
		}
		
		return activities.put(activity.getId(), activity);
	}

	@Override
	public Activity delete(Long id) {
		return activities.remove(id);
	}

	@Override
	public Activity findByName(String name) {
		Activity retVal = null;
		for(Activity act : activities.values()) {
			if(act.getName().equals(name)) {
				retVal = act;
			}
		}
		return retVal;
	}

	
	@Override
	public List<Activity> saveList(List<Activity> activiti) {
		List<Activity> retVal = new ArrayList<Activity>();
		for(Activity act : activiti) {
			String name = act.getName();
			Activity novi = new Activity(name);
			save(novi);
			retVal.add(novi);
		}
		return retVal;
	}

	@Override
	public List<Activity> removeList(List<Long> IDes) {
		List<Activity> retVal = new ArrayList<Activity>();
		for(Long id : IDes) {
			Activity novi = findOne(id);
			retVal.add(novi);
			delete(id);
		}
		return retVal;
	}

	@Override
	public List<Activity> findAllByName(String name) {
		List<Activity> retVal = new ArrayList<Activity>();
		for(Activity act : activities.values()) {
			if(act.getName().equals(name)) {
				retVal.add(act);
			}
		}
		return retVal;
	}
	
}
