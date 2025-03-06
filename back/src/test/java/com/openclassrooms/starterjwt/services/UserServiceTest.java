package com.openclassrooms.starterjwt.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.mockito.*;


public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Mock
    private UserRepository userRepository;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        createdAt = LocalDateTime.parse("2025-03-03T23:50:00");
        updatedAt = LocalDateTime.parse("2025-03-04T00:04:30");
    }

    
    @Test
    /// Test - Delete user by ID
    public void testDelete() {
        // Arrange
        User user = new User(1L, "john.doe@test.com", "DOE", "John", "password", false, createdAt, updatedAt); 

        // Act
        doNothing().when(userRepository).deleteById(user.getId());
        userService.delete(user.getId());

        // Assert
        verify(userRepository).deleteById(user.getId());
    }


    @Test
    /// Test - Find user by ID
    public void testFindById() {
        // Arrange
        User user = new User(1L, "john.doe@test.com", "DOE", "John", "password", false, createdAt, updatedAt); 

        // Act
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User userFound = userService.findById(1L);
 
        // Assert
        assertEquals("john.doe@test.com", userFound.getEmail());
    }
}
