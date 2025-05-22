package com.example.jolokia_demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void hello_withoutAuthentication_shouldReturn401() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "login", roles = {"JOLOKIA_USER"})
    void hello_withAuthentication_shouldReturnHelloWorld() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }
}
