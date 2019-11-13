package com.demo.core.controller;

import com.demo.core.entity.Address;
import com.demo.core.service.AddressService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController implements ICrudController<Address, Long> {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public List<Address> read() {
        return this.addressService.read();
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Address read(@PathVariable("id") Long id) {
        return this.addressService.read(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public Address create(@RequestBody @Valid Address entity) {
        return this.addressService.create(entity);
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Address update(@PathVariable("id") Long id, @RequestBody @Valid Address entity) {
        return this.addressService.update(id, entity);
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
         this.addressService.delete(id);
    }
}
