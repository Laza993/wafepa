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

import jwd.wafepa.model.Address;
import jwd.wafepa.service.AddressService;

@RestController
@RequestMapping(value = "/api/addresses")
public class ApiAddressController {
	@Autowired
	private AddressService addressService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Address>> getAll(){
		List<Address> addresses = addressService.findAll();
		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Address> getOne(@PathVariable Long id){
		Address address = addressService.findOne(id);
		return new ResponseEntity<>(address, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Address> addOne(@RequestBody Address address){
		Address saved = addressService.save(address);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<List<Address>> delete(@RequestBody List<Long> IDes){
		List<Address> deleted = addressService.deleteAll(IDes);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Address> delete(@PathVariable Long id){
		Address deleted = addressService.delete(id);
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody Address address){
		if(addressService.findOne(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(!id.equals(address.getId()) ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Address updated = addressService.update(id, address);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
}
