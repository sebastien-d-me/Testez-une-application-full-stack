package com.openclassrooms.starterjwt.payload.response;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;


public class JwtResponseTest {
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    /// Test - Create a Token
    public void testCreateToken() {
        // Arrange
        JwtResponse jwtResponse = new JwtResponse("", 1L, "", "", "", false);
        jwtResponse.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNzM5NTQ2MTE3fQ.fNmj7-TytXWP1LrFBOCbNt0tIUUcK6KFcGXTWmtggCs");
        jwtResponse.setType("Bearer");
        jwtResponse.setId(2L);
        jwtResponse.setUsername("john.doe@test.com");
        jwtResponse.setFirstName("John");
        jwtResponse.setLastName("DOE");
        jwtResponse.setAdmin(true);

        // Assert
        assertEquals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNzM5NTQ2MTE3fQ.fNmj7-TytXWP1LrFBOCbNt0tIUUcK6KFcGXTWmtggCs", jwtResponse.getToken());
        assertEquals("Bearer", jwtResponse.getType());
        assertEquals(2L, jwtResponse.getId());
        assertEquals("john.doe@test.com", jwtResponse.getUsername());
        assertEquals("John", jwtResponse.getFirstName());
        assertEquals("DOE", jwtResponse.getLastName());
        assertEquals(true, jwtResponse.getAdmin());
    } 
}
