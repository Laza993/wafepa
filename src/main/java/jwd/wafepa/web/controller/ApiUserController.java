package jwd.wafepa.web.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.User;
import jwd.wafepa.service.RecordService;
import jwd.wafepa.service.UserService;
import jwd.wafepa.support.RecordToRecordDTO;
import jwd.wafepa.support.UserRegistrationDTOtoUser;
import jwd.wafepa.support.UserToUserDTO;
import jwd.wafepa.web.DTO.RecordDTO;
import jwd.wafepa.web.DTO.UserDTO;
import jwd.wafepa.web.DTO.UserRegistrationDTO;

@RestController
@RequestMapping(value = "/api/users")

public class ApiUserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserToUserDTO toUserDTO;
	@Autowired
	private UserRegistrationDTOtoUser registrationToUser;
	@Autowired
	private RecordService recordService;
	@Autowired
	private RecordToRecordDTO convertRecordToDto;

	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findUsers(
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName,
			@RequestParam(required = false) String userName,
			@RequestParam(required = false) String email
			){
		
		Page<User> userPage = null;
			
		if(firstName != null || lastName != null || userName != null || email != null) {
			userPage = userService.search(firstName, email, lastName, userName, pageNum);
		}else {
			userPage = userService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(userPage.getTotalPages()));
		
		return new ResponseEntity<>(toUserDTO.convert(userPage.getContent()), headers, HttpStatus.OK);
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
	public ResponseEntity<UserDTO> register(@RequestBody UserRegistrationDTO userReg){
		User added = userService.save(registrationToUser.convert(userReg));
		return new ResponseEntity<>(toUserDTO.convert(added), HttpStatus.CREATED);
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
		if(userService.getOne(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(!id.equals(registrationToUser.convert(userReg).getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User updated = userService.save(registrationToUser.convert(userReg));
		return new ResponseEntity<>(toUserDTO.convert(updated), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/records")
	public ResponseEntity<List<RecordDTO>> getRecordsFromUser(@PathVariable Long id){
		List<RecordDTO> dtos = convertRecordToDto.convert(recordService.findAllByUser(id));
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
}

