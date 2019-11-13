package com.demo.core.controller;

import com.demo.core.entity.Person;
import com.demo.core.exception.NotFoundException;
import com.demo.core.service.PersonService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController implements ICrudController<Person, Long> {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public List<Person> read() {
        return this.personService.read();
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Person read(@PathVariable("id") Long id) throws NotFoundException {
        return this.personService.read(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public Person create(@RequestBody @Valid Person entity) {
        return this.personService.create(entity);
    }


    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Person update(@PathVariable("id") Long id, @RequestBody @Valid Person entity) {
        return this.personService.update(id, entity);
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        this.personService.delete(id);
    }

    @RequestMapping(value = "/assignAddress", method = RequestMethod.PUT)
    public Person assignAddress(@RequestParam("personId") Long personId, @RequestParam("addressId") Long addressId){
        return this.personService.assignAddress(personId, addressId);
    }
}
