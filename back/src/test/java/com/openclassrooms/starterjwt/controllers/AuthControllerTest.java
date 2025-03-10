package com.openclassrooms.starterjwt.controllers;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.starterjwt.payload.request.SignupRequest;
import com.openclassrooms.starterjwt.repository.UserRepository;


@AutoConfigureMockMvc
@SpringBootTest
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserRepository userRepository;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    /// Test - Register a user already exist
    public void testRegisterAlreadyExist() throws Exception {
        // Arrange
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("john.doe@test.com");
        signupRequest.setFirstName("John");
        signupRequest.setLastName("DOE");
        signupRequest.setPassword("password");

        String signupRequestJSON = new ObjectMapper().writeValueAsString(signupRequest);
        
        // Act
        when(userRepository.existsByEmail(signupRequest.getEmail())).thenReturn(true);

        // Assert
        mockMvc.perform(post("/api/auth/register")
        .content(signupRequestJSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    }

    
    @Test
    /// Test - Register a user
    public void testRegister() throws Exception {
        // Arrange
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("john.doe@test.com");
        signupRequest.setFirstName("John");
        signupRequest.setLastName("DOE");
        signupRequest.setPassword("password");

        String signupRequestJSON = new ObjectMapper().writeValueAsString(signupRequest);
        
        // Act
        when(userRepository.existsByEmail(signupRequest.getEmail())).thenReturn(false);

        // Assert
        mockMvc.perform(post("/api/auth/register")
        .content(signupRequestJSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }
}

