package com.openclassrooms.starterjwt.models;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import com.openclassrooms.starterjwt.repository.TeacherRepository;
import com.openclassrooms.starterjwt.services.TeacherService;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.mockito.*;


public class TeacherTest {
    @InjectMocks
    private TeacherService teacherService;

    @Mock
    private TeacherRepository teacherRepository;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        
        createdAt = LocalDateTime.parse("2025-03-03T23:50:00");
        updatedAt = LocalDateTime.parse("2025-03-04T00:04:30");
    }


    @Test
    /// Test - Setter of the teacher
    public void testSetter() {
        // Arrange
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setLastName("DOE");
        teacher.setFirstName("John");
        teacher.setCreatedAt(createdAt);
        teacher.setUpdatedAt(updatedAt);

        // Act
        when(teacherRepository.save(teacher)).thenReturn(teacher);

        // Assert
        assertEquals(1L, teacher.getId());
        assertEquals("DOE", teacher.getLastName());
        assertEquals("John", teacher.getFirstName());
        assertEquals(createdAt, teacher.getCreatedAt());
        assertEquals(updatedAt, teacher.getUpdatedAt());
    }


    @Test
    /// Test - Getter of the teacher
    public void testGetter() {
        // Arrange
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setLastName("DOE");
        teacher.setFirstName("John");
        teacher.setCreatedAt(createdAt);
        teacher.setUpdatedAt(updatedAt);

        // Act
        when(teacherRepository.save(teacher)).thenReturn(teacher);
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        Teacher teacherFound = teacherService.findById(1L);

        // Assert
        assertEquals(teacher.getId(), teacherFound.getId());
        assertEquals(teacher.getLastName(), teacherFound.getLastName());
        assertEquals(teacher.getFirstName(), teacherFound.getFirstName());
        assertEquals(teacher.getCreatedAt(), teacherFound.getCreatedAt());
        assertEquals(teacher.getUpdatedAt(), teacherFound.getUpdatedAt());
    }
    

    @Test
    /// Test - Builder of the teacher
    public void testBuilderTeacher() {
        // Arrange
        Teacher teacher = Teacher.builder().id(1L).lastName("DOE").firstName("John").createdAt(createdAt).updatedAt(updatedAt).build();

        // Act
        when(teacherRepository.save(teacher)).thenReturn(teacher);

        // Assert
        assertEquals(1L, teacher.getId());
        assertEquals("DOE", teacher.getLastName());
        assertEquals("John", teacher.getFirstName());
        assertEquals(createdAt, teacher.getCreatedAt());
        assertEquals(updatedAt, teacher.getUpdatedAt());
    }

        

    @Test
    /// Test - To String
    public void testToString() {
        // Arrange
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setLastName("DOE");
        teacher.setFirstName("John");
        teacher.setCreatedAt(createdAt);
        teacher.setUpdatedAt(updatedAt);

        // Act
        when(teacherRepository.save(teacher)).thenReturn(teacher);

        // Assert
        assertEquals("Teacher(id=1, lastName=DOE, firstName=John, createdAt="+createdAt+", updatedAt="+updatedAt+")", teacher.toString());
    }
}
