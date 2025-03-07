package com.openclassrooms.starterjwt.controllers;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;


@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {     
    @Autowired
    private MockMvc mockMvc;


    @InjectMocks
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @MockBean
    private UserRepository userRepository;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        createdAt = LocalDateTime.parse("2025-03-03T23:50:00");
        updatedAt = LocalDateTime.parse("2025-03-04T00:04:30");
    }

    
    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Get the details of a user
    public void testFindById() throws Exception {
        // Arrange
        User user = new User(1L, "john.doe@test.com", "DOE", "John", "password", false, createdAt, updatedAt); 

        // Act
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // Assert
        mockMvc.perform(get("/api/user/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.email").value(user.getEmail()));
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Get the details of a non existing user
    public void testFindByNonExistId() throws Exception {
        // Arrange
        User user = new User(2L, "john.doe@test.com", "DOE", "John", "password", false, createdAt, updatedAt); 

        // Act
        when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // Assert
        mockMvc.perform(get("/api/user/1"))
            .andExpect(status().isNotFound());
    }
    

    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Get the details of a non number user ID
    public void testFindByNonNumberId() throws Exception {
        // Arrange
        User user = new User(1L, "john.doe@test.com", "DOE", "John", "password", false, createdAt, updatedAt); 

        // Act
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // Assert
        mockMvc.perform(get("/api/user/abc"))
            .andExpect(status().isBadRequest());
    }
}
