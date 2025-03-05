package com.openclassrooms.starterjwt.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.repository.TeacherRepository;
import java.time.LocalDateTime;
import java.util.*;
import org.junit.jupiter.api.*;
import org.mockito.*;


public class TeacherServiceTest {
    @InjectMocks
    private TeacherService teacherService;


    @Mock
    private TeacherRepository teachRepository;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        createdAt = LocalDateTime.parse("2025-03-03T23:50:00");
        updatedAt = LocalDateTime.parse("2025-03-04T00:04:30");
    }

    
    @Test
    /// Test - Find all teachers
    public void testFindAll() {
        // Arrange
        LocalDateTime createdAtSecondary = LocalDateTime.parse("2025-04-03T22:30:00");
        LocalDateTime updatedAtSecondary = LocalDateTime.parse("2025-04-04T22:35:30");

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(1L, "DOE", "John", createdAt, updatedAt));
        teachers.add(new Teacher(2L, "DUPONT", "Jean", createdAtSecondary, updatedAtSecondary));

        // Act
        when(teachRepository.findAll()).thenReturn(teachers);
        List<Teacher> teachersFound = teacherService.findAll();

        // Assert
        assertEquals(teachers, teachersFound);
    }


    @Test
    /// Test - Find teacher by ID
    public void testFindById() {
        // Arrange
        Teacher teacher = new Teacher(1L, "DOE", "John", createdAt, updatedAt); 

        // Act
        when(teachRepository.findById(1L)).thenReturn(Optional.of(teacher));
        Teacher teacherFound = teacherService.findById(1L);
 
        // Assert
        assertEquals("DOE", teacherFound.getLastName());
    }
}
