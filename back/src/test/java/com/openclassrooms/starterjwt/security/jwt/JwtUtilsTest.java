package com.openclassrooms.starterjwt.security.jwt;


import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;


public class JwtUtilsTest {
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    
    @Test
    /// Test - Invalid authToken
    public void testValidateJwtTokenFalse() {
        // Arrange
        JwtUtils jwtUtils = new JwtUtils();
        boolean validateJwtToken = jwtUtils.validateJwtToken("abc");

        // Assert
        assertFalse(validateJwtToken);
    }
}
