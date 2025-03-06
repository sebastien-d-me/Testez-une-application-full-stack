package com.openclassrooms.starterjwt.models;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import com.openclassrooms.starterjwt.repository.SessionRepository;
import com.openclassrooms.starterjwt.services.SessionService;
import java.time.LocalDateTime;
import java.util.*;
import org.junit.jupiter.api.*;
import org.mockito.*;


public class SessionTest {
    @InjectMocks
    private SessionService sessionService;

    @Mock
    private SessionRepository sessionRepository;
    private Date sessionDate;
    Teacher teacher;
    List<User> users;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        sessionDate = new Date();
        createdAt = LocalDateTime.parse("2025-03-03T23:50:00");
        updatedAt = LocalDateTime.parse("2025-03-04T00:04:30");

        teacher = new Teacher(1L, "DOE", "John", createdAt, updatedAt);

        users = new ArrayList<>();
        users.add(new User(1L, "martin.petit@test.com", "PETIT", "Martin", "password123", false, createdAt, updatedAt)); 
        users.add(new User(2L, "leon.bernard@test.com", "BERNARD", "Léon", "password123", false, createdAt, updatedAt)); 
    }


    @Test
    /// Test - Setter of the session
    public void testSetter() {
        // Arrange
        Session session = new Session();
        session.setId(1L);
        session.setName("Lorem ipsum");
        session.setDate(sessionDate);
        session.setDescription("Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.");
        session.setTeacher(teacher);
        session.setUsers(users);
        session.setCreatedAt(createdAt);
        session.setUpdatedAt(updatedAt);

        // Act
        when(sessionRepository.save(session)).thenReturn(session);

        // Assert
        assertEquals(1L, session.getId());
        assertEquals("Lorem ipsum", session.getName());
        assertEquals(sessionDate, session.getDate());
        assertEquals("Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", session.getDescription());
        assertEquals(teacher, session.getTeacher());
        assertEquals(users, session.getUsers());
        assertEquals(createdAt, session.getCreatedAt());
        assertEquals(updatedAt, session.getUpdatedAt());
    }


    @Test
    /// Test - Getter of the session
    public void testGetter() {
        // Arrange
        Session session = new Session();
        session.setId(1L);
        session.setName("Lorem ipsum");
        session.setDate(sessionDate);
        session.setDescription("Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.");
        session.setTeacher(teacher);
        session.setUsers(users);
        session.setCreatedAt(createdAt);
        session.setUpdatedAt(updatedAt);

        // Act
        when(sessionRepository.save(session)).thenReturn(session);
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        Session sessionFound = sessionService.getById(1L);

        // Assert
        assertEquals(session.getId(), sessionFound.getId());
        assertEquals(session.getName(), sessionFound.getName());
        assertEquals(session.getDate(), sessionFound.getDate());
        assertEquals(session.getDescription(), sessionFound.getDescription());
        assertEquals(session.getTeacher(), sessionFound.getTeacher());
        assertEquals(session.getUsers(), sessionFound.getUsers());
        assertEquals(session.getCreatedAt(), sessionFound.getCreatedAt());
        assertEquals(session.getUpdatedAt(), sessionFound.getUpdatedAt());
    }
    

    @Test
    /// Test - Builder of the session
    public void testBuilderSession() {
        // Arrange
        Session session = Session.builder().id(1L).name("Lorem ipsum").date(sessionDate).description("Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.").teacher(teacher).users(users).createdAt(createdAt).updatedAt(updatedAt).build();

        // Act
        when(sessionRepository.save(session)).thenReturn(session);

        // Assert
        assertEquals(1L, session.getId());
        assertEquals("Lorem ipsum", session.getName());
        assertEquals(sessionDate, session.getDate());
        assertEquals("Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", session.getDescription());
        assertEquals(teacher, session.getTeacher());
        assertEquals(users, session.getUsers());
        assertEquals(createdAt, session.getCreatedAt());
        assertEquals(updatedAt, session.getUpdatedAt());
    }
    

    @Test
    /// Test - To String
    public void testToString() {
        // Arrange
        Session session = new Session();
        session.setId(1L);
        session.setName("Lorem ipsum");
        session.setDate(sessionDate);
        session.setDescription("Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.");
        session.setTeacher(teacher);
        session.setUsers(users);
        session.setCreatedAt(createdAt);
        session.setUpdatedAt(updatedAt);

        // Act
        when(sessionRepository.save(session)).thenReturn(session);

        // Assert
        assertEquals("Session(id=1, name=Lorem ipsum, date="+sessionDate+", description=Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque., teacher=Teacher(id=1, lastName=DOE, firstName=John, createdAt="+createdAt+", updatedAt="+updatedAt+"), users=[User(id=1, email=martin.petit@test.com, lastName=PETIT, firstName=Martin, password=password123, admin=false, createdAt="+createdAt+", updatedAt="+updatedAt+"), " + "User(id=2, email=leon.bernard@test.com, lastName=BERNARD, firstName=Léon, password=password123, admin=false, createdAt="+createdAt+", updatedAt="+updatedAt+")], createdAt="+createdAt+", updatedAt="+updatedAt+")", session.toString());
    }
}
