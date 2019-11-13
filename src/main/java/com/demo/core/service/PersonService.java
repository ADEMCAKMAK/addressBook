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
public class PersonService implements ICrudService<Person, Long> {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    public PersonService(PersonRepository personRepository, AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }


    @Override
    public List<Person> read() {
        return this.personRepository.read();
    }

    @Override
    public Person read(Long id) {
        return this.personRepository.read(id);
    }

    @Transactional
    @Override
    public Person create(Person entity) {
        return this.personRepository.create(entity);
    }

    @Transactional
    @Override
    public Person update(Long id, Person entity) {
        return this.personRepository.update(id, entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        this.personRepository.delete(id);
    }

    @Transactional
    public Person assignAddress(Long personId, Long addressId){
        Person foundPerson = this.personRepository.read(personId);
        Address foundAddress = this.addressRepository.read(addressId);
        foundPerson.setAddressId(foundAddress.getId());
        foundPerson.setAddress(foundAddress);
        return this.personRepository.update(personId,foundPerson);
    }


}
