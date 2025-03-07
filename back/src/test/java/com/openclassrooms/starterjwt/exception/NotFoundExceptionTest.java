package com.openclassrooms.starterjwt.exception;


import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class NotFoundExceptionTest {
    @Test
    /// Test - Instance
    public void testInstance() {
        // Arrange
        NotFoundException notFoundException = new NotFoundException();

        // Act

        // Assert
        assertTrue(notFoundException instanceof RuntimeException);
    }
}
