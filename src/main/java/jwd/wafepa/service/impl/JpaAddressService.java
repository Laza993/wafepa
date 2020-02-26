package jwd.wafepa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Address;
import jwd.wafepa.repository.AddressRepository;
import jwd.wafepa.service.AddressService;

@Service
public class JpaAddressService implements AddressService {
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override
	public Address findOne(Long id) {
		return addressRepository.findOne(id);
	}

	@Override
	public Address save(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public Address delete(Long id) {
		Address toDelete = addressRepository.findOne(id);
		if (toDelete != null) {
			addressRepository.delete(toDelete);
		}
		return toDelete;
	}

	@Override
	public List<Address> deleteAll(List<Long> iDes) {
		List<Address> deletedList = new ArrayList<Address>();
		for (Long id : iDes) {
			if (findOne(id) != null) {
				deletedList.add(delete(id));
			}
		}
		return deletedList;
	}

	@Override
	public Address update(Long id, Address address) {
		address.setId(id);
		return addressRepository.save(address);
	}

	
	@Override
	public List<Address> findAllByNumber(String number) {
		return addressRepository.findAllByNumber(number);
	}
	
	@Override
	public List<Address> findAllByStreet(String street) {
		return addressRepository.findAllByStreet(street);
	}

//	@PostConstruct
	public void init() {
		save(new Address("jovana ducica", "15a"));
		save(new Address(null, "laze lazarevica", "95"));
	}

	

}
