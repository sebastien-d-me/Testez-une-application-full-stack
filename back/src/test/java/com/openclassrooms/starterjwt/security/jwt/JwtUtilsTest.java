package com.openclassrooms.starterjwt.security.jwt;


import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;


public class JwtUtilsTest {
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    
    @Test
    /// Test - Not validate the JWT Token
    public void testNotValidate() {
        // Arrange
        JwtUtils jwtUtils = new JwtUtils();

        boolean validateJwtToken = jwtUtils.validateJwtToken("abc");

        // Assert
        assertFalse(validateJwtToken);
    }
}
