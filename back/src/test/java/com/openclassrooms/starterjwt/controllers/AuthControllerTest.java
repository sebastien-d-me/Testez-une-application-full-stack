package com.openclassrooms.starterjwt.controllers;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.starterjwt.payload.request.LoginRequest;
import com.openclassrooms.starterjwt.payload.request.SignupRequest;
import com.openclassrooms.starterjwt.repository.UserRepository;
import com.openclassrooms.starterjwt.security.jwt.JwtUtils;
import com.openclassrooms.starterjwt.security.services.UserDetailsImpl;


@AutoConfigureMockMvc
@SpringBootTest
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserRepository userRepository;


    @MockBean
    private AuthenticationManager authenticationManager;


    @MockBean
    private Authentication authentication;


    @MockBean
    private JwtUtils jwtUtils;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    /// Test - Authenticate a user
    public void testAuthenticate() throws Exception {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("john.doe@test.com");
        loginRequest.setPassword("password");

        String loginRequestJSON = new ObjectMapper().writeValueAsString(loginRequest);
        
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(1L, "john.doe@test.com", "John", "DOE", false, "password");

        String jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNzM5NTQ2MTE3fQ.fNmj7-TytXWP1LrFBOCbNt0tIUUcK6KFcGXTWmtggCs";

        // Act
        when(userRepository.existsByEmail(loginRequest.getEmail())).thenReturn(true);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetailsImpl);
        when(jwtUtils.generateJwtToken(authentication)).thenReturn(jwtToken);

        // Assert
        mockMvc.perform(post("/api/auth/login")
        .content(loginRequestJSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
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

