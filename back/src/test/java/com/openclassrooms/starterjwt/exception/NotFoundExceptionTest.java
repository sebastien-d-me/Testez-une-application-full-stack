package com.openclassrooms.starterjwt.exception;


import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class NotFoundExceptionTest {
    @Test
    /// Test - Extends exception
    public void testExtendsException() {
        // Arrange
        NotFoundException notFoundException = new NotFoundException();

        // Assert
        assertTrue(notFoundException instanceof RuntimeException);
    }
}
