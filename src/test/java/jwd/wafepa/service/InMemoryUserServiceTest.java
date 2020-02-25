package jwd.wafepa.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jwd.wafepa.model.User;
import jwd.wafepa.service.impl.InMemoryUserService;

public class InMemoryUserServiceTest{
	
	UserService userService;
	
	@Before
	public void setUp() {
		userService = new InMemoryUserService();
		userService.save(new User(null, "laza@gmail.com", "Laza", "Lazarevic"));
		userService.save( new User(null, "kika@gmail.com", "Kika", "Lazarevic"));
	}
	
	@Test
	public void getOne() {
		Long id = 1L;
		User user = userService.getOne(1L);
		Assert.assertEquals(user.getFirstName(), "Laza");
		Assert.assertEquals(user.getLastName(), "Lazarevic");
		Assert.assertEquals(user.getEmail(), "laza@gmail.com");
		Assert.assertEquals(user.getId(), id);
	}
	
	@Test
	public void getUsers(){
		List<User> users = userService.getUsers();
		Assert.assertEquals(users.size(), 2);
	}
	
	
}
