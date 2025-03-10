package com.openclassrooms.starterjwt.controllers;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.TeacherRepository;
import com.openclassrooms.starterjwt.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;


@AutoConfigureMockMvc
@SpringBootTest
public class TeacherControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @InjectMocks
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @MockBean
    private TeacherRepository teacherRepository;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        createdAt = LocalDateTime.parse("2025-03-03T23:50:00");
        updatedAt = LocalDateTime.parse("2025-03-04T00:04:30");
    }

    
    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Get the details of a teacher
    public void testFindById() throws Exception {
        // Arrange
        Teacher teacher = new Teacher(1L, "DOE", "John", createdAt, updatedAt);

        // Act
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(teacherRepository.save(teacher)).thenReturn(teacher);

        // Assert
        mockMvc.perform(get("/api/teacher/1"))
            .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Get the details of a not existing teacher
    public void testFindByNotExistId() throws Exception {
        // Arrange
        Teacher teacher = new Teacher(2L, "DOE", "John", createdAt, updatedAt);

        // Act
        when(teacherRepository.findById(2L)).thenReturn(Optional.of(teacher));
        when(teacherRepository.save(teacher)).thenReturn(teacher);

        // Assert
        mockMvc.perform(get("/api/teacher/1"))
            .andExpect(status().isNotFound());
    }
    

    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Get the details of a not number teacher ID
    public void testFindByNotNumberId() throws Exception {
        // Arrange
        Teacher teacher = new Teacher(1L, "DOE", "John", createdAt, updatedAt);

        // Act
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(teacherRepository.save(teacher)).thenReturn(teacher);

        // Assert
        mockMvc.perform(get("/api/teacher/abc"))
            .andExpect(status().isBadRequest());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    /// Test - Get the details of all teachers
    public void testFindAll() throws Exception {
        // Arrange
        LocalDateTime createdAtSecondary = LocalDateTime.parse("2025-04-03T22:30:00");
        LocalDateTime updatedAtSecondary = LocalDateTime.parse("2025-04-04T22:35:30");

        Teacher teacherOne = new Teacher(1L, "DOE", "John", createdAt, updatedAt);
        Teacher teacherTwo = new Teacher(2L, "DUPONT", "Jean", createdAtSecondary, updatedAtSecondary);

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacherOne);
        teachers.add(teacherTwo);
        
        // Act
        when(teacherRepository.findAll()).thenReturn((teachers));
        when(teacherRepository.save(teacherOne)).thenReturn(teacherOne);
        when(teacherRepository.save(teacherTwo)).thenReturn(teacherTwo);

        // Assert
        mockMvc.perform(get("/api/teacher"))
            .andExpect(status().isOk());
    }
}
