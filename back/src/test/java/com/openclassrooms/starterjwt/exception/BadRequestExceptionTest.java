package com.openclassrooms.starterjwt.exception;


import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class BadRequestExceptionTest {
    @Test
    /// Test - Extends exception
    public void testExtendsException() {
        // Arrange
        BadRequestException badRequestException = new BadRequestException();

        // Assert
        assertTrue(badRequestException instanceof RuntimeException);
    }
}
