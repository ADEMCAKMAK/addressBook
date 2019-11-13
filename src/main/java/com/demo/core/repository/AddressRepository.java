package com.demo.core.repository;

import com.demo.core.entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends IRepository<Address, Long> {
}
