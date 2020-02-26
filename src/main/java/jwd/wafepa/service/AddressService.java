package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Address;

public interface AddressService {
	List<Address> findAll();
	Address findOne(Long id);
	Address save(Address address);
	Address delete(Long id);
	List<Address> deleteAll(List<Long> iDes);
	Address update(Long id, Address address);
	
}
