package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.Demo;
import com.example.demo.service.DemoService;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {
    @Autowired private MockMvc mockMvc;

    @MockBean private DemoService loginService;

    @Test
    public void testSuccessfulLogin() throws Exception {
        Demo user = new Demo();
        user.setUsername("admin");
        when(loginService.log(anyString(), anyString())).thenReturn(user);

        mockMvc.perform(post("/login")
                .param("username", "admin")
                .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/welcome"));
    }

    @Test
    public void testFailedLogin() throws Exception {
        when(loginService.log(anyString(), anyString())).thenReturn(null);

        mockMvc.perform(post("/login")
                .param("username", "wrong")
                .param("password", "wrong"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("error"));
    }
}
