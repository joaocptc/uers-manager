package com.example.users.controllers;

import com.example.users.adapters.repository.UserRepository;
import com.example.users.core.dtos.UserDTO;
import com.example.users.utils.UserUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class UserControllerIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    private void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Test find user without page and page-size")
    void testFindUserWithoutPageAndPageNumber() throws Exception {
        var user = UserUtil.buildValidUserData();
        userRepository.save(user);

        var response = this.mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("application/json", response.getResponse().getContentType());

        var body= response.getResponse().getContentAsString();

        var dtoList= new ObjectMapper().readValue(body, new TypeReference<List<UserDTO>>(){});
        var dto = dtoList.stream().findFirst().get();

        assertEquals(user.getUsername(), dto.getUsername());
        assertEquals(user.getPassword(), dto.getPassword());
        assertEquals(user.getEmail(), dto.getEmail());
    }

}
