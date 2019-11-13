package com.demo.core.controller;

import com.demo.AddressBookApplication;
import com.demo.core.entity.Address;
import com.demo.core.entity.Person;
import com.demo.core.repository.PersonRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AddressBookApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class PersonRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PersonRepository repository;

    @After
    public void resetDb() {
        repository.deleteAll();
    }

    // kisi ekle
    @Test
    public void whenValidInput_thenCreatePerson() throws Exception {
        Person tester = new Person();
        tester.setFirstName("adem");
        tester.setLastName("cakmak");
        ResultActions resultActions = mvc.perform(post("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(tester))).andDo(print()).andExpect(
                        status().is2xxSuccessful());
    }

    @Test
    public void whenInvalidInput_thenCreatePerson() throws Exception {
        Person tester = new Person();
        tester.setFirstName("adem3");
        tester.setLastName("cakmak");
        ResultActions resultActions = mvc.perform(post("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(tester))).andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // kisiye adres ata
    @Test
    public void whenValidAddressAssignedtoValidPerson() throws Exception {
        Person tester = new Person();
        tester.setFirstName("adem");
        tester.setLastName("cakmak");
        Address address = new Address();
        address.setAddressDetail("lorem ipsum");
        address.setDirections("sit amet");
        MvcResult mvcResultPerson = mvc.perform(post("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(tester))).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        Map personMap = toMap(mvcResultPerson.getResponse().getContentAsString());

        MvcResult mvcResultAddress = mvc.perform(post("/api/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(address))).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        Map addressMap = toMap(mvcResultAddress.getResponse().getContentAsString());

        String url = "/api/person/assignAddress?personId="+personMap.get("id")+"&addressId="+addressMap.get("id");
        MvcResult assignAddress = mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

/*
        String url_ = "/api/person/"+personMap.get("id");
        MvcResult personWithAddress = mvc.perform(get(url_)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
*/

    }

/*
    @Test
    public void whenInvalidIdDeletePerson() throws Exception {

        Long addressId = 9L;
        String url = "/api/person/"+addressId.toString();
        ResultActions resultActions = mvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is5xxServerError());
    }
*/



    private byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    private Map toMap(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Map.class);
    }

}


