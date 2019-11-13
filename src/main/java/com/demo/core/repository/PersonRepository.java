package com.demo.core.repository;

import com.demo.core.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends IRepository<Person, Long> {
    List<Person> findByAddressId(Long id);
}
