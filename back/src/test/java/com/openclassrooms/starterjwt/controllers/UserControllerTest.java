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
            .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Get the details of a not existing user
    public void testFindByNotExistId() throws Exception {
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
    /// Test - Get the details of a not number user ID
    public void testFindByNotNumberId() throws Exception {
        // Arrange
        User user = new User(1L, "john.doe@test.com", "DOE", "John", "password", false, createdAt, updatedAt); 

        // Act
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // Assert
        mockMvc.perform(get("/api/user/abc"))
            .andExpect(status().isBadRequest());
    }


    @Test
    @WithMockUser(username = "john.doe.2@test.com", roles = "USER")
    /// Test - Delete a user but unauthorized
    public void testDeleteUnauthorized() throws Exception {
        // Arrange
        User user = new User(1L, "john.doe@test.com", "DOE", "John", "password", false, createdAt, updatedAt); 

        // Act
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);
        when(userRepository.save(user)).thenReturn(user);

        // Assert
        mockMvc.perform(delete("/api/user/1"))
            .andExpect(status().isUnauthorized());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Delete a not existing user ID
    public void testDeleteNotExist() throws Exception {
        // Arrange
        User user = new User(2L, "john.doe@test.com", "DOE", "John", "password", false, createdAt, updatedAt); 

        // Act
        doNothing().when(userRepository).delete(user);
        when(userRepository.save(user)).thenReturn(user);

        // Assert
        mockMvc.perform(delete("/api/user/1"))
            .andExpect(status().isNotFound());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Delete a not number user ID
    public void testDeleteNotNumber() throws Exception {
        // Arrange
        User user = new User(1L, "john.doe@test.com", "DOE", "John", "password", false, createdAt, updatedAt); 

        // Act
        doNothing().when(userRepository).delete(user);
        when(userRepository.save(user)).thenReturn(user);

        // Assert
        mockMvc.perform(delete("/api/user/abc"))
            .andExpect(status().isBadRequest());
    }
}
