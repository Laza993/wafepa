package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.User;

public interface UserService {
	User save(User user);
	
	List<User> getUsers();
	
	User getOne(Long id);
	
	User update(Long id, User user);
	
	User delete(Long id);
	
	User findByName(String name);
	
	List<User> findUsersByName(String name);
	
}
