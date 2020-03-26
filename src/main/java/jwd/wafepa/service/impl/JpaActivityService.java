package jwd.wafepa.service.impl;

import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jwd.wafepa.model.Activity;
import jwd.wafepa.repository.ActivityRepository;
import jwd.wafepa.service.ActivityService;

@Transactional
@Service
public class JpaActivityService implements ActivityService {
	
	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public List<Activity> findAll() {
		return activityRepository.findAll();
	}

	@Override
	public Activity findOne(Long id) {
		return activityRepository.findOne(id);
	}

	@Override
	public Activity save(Activity activity) {
		return activityRepository.save(activity);
	}

	@Override
	public Activity delete(Long id) {
		Activity toDelete = findOne(id);
		if (toDelete != null) {
			activityRepository.delete(toDelete);
		}
		return toDelete;
	}

	@Override
	public Activity findByName(String name) {
		return activityRepository.findByName(name);
	}

	@Override
	public List<Activity> findAllByName(String name) {
		return activityRepository.findAllByName(name);
	}

	@Override
	public List<Activity> saveList(List<Activity> activiti) {
		return activityRepository.save(activiti);
	}

	@Override
	public List<Activity> removeList(List<Long> IDes) {
		List<Activity> deleted = new ArrayList<Activity>();
		for (Long id : IDes) {
			if (findOne(id) != null) {
				deleted.add(findOne(id));
				activityRepository.delete(findOne(id));
			}
		}
		return deleted;
	}
	
//	@PostConstruct
	public void БилоШта(){
		save(new Activity("Swimming"));
		save(new Activity("Running"));
		
	}

}
