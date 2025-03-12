package com.openclassrooms.starterjwt.payload.request;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.repository.TeacherRepository;
import com.openclassrooms.starterjwt.repository.UserRepository;


public class SignupRequestTest {
    @Mock
    private UserRepository userRepository;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    /// Test - To String
    public void testToString() {
        // Arrange
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("john.doe@test.com");
        signupRequest.setFirstName("John");
        signupRequest.setLastName("DOE");
        signupRequest.setPassword("password123");

        // Act

        // Assert
        assertEquals("SignupRequest(email=john.doe@test.com, firstName=John, lastName=DOE, password=password123)", signupRequest.toString());
    }


    @Test
    /// Test - Hash
    public void testHashCode() {
        // Arrange
        SignupRequest signupRequestOne = new SignupRequest();
        signupRequestOne.setEmail("john.doe@test.com");
        signupRequestOne.setFirstName("John");
        signupRequestOne.setLastName("DOE");
        signupRequestOne.setPassword("password123");

        SignupRequest signupRequestTwo = new SignupRequest();
        signupRequestTwo.setEmail("martin.dupont@test.com");
        signupRequestTwo.setFirstName("Martin");
        signupRequestTwo.setLastName("DUPONT");
        signupRequestTwo.setPassword("password456");
        
        // Act

        // Assert
        assertFalse(signupRequestOne.hashCode() == signupRequestTwo.hashCode());
    }
}
