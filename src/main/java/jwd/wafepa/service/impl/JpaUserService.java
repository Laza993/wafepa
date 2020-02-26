package jwd.wafepa.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jwd.wafepa.model.User;
import jwd.wafepa.repository.UserRepository;
import jwd.wafepa.service.UserService;

@Transactional
@Service
public class JpaUserService implements UserService {
	
	@Autowired
	private UserRepository userRepository;	
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	@Override
	public User getOne(Long id) {
		return userRepository.findOne(id);
	}
	@Override
	public User update(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}
	@Override
	public User delete(Long id) {
		User user = userRepository.findOne(id);
		if(user != null) {
			userRepository.delete(user);
		}
		return user;
	}
	@Override
	public User findByName(String name) {
		return userRepository.findByFirstName(name);
	}
	
	@Override
	public List<User> findUsersByName(String name) {
		return userRepository.findAllByFirstName(name);
	}
	
	//@PostConstruct
	public void biloSta(){
		save(new User(null, "laza@gmail.com", "Laza", "Lazarevic", "laza123"));
		save( new User(null, "kika@gmail.com", "Kika", "Lazarevic", "kika123"));
	}

	@Override
	public List<User> findUsersByLastName(String lastName) {
		return userRepository.findAllByLastName(lastName);
	}

	@Override
	public List<User> findUsersByEmail(String email) {
		return userRepository.findAllByEmail(email);
	}
}
