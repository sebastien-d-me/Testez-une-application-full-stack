package com.openclassrooms.starterjwt.controllers;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import com.openclassrooms.starterjwt.models.*;
import com.openclassrooms.starterjwt.repository.SessionRepository;


@AutoConfigureMockMvc
@SpringBootTest
public class SessionControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @InjectMocks
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @MockBean
    private SessionRepository sessionRepository;


    @Mock
    private Date sessionDate;
    Teacher teacher;
    List<User> users;


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
    @WithMockUser(roles = "ADMIN")
    /// Test - Get the details of a session
    public void testFindById() throws Exception {
        // Arrange
        Session session = new Session(1L, "Lorem ipsum", sessionDate, "Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", teacher, users, createdAt, updatedAt);

        // Act
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        when(sessionRepository.save(session)).thenReturn(session);

        // Assert
        mockMvc.perform(get("/api/session/1"))
            .andExpect(status().isOk());
    } 


    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Get the details of a not existing session
    public void testFindByNotExistId() throws Exception {
        // Arrange
        Session session = new Session(2L, "Lorem ipsum", sessionDate, "Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", teacher, users, createdAt, updatedAt);

        // Act
        when(sessionRepository.findById(2L)).thenReturn(Optional.of(session));
        when(sessionRepository.save(session)).thenReturn(session);

        // Assert
        mockMvc.perform(get("/api/session/1"))
            .andExpect(status().isNotFound());
    }
    

    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Get the details of a not number session ID
    public void testFindByNotNumberId() throws Exception {
        // Arrange
        Session session = new Session(1L, "Lorem ipsum", sessionDate, "Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", teacher, users, createdAt, updatedAt);

        // Act
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        when(sessionRepository.save(session)).thenReturn(session);

        // Assert
        mockMvc.perform(get("/api/session/abc"))
            .andExpect(status().isBadRequest());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Get the details of all sessions
    public void testFindAll() throws Exception {
        // Arrange
        LocalDateTime createdAtSecondary = LocalDateTime.parse("2025-04-03T22:30:00");
        LocalDateTime updatedAtSecondary = LocalDateTime.parse("2025-04-04T22:35:30");

        Session sessionOne = new Session(1L, "Lorem ipsum", sessionDate, "Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", teacher, users, createdAt, updatedAt);
        Session sessionTwo = new Session(2L, "Dolor sit", sessionDate, "Maecenas consequat odio at vehicula volutpat.", teacher, users, createdAtSecondary, updatedAtSecondary);

        List<Session> sessions = new ArrayList<>();
        sessions.add(sessionOne);
        sessions.add(sessionTwo);
        
        // Act
        when(sessionRepository.findAll()).thenReturn((sessions));
        when(sessionRepository.save(sessionOne)).thenReturn(sessionOne);
        when(sessionRepository.save(sessionTwo)).thenReturn(sessionTwo);

        // Assert
        mockMvc.perform(get("/api/session"))
            .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Delete a not existing session ID
    public void testDeleteNotExist() throws Exception {
        // Arrange
        Session session = new Session(2L, "Lorem ipsum", sessionDate, "Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", teacher, users, createdAt, updatedAt);

        // Act
        doNothing().when(sessionRepository).delete(session);
        when(sessionRepository.save(session)).thenReturn(session);

        // Assert
        mockMvc.perform(delete("/api/session/1"))
            .andExpect(status().isNotFound());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Delete a existing session ID
    public void testDelete() throws Exception {
        // Arrange
        Session session = new Session(1L, "Lorem ipsum", sessionDate, "Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", teacher, users, createdAt, updatedAt);

        // Act
        doNothing().when(sessionRepository).delete(session);
        when(sessionRepository.save(session)).thenReturn(session);
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));

        // Assert
        mockMvc.perform(delete("/api/session/1"))
            .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Delete a not number session ID
    public void testDeleteNotNumber() throws Exception {
        // Arrange
        Session session = new Session(1L, "Lorem ipsum", sessionDate, "Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", teacher, users, createdAt, updatedAt);

        // Act
        doNothing().when(sessionRepository).delete(session);
        when(sessionRepository.save(session)).thenReturn(session);

        // Assert
        mockMvc.perform(delete("/api/session/abc"))
            .andExpect(status().isBadRequest());
    }
}
