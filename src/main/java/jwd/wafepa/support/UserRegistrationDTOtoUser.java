package jwd.wafepa.support;


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
}
