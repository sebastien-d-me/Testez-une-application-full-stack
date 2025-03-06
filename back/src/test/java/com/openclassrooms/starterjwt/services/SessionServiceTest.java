package com.openclassrooms.starterjwt.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.SessionRepository;
import java.time.LocalDateTime;
import java.util.*;
import org.junit.jupiter.api.*;
import org.mockito.*;


public class SessionServiceTest {
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
        users.add(new User("martin.petit@test.com", "PETIT", "Martin", "password123", false)); 
        users.add(new User("leon.bernard@test.com", "BERNARD", "Léon", "password123", false)); 
    }

    
    @Test
    /// Test - Create a session
    public void testCreate() {
        // Arrange
        Session session = new Session(1L, "Lorem ipsum", sessionDate, "Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", teacher, users, createdAt, updatedAt);

        // Act
        when(sessionRepository.save(session)).thenReturn(session);
        Session sessionsCreated = sessionService.create(session);

        // Assert
        assertEquals("Lorem ipsum", sessionsCreated.getName());
    }


    @Test
    /// Test - Delete
    public void testDelete() {
        // Arrange
        Session session = new Session(1L, "Lorem ipsum", sessionDate, "Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", teacher, users, createdAt, updatedAt);

        // Act
        doNothing().when(sessionRepository).deleteById(session.getId());
        sessionService.delete(session.getId());

        // Assert
        verify(sessionRepository).deleteById(session.getId());
    }


    @Test
    /// Test - Find all sessions
    public void testFindAll() {
        // Arrange
        LocalDateTime createdAtSecondary = LocalDateTime.parse("2025-04-03T22:30:00");
        LocalDateTime updatedAtSecondary = LocalDateTime.parse("2025-04-04T22:35:30");

        Teacher teacherSecondary = new Teacher(2L, "DUPONT", "Jean", createdAtSecondary, updatedAtSecondary);

        List<Session> sessions = new ArrayList<>();
        sessions.add(new Session(1L, "Lorem ipsum", sessionDate, "Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", teacher, users, createdAt, updatedAt));
        sessions.add(new Session(2L, "Dolor sit", sessionDate, "Maecenas consequat odio at vehicula volutpat.", teacherSecondary, users, createdAtSecondary, updatedAtSecondary));

        // Act
        when(sessionRepository.findAll()).thenReturn(sessions);
        List<Session> sessionsFound = sessionService.findAll();

        // Assert
        assertEquals(sessions, sessionsFound);
    }


    @Test
    /// Test - Find session by ID
    public void testGetById() {
        // Arrange
        Session session = new Session(1L, "Lorem ipsum", sessionDate, "Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", teacher, users, createdAt, updatedAt);

        // Act
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        Session sessionFound = sessionService.getById(1L);

        // Assert
        assertEquals("Lorem ipsum", sessionFound.getName());
    }


    @Test
    /// Test - Update a session
    public void testUpdate() {
        // Arrange
        Session session = new Session(1L, "Lorem ipsum", sessionDate, "Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", teacher, users, createdAt, updatedAt);
        
        // Act
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        when(sessionRepository.save(session)).thenReturn(session);
        Session sessionFound = sessionService.getById(1L);
        sessionFound.setName("Lorem ipsum updated");
        Session sessionsUpdated = sessionService.update(1L, sessionFound);

        // Assert
        assertEquals("Lorem ipsum updated", sessionsUpdated.getName());
    }
}
