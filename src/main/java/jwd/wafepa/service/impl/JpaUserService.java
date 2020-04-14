package jwd.wafepa.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

	
//	@PostConstruct
	public void biloSta(){
		save(new User(null, "laza@gmail.com", "Laza", "Lazarevic", "laza123"));
		save( new User(null, "kika@gmail.com", "Kika", "Lazarevic", "kika123"));
	}


	@Override
	public Page<User> search(String firstName, String email, String lastName, String userName, int pageNum) {
		if(firstName != null) {
			firstName = '%' + firstName + '%';
		}
		if(email != null) {
			email = '%' + email + '%';
		}
		if(lastName != null) {
			lastName = '%' + lastName + '%';
		}
		if(userName != null) {
			userName = '%' + userName + '%';
		}
		return userRepository.search(firstName, email, lastName, userName, new PageRequest(pageNum, 5));
	}

	@Override
	public Page<User> findAll(int pageNum) {
		 Page<User> page = userRepository.findAll(new PageRequest(pageNum, 5));
		return page;
	}
}
