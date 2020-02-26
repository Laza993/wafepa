package jwd.wafepa.web.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;
import jwd.wafepa.support.UserRegistrationDTOtoUser;
import jwd.wafepa.support.UserToUserDTO;
import jwd.wafepa.web.DTO.UserDTO;
import jwd.wafepa.web.DTO.UserRegistrationDTO;

@RestController
@RequestMapping(value = "/api/users")

public class ApiUserController {
	
	@Autowired
	UserService userService;
	@Autowired
	UserToUserDTO toUserDTO;
	@Autowired
	UserRegistrationDTOtoUser registrationToUser;
	
	
//	@RequestMapping(method = RequestMethod.GET, params = {"name"})
//	public ResponseEntity<List<UserDTO>> getUsers(@RequestParam String name){
//		List<User> users = userService.findUsersByName(name);
//		return new ResponseEntity<>(toUserDTO.convert(users), HttpStatus.OK);
//	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getUsers(){
		List<User> users = userService.getUsers();
		return new ResponseEntity<>(toUserDTO.convert(users), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<UserDTO> getOne(@PathVariable Long id){
		User user = userService.getOne(id);
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toUserDTO.convert(user), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> register(@RequestBody UserRegistrationDTO userReg){
		
		User added = userService.save(registrationToUser.convert(userReg));
		return new ResponseEntity<>(added, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id){
		User deleted = userService.delete(id);
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toUserDTO.convert(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserRegistrationDTO userReg){
		if(id != registrationToUser.convert(userReg).getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User updated = userService.save(registrationToUser.convert(userReg));
		
		return new ResponseEntity<>(toUserDTO.convert(updated), HttpStatus.OK);
	}
	
}

