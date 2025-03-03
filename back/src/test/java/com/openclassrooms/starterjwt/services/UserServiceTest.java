package com.openclassrooms.starterjwt.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.mockito.*;


public class UserServiceTest {
    @InjectMocks
    private UserService userService;


    @Mock
    private UserRepository userRepository;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    
    @Test
    /// Test - Find user by ID
    public void testFindById() {
        // Arrange
        User user = new User("john.doe@test.com", "DOE", "John", "password", false); 

        // Act
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User userFound = userService.findById(1L);
 
        // Assert
        assertEquals("john.doe@test.com", userFound.getEmail());
    }

    @Test
    /// Test - Delete user by ID
    public void testDelete() {
        // Arrange
        User user = new User("john.doe@test.com", "DOE", "John", "password", false); 

        // Act
        doNothing().when(userRepository).deleteById(user.getId());
        userService.delete(user.getId());

        // Assert
        verify(userRepository).deleteById(user.getId());
    }
}
