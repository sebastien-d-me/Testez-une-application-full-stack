package com.openclassrooms.starterjwt.security.services;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.UserRepository;


public class UserDetailsServiceImplTest {
    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private UserRepository userRepository;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        createdAt = LocalDateTime.parse("2025-03-03T23:50:00");
        updatedAt = LocalDateTime.parse("2025-03-04T00:04:30");
    }


    @Test
    /// Test - Get credentials
    public void testGetCredentials() {
        // Arrange
        User user = new User(1L, "john.doe@test.com", "DOE", "John", "password", false, createdAt, updatedAt); 

        // Act
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        UserDetails userFound = userDetailsServiceImpl.loadUserByUsername(user.getEmail());

        // Assert
        assertEquals(userFound.getUsername(), user.getEmail());
        assertEquals(userFound.getPassword(), user.getPassword());
    }


    @Test
    /// Test - Not found exception
    public void testNotFoundException() {
        // Assert       
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsServiceImpl.loadUserByUsername(null);
        });
    }
}
