package com.openclassrooms.starterjwt.security.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.payload.request.SignupRequest;
import com.openclassrooms.starterjwt.repository.UserRepository;
import com.openclassrooms.starterjwt.services.UserService;

public class UserDetailsImplTest {
    @InjectMocks
    private UserService userService;


    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;


    @Mock
    private UserRepository userRepository;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    /// Test - Find by mail
    public void testGetAdmin() {
        // Arrange
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(1L, "john.doe@test.com", "John", "DOE", false, "password");

        // Assert
        assertEquals(false, userDetailsImpl.getAdmin());
    }


    @Test
    /// Test - Equals
    public void testEquals() {
        // Arrange
        UserDetailsImpl userDetailsImplOne = new UserDetailsImpl(1L, "john.doe@test.com", "John", "DOE", false, "password");
        UserDetailsImpl userDetailsImplTwo = new UserDetailsImpl(1L, "john.doe@test.com", "John", "DOE", false, "password");

        // Assert
        assertTrue(userDetailsImplOne.equals(userDetailsImplTwo));
    }


    @Test
    /// Test - Account not expired
    public void testNotExpired() {
        // Arrange
        UserDetailsImpl userDetailsImplTwo = new UserDetailsImpl(1L, "john.doe@test.com", "John", "DOE", false, "password");

        // Assert
        assertTrue(userDetailsImplTwo.isAccountNonExpired());
    }
    

    @Test
    /// Test - Account not locked
    public void testNotLocked() {
        // Arrange
        UserDetailsImpl userDetailsImplTwo = new UserDetailsImpl(1L, "john.doe@test.com", "John", "DOE", false, "password");

        // Assert
        assertTrue(userDetailsImplTwo.isAccountNonLocked());
    }
    

    @Test
    /// Test - Credential not expired
    public void testCredientialNotExpired() {
        // Arrange
        UserDetailsImpl userDetailsImplTwo = new UserDetailsImpl(1L, "john.doe@test.com", "John", "DOE", false, "password");

        // Assert
        assertTrue(userDetailsImplTwo.isCredentialsNonExpired());
    }
    

    @Test
    /// Test - Account enabled
    public void testEnabled() {
        // Arrange
        UserDetailsImpl userDetailsImplTwo = new UserDetailsImpl(1L, "john.doe@test.com", "John", "DOE", false, "password");

        // Assert
        assertTrue(userDetailsImplTwo.isEnabled());
    }
}
