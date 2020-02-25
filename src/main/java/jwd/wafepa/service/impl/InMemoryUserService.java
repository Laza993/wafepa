package jwd.wafepa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;

@Service
public class InMemoryUserService implements UserService {
	HashMap<Long, User> users = new HashMap<Long, User>();
	private long ID = 1l;
	
	@Override
	public User save(User user) {
//		String email = user.getEmail();
//		String name = user.getFirstName();
//		String lastName = user.getLastName();

		if(user.getId() == null) {
			user.setId(ID);
			ID++;
		}
		return users.put(user.getId(), user);
		
//		return users.put(id, new User(id, email, name, lastName));
	}

	@Override
	public List<User> getUsers() {
		List<User> userss = new ArrayList<User>(users.values());
		return userss;
	}

	@Override
	public User getOne(Long id) {
		User user = users.get(id);
		return user;
	}

	@Override
	public User update(Long id, User user) {
		User retVal = null;
		retVal = users.put(id, user);
		return retVal;
	}

	@Override
	public User delete(Long id) {
		User deleted = users.remove(id);
		return deleted;
	}

	@Override
	public User findByName(String name) {
		User retVal = null;
		for(User user : users.values()) {
			if((user.getFirstName()).equals(name)) {
				retVal = user;
			}
		}
		return retVal;
	}

	@Override
	public List<User> findUsersByName(String name) {
		List<User> retVal = new ArrayList<User>();
		for(User user : users.values()) {
			if((user.getFirstName()).equals(name)) {
				retVal.add(user);
			}
		}
		return retVal;
	}

}
