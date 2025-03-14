package com.openclassrooms.starterjwt.models;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    

    @Test
    /// Test - Builder To String
    public void testBuilderToString() {
        // Arrange
        com.openclassrooms.starterjwt.models.Teacher.TeacherBuilder teacher = Teacher.builder().id(1L).lastName("DOE").firstName("John").createdAt(createdAt).updatedAt(updatedAt);

        // Act
        String teacherStr = teacher.toString();

        // Assert
        assertEquals("Teacher.TeacherBuilder(id=1, lastName=DOE, firstName=John, createdAt="+createdAt+", updatedAt="+updatedAt+")", teacherStr);
    }
    

    @Test
    /// Test - Hash
    public void testHashCode() {
        // Arrange
        Teacher teacher = new Teacher(1L, "DOE", "John", createdAt, updatedAt);
        
        // Act
        when(teacherRepository.save(teacher)).thenReturn(teacher);
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        Teacher teacherFound = teacherService.findById(1L);

        // Assert
        assertTrue(teacher.hashCode() == teacherFound.hashCode());
    }
    
    
    @Test
    /// Test - Equals
    public void testEquals() {
        // Arrange
        Teacher teacherOne = new Teacher(1L, "DOE", "John", createdAt, updatedAt);
        Teacher teacherTwo = new Teacher(1L, "DOE", "John", createdAt, updatedAt);

        // Assert
        assertTrue(teacherOne.equals(teacherTwo));
    }
}
