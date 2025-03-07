package com.openclassrooms.starterjwt.exception;


import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class BadRequestExceptionTest {
    @Test
    /// Test - Instance
    public void testInstance() {
        // Arrange
        BadRequestException badRequestException = new BadRequestException();

        // Act

        // Assert
        assertTrue(badRequestException instanceof RuntimeException);
    }
}
