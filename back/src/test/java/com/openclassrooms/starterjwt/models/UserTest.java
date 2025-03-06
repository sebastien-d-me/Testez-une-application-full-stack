package com.openclassrooms.starterjwt.models;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import com.openclassrooms.starterjwt.repository.UserRepository;
import com.openclassrooms.starterjwt.services.UserService;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.mockito.*;


public class UserTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        createdAt = LocalDateTime.parse("2025-03-03T23:50:00");
        updatedAt = LocalDateTime.parse("2025-03-04T00:04:30");
    }


    @Test
    /// Test - Setter of the user
    public void testSetter() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setEmail("john.doe@test.com");
        user.setLastName("DOE");
        user.setFirstName("John");
        user.setPassword("password");
        user.setAdmin(false);
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(updatedAt);

        // Act
        when(userRepository.save(user)).thenReturn(user);

        // Assert
        assertEquals(1L, user.getId());
        assertEquals("john.doe@test.com", user.getEmail());
        assertEquals("DOE", user.getLastName());
        assertEquals("John", user.getFirstName());
        assertEquals("password", user.getPassword());
        assertEquals(false, user.isAdmin());
        assertEquals(createdAt, user.getCreatedAt());
        assertEquals(updatedAt, user.getUpdatedAt());
    }


    @Test
    /// Test - Getter of the user
    public void testGetter() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setEmail("john.doe@test.com");
        user.setLastName("DOE");
        user.setFirstName("John");
        user.setPassword("password");
        user.setAdmin(false);
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(updatedAt);

        // Act
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User userFound = userService.findById(1L);

        // Assert
        assertEquals(user.getId(), userFound.getId());
        assertEquals(user.getEmail(), userFound.getEmail());
        assertEquals(user.getLastName(), userFound.getLastName());
        assertEquals(user.getFirstName(), userFound.getFirstName());
        assertEquals(user.getPassword(), userFound.getPassword());
        assertEquals(user.isAdmin(), userFound.isAdmin());
        assertEquals(user.getCreatedAt(), userFound.getCreatedAt());
        assertEquals(user.getUpdatedAt(), userFound.getUpdatedAt());
    }
    

    @Test
    /// Test - Builder of the user
    public void testBuilderUser() {
        // Arrange
        User user = User.builder().id(1L).email("john.doe@test.com").lastName("DOE").firstName("John").password("password").admin(false).createdAt(createdAt).updatedAt(updatedAt).build();

        // Act
        when(userRepository.save(user)).thenReturn(user);

        // Assert
        assertEquals(1L, user.getId());
        assertEquals("john.doe@test.com", user.getEmail());
        assertEquals("DOE", user.getLastName());
        assertEquals("John", user.getFirstName());
        assertEquals("password", user.getPassword());
        assertEquals(false, user.isAdmin());
        assertEquals(createdAt, user.getCreatedAt());
        assertEquals(updatedAt, user.getUpdatedAt());
    }


    @Test
    /// Test - To String
    public void testToString() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setEmail("john.doe@test.com");
        user.setLastName("DOE");
        user.setFirstName("John");
        user.setPassword("password");
        user.setAdmin(false);
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(updatedAt); 

        // Act
        when(userRepository.save(user)).thenReturn(user);

        // Assert
        assertEquals("User(id=1, email=john.doe@test.com, lastName=DOE, firstName=John, password=password, admin=false, createdAt="+createdAt+", updatedAt="+updatedAt+")", user.toString());
    }
}
