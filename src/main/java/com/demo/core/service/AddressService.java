package com.demo.core.service;

import com.demo.core.entity.Address;
import com.demo.core.entity.Person;
import com.demo.core.repository.AddressRepository;
import com.demo.core.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class AddressService implements ICrudService<Address, Long> {

    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    public AddressService(AddressRepository addressRepository, PersonRepository personRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<Address> read() {
        return this.addressRepository.read();
    }

    @Override
    public Address read(Long id) {
        return this.addressRepository.read(id);
    }

    @Transactional
    @Override
    public Address create(Address entity) {
        return this.addressRepository.create(entity);
    }

    @Transactional
    @Override
    public Address update(Long id, Address entity) {
        return this.addressRepository.update(id, entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {

        this.addressRepository.delete(id);
        List<Person> personList = this.personRepository.findByAddressId(id);
        if (!personList.isEmpty()) {
            personList.forEach(item -> {
                item.setAddressId(null);
            });
            this.personRepository.saveAll(personList);
        }

    }


}
