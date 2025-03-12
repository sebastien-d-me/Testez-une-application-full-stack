package com.openclassrooms.starterjwt.payload.response;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;


public class MessageResponseTest {
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    /// Test - Create a session
    public void testSetMessage() {
        // Arrange
        MessageResponse messageResponse = new MessageResponse("");
        messageResponse.setMessage("Test");

        // Assert
        assertEquals("Test", messageResponse.getMessage());
    }
}
