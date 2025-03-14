package com.openclassrooms.starterjwt.controllers;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.openclassrooms.starterjwt.dto.SessionDto;
import com.openclassrooms.starterjwt.mapper.SessionMapper;
import com.openclassrooms.starterjwt.models.*;
import com.openclassrooms.starterjwt.repository.SessionRepository;
import com.openclassrooms.starterjwt.repository.UserRepository;
import com.openclassrooms.starterjwt.services.SessionService;


@AutoConfigureMockMvc
@SpringBootTest
public class SessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SessionService sessionService;


    @InjectMocks
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @InjectMocks
    private SessionController sessionController;

    @MockBean
    private SessionRepository sessionRepository;

    @Mock
    private UserRepository userRepository;


    @MockBean
    private SessionMapper sessionMapper;


    @Mock
    private Date sessionDate;
    Teacher teacher;
    List<User> users;
    List<Long> usersId;

    List<User> usersList;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        sessionDate = new Date();
        createdAt = LocalDateTime.parse("2025-03-03T23:50:00");
        updatedAt = LocalDateTime.parse("2025-03-04T00:04:30");

        teacher = new Teacher(1L, "DOE", "John", createdAt, updatedAt);

        User userOne = new User("martin.petit@test.com", "PETIT", "Martin", "password123", false); 
        User userTwo = new User("leon.bernard@test.com", "BERNARD", "Léon", "password123", false);

        users = new ArrayList<>();
        users.add(userOne); 
        users.add(userTwo);
        
        usersId = new ArrayList<>();
        usersId.add(userOne.getId()); 
        usersId.add(userTwo.getId()); 
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
    /// Test - Create a session
    public void testCreate() throws Exception {
        // Arrange
        Session session = new Session(1L, "Lorem ipsum", sessionDate, "Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", teacher, users, createdAt, updatedAt);
        SessionDto sessionDTO = new SessionDto(1L, "Lorem ipsum", sessionDate, 1L, "Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", usersId, createdAt, updatedAt);

        ObjectMapper sessionRequestObject = new ObjectMapper();
        sessionRequestObject.registerModule(new JavaTimeModule());
        sessionRequestObject.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String sessionRequestJSON = sessionRequestObject.writeValueAsString(sessionDTO);
        Session sessionMapped = sessionMapper.toEntity(sessionDTO);

        // Act
        when(sessionRepository.save(sessionMapped)).thenReturn(session);
        
        // Assert
        mockMvc.perform(post("/api/session")
            .content(sessionRequestJSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
    

    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Update a not number session ID
    public void testUpdateNotNumber() throws Exception {
        // Arrange
        Session session = new Session(1L, "Lorem ipsum", sessionDate, "Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", teacher, users, createdAt, updatedAt);
        SessionDto sessionDTO = new SessionDto(1L, "Lorem ipsum", sessionDate, 1L, "Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", usersId, createdAt, updatedAt);

        ObjectMapper sessionRequestObject = new ObjectMapper();
        sessionRequestObject.registerModule(new JavaTimeModule());
        sessionRequestObject.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String sessionRequestJSON = sessionRequestObject.writeValueAsString(sessionDTO);
        Session sessionMapped = sessionMapper.toEntity(sessionDTO);

        // Act
        when(sessionRepository.save(sessionMapped)).thenReturn(session);
        
        // Assert
        mockMvc.perform(put("/api/session/abc")
            .content(sessionRequestJSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
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
        doNothing().when(sessionRepository).deleteById(1L);
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


    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Participate at a session
    public void testParticipate() throws Exception {
        // Arrange
        User userOne = new User(1L, "martin.petit@test.com", "PETIT", "Martin", "password123", false, createdAt, updatedAt);
        usersList = new ArrayList<>();
        Session session = new Session(1L, "Lorem ipsum", sessionDate, "Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", teacher, usersList, createdAt, updatedAt);

        // Act
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        when(userRepository.findById(1L)).thenReturn(Optional.of(userOne));
        when(sessionRepository.save(session)).thenReturn(session);
        sessionController.participate("1L", "1L");

        // Assert
        mockMvc.perform(post("/api/session/1/participate/1"))
            .andExpect(status().isOk());
    } 

    

    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Unparticipate at a session
    public void testUnParticipate() throws Exception {
        // Arrange
        User userOne = new User(1L, "martin.petit@test.com", "PETIT", "Martin", "password123", false, createdAt, updatedAt);
        usersList = new ArrayList<>();
        usersList.add(userOne);
        Session session = new Session(1L, "Lorem ipsum", sessionDate, "Suspendisse potenti. Praesent orci ligula, rhoncus ut semper ut, ullamcorper eget neque.", teacher, usersList, createdAt, updatedAt);

        // Act
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        when(userRepository.findById(1L)).thenReturn(Optional.of(userOne));
        when(sessionRepository.save(session)).thenReturn(session);
        sessionController.participate("1L", "1L");
        sessionController.noLongerParticipate("1L", "1L");

        // Assert
        mockMvc.perform(delete("/api/session/1/participate/1"))
            .andExpect(status().isOk());
    } 
}
