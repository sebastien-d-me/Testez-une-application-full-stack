package com.openclassrooms.starterjwt.security.services;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.*;


public class UserDetailsImplTest {
    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    private UserDetailsImpl userDetailsImpl;


    @BeforeEach
    public void init() {
        userDetailsImpl = new UserDetailsImpl(1L, "john.doe@test.com", "John", "DOE", false, "password");

        MockitoAnnotations.openMocks(this);
    }


    @Test
    /// Test - Get admin
    public void testGetAdmin() {
        // Assert
        assertEquals(false, userDetailsImpl.getAdmin());
    }


    @Test
    /// Test - Equals
    public void testEquals() {
        // Arrange
        UserDetailsImpl userDetailsImplTwo = new UserDetailsImpl(1L, "john.doe@test.com", "John", "DOE", false, "password");

        // Assert
        assertTrue(userDetailsImpl.equals(userDetailsImplTwo));
    }


    @Test
    /// Test - Account not expired
    public void testNotExpired() {
        // Assert
        assertTrue(userDetailsImpl.isAccountNonExpired());
    }
    

    @Test
    /// Test - Account not locked
    public void testNotLocked() {
        // Assert
        assertTrue(userDetailsImpl.isAccountNonLocked());
    }
    

    @Test
    /// Test - Credential not expired
    public void testCredientialNotExpired() {
        // Assert
        assertTrue(userDetailsImpl.isCredentialsNonExpired());
    }
    

    @Test
    /// Test - Account enabled
    public void testEnabled() {
        assertTrue(userDetailsImpl.isEnabled());
    }
}
