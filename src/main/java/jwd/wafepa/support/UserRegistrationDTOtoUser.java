package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.User;
import jwd.wafepa.web.DTO.UserRegistrationDTO;

@Component
public class UserRegistrationDTOtoUser implements Converter<UserRegistrationDTO, User> {

	@Override
	public User convert(UserRegistrationDTO source) {
		User user = new User();
		user.setId(source.getId());
		user.setUsername(source.getUsername());
		user.setEmail(source.getEmail());
		user.setFirstName(source.getFirstName());
		user.setLastName(source.getLastName());
		user.setPassword(source.getPassword1());
		return user;
	}
	
	public List<User> convert(List<UserRegistrationDTO> dtos){
		List<User> users = new ArrayList<User>();
		for(UserRegistrationDTO dto : dtos) {
			User user = new User();
			user = convert(dto);
			users.add(user);
		}
		return users;
	}

}
