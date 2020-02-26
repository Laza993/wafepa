package jwd.wafepa.web.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.support.ActivityToActivityDTO;
import jwd.wafepa.support.AtivityDTOtoActivity;
import jwd.wafepa.web.DTO.ActivityDTO;

@RestController
@RequestMapping(value = "api/activities")
public class ApiActivityController {
	@Autowired
	ActivityToActivityDTO toDTo;
	
	@Autowired
	AtivityDTOtoActivity convertToActivity;
	
	@Autowired
	ActivityService	activityServis;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ActivityDTO>> getAllActivities(){
		List<Activity> activities = activityServis.findAll();
		return new ResponseEntity<>(toDTo.convert(activities), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, params = {"name"})
	public ResponseEntity<List<ActivityDTO>> getAllActivities(@RequestParam String name){
		List<Activity> activities = activityServis.findAllByName(name);
		return new ResponseEntity<>(toDTo.convert(activities), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<ActivityDTO> getActivity(@PathVariable Long id){
		Activity activity = activityServis.findOne(id);
		if(id == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ActivityDTO dto = toDTo.convert(activity);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
//	@RequestMapping(method = RequestMethod.GET, params = {"name"})
//	public ResponseEntity<ActivityDTO> getActivityByName(@RequestParam String name){
//		System.out.println(name);
//		Activity activity = activityServis.findByName(name);
//		if(activity == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		System.out.println(activity.toString());
//		ActivityDTO dto = toDTo.convert(activity);
//		return new ResponseEntity<>(dto, HttpStatus.OK);
//	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<ActivityDTO> deleteActivity(@PathVariable Long id){
		Activity deleted = activityServis.delete(id);
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ActivityDTO dto = toDTo.convert(deleted);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ActivityDTO> addActivity(@RequestBody ActivityDTO dto){	
		Activity activity = convertToActivity.convert(dto);
		Activity added = activityServis.save(activity);
		if(added == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
		ActivityDTO addedDTO = toDTo.convert(added);
		return new ResponseEntity<>(addedDTO, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<ActivityDTO> update(@RequestBody ActivityDTO dto, @PathVariable Long id){
		Activity activity = convertToActivity.convert(dto);
		if(id != activity.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Activity persisted = activityServis.save(activity);
		ActivityDTO persistedDTO = toDTo.convert(persisted);
		return new ResponseEntity<>(persistedDTO, HttpStatus.OK);
	}
}
