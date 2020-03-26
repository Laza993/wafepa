package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.User;
import jwd.wafepa.web.DTO.UserDTO;

@Component
public class UserToUserDTO implements Converter<User, UserDTO> {

	@Override
	public UserDTO convert(User source) {
		UserDTO dto = new UserDTO();
		dto.setId(source.getId());
		dto.setUsername(source.getUsername());
		dto.setEmail(source.getEmail());
		dto.setFirstName(source.getFirstName());
		dto.setLastName(source.getLastName());
		return dto;
	}
	
	public List<UserDTO> convert(List<User> users){
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		for(User user : users) {
			UserDTO dto = convert(user);
			dtos.add(dto);
		}
		return dtos;
	}
 
}
