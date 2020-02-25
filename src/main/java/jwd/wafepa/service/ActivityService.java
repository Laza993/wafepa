package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Activity;

public interface ActivityService {
	
	
	List<Activity> findAll();
	
	Activity findOne(Long id);
	
	// vraca snimljeni objekat, da proverimo da li je snimljen kako valja
	Activity save(Activity activity);
	
	// vraca obrisan objekat, da bi mogli da ga vratimo ako smo greskom obrisali
	Activity delete(Long id);
	
	Activity findByName(String name);
	
	List<Activity> findAllByName(String name);
	
	List<Activity> saveList(List<Activity> activiti);
	
	List<Activity> removeList(List<Long> IDes);
}
