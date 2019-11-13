package com.demo.core.controller;

import com.demo.AddressBookApplication;
import com.demo.core.entity.Address;
import com.demo.core.repository.AddressRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AddressBookApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AddressRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AddressRepository repository;

    @After
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    public void whenValidInputThenCreateAddress() throws Exception {
        Address tester = new Address();
        tester.setAddressDetail("lorem ipsum");
        tester.setDirections("sit amet");
        ResultActions resultActions = mvc.perform(post("/api/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(tester))).andDo(print()).andExpect(
                status().is2xxSuccessful());
    }

    @Test
    public void whenInvalidInputThenCreateAddress() throws Exception {
        Address tester = new Address();
        tester.setAddressDetail("lor");
        tester.setDirections("sit amet");
        ResultActions resultActions = mvc.perform(post("/api/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(tester))).andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenDeleteAddress() throws Exception {
        Address tester = new Address();
        tester.setAddressDetail("lorem ipsum 2");
        tester.setDirections("sit amet");
        MvcResult mvcResult = mvc.perform(post("/api/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(tester))).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        Map addressMap = toMap(mvcResult.getResponse().getContentAsString());

        String url = "/api/address/"+addressMap.get("id");
        ResultActions resultActions = mvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(
                status().is2xxSuccessful());
    }

/*
    @Test
    public void whenInvalidIdDeleteAddress() throws Exception {

        String url = "/api/address/9";
        ResultActions resultActions = mvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(
                        status().is5xxServerError());
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
