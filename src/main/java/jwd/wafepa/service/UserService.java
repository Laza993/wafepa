package jwd.wafepa.service;

import java.util.List;

import org.springframework.data.domain.Page;

import jwd.wafepa.model.User;

public interface UserService {
	User save(User user);
	
	List<User> getUsers();
	
	User getOne(Long id);
	
	User update(Long id, User user);
	
	User delete(Long id);
	

	Page<User> search(String firstName, String email, String lastName, String userName, int pageNum);

	Page<User> findAll(int pageNum);
	
}
