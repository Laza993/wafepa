package jwd.wafepa.service;



import java.util.ArrayList;
import java.util.List;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.impl.InMemoryActivityService;

public class InMemoryActivityServiceTest {
	
	private ActivityService activityService;

	@Before
	public void setUp() {
		activityService = new InMemoryActivityService();
		
		Activity a1 = new Activity("swimming");
		Activity a2 = new Activity("running");
		
		activityService.save(a1);
		activityService.save(a2);
	}
	
	@Test
	public void TestFindOne() {
		Activity a = activityService.findOne(2L);
		Assert.assertEquals("running", a.getName());
	}
	
	@Test
	public void TestFindOneNotExist() {
		Activity a = activityService.findOne(3L);
		Assert.assertNull(a);
	}
	
	
//	@Test (expected = InvalidAttributesException.class)
//	public void TestFindOneInvalid() {
//		Activity a = activityService.findOne(null);
//		Assert.assertNull(a);
//	}
	
	@Test
	public void TestFindAll() {
		List<Activity> akti = activityService.findAll();
		Assert.assertEquals(2, akti.size());
	}
	
	@Test
	public void TestDelete() {
		Activity a = activityService.delete(1L);
		Assert.assertNull(a);
	}
	
	@Test
	public void TestfindByName() {
		Activity a = activityService.findByName("swimming");
		Assert.assertNotNull(a);
		Assert.assertEquals(a.getName(), "swimming");
	}
	
	@Test
	public void TestfindByNameInvalid() {
		Activity a = activityService.findByName("");
		Assert.assertNull(a);
	}
	@Test
	public void TestfindByNameNotExists() {
		Activity a = activityService.findByName("sdas");
		Assert.assertNull(a);
	}
	
	@Test// (expected = InvalidAttributesException.class)
	public void TestfindByNameWronginput() {
		Activity a = activityService.findByName(null);
		Assert.assertNull(a);
	}
	
	@Test
	public void TestsaveList() {
		List<Activity> listAct = new ArrayList<Activity>();
		Activity a1 = new Activity("diving");
		Activity a2 = new Activity("climbing");
		listAct.add(a1);
		listAct.add(a2);
		List<Activity> listaa = activityService.saveList(listAct);
		//listaa.get(0).getName()
		Assert.assertEquals(listaa.get(0), activityService.findByName("diving"));	
		Assert.assertEquals(listaa.get(1), activityService.findByName("climbing"));
		Assert.assertEquals(activityService.findAll().size(), 4);
	}

	@Test
	public void TestremoveList() {
		List<Long> ides = new ArrayList<Long>();
		ides.add(1L);
		ides.add(2L);
		List<Activity> listaa = activityService.removeList(ides);
		Assert.assertNull(activityService.findOne(1L));	
		Assert.assertNull(activityService.findOne(2L));
		Assert.assertEquals(activityService.findAll().size(), 0);
		Assert.assertEquals(listaa.size(), 2);
	}
	
	
	
}
