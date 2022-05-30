package net.javacrud.springbootapi;

import net.javacrud.springbootapi.exception.ResourceNotFoundException;
import net.javacrud.springbootapi.model.Student;
import net.javacrud.springbootapi.repository.StudentRepository;
import net.javacrud.springbootapi.service.StudentServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTests {

    @Mock   //useful when we want to use the mocked object at multiple places
    private StudentRepository studentRepository;

    @InjectMocks    //creates the mock object of the class and injects the mocks that are marked with @Mock annotations
    private StudentServiceImplementation studentService;

    private Student student;

    @BeforeEach
    public void initial(){
        studentRepository = Mockito.mock(StudentRepository.class);
        studentService = new StudentServiceImplementation(studentRepository);
        student = Student.builder()
                .id(1L)
                .fullName("Ramesh Prasain")
                .grade("8th Sem")
                .rollNo("14678/074")
                .department("CSIT")
                .build();
    }

    // JUnit test for createStudent method

    @Test
    public void givenStudentObject_whenSaveStudent_thenReturnStudentObject(){
        // given - precondition
        given(studentRepository.findByrollNo(student.getRollNo()))
                .willReturn(Optional.empty());

        given(studentRepository.save(student)).willReturn(student);

        System.out.println(studentRepository);
        System.out.println(studentService);

        // when -  action or the behaviour that we are going test
        Student savedEmployee = studentService.saveStudent(student);

        System.out.println(savedEmployee);
        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for createStudent method which throws exception
    @Test
    public void givenExisitingrollNo_whenSavedStudent_thenThrowsException(){

        //given -precondition
        given(studentRepository.findByrollNo(student.getRollNo()))
                .willReturn(Optional.of(student));
        System.out.println(studentRepository);
        System.out.println(studentService);

        //when -  action or the behaviour that we are going to test
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> studentService.saveStudent(student));
    }

    //JUnit test for getAllStudents method
    @Test
    public void givenStudentList_whenGetAllStudents_thenReturnStudentsList(){
        //given - precondition

        Student student1 = Student.builder()
                .id(3L)
                .fullName("Tony Stark")
                .grade("Ironman")
                .rollNo("101")
                .department("Avenger")
                .build();

        given(studentRepository.findAll()).willReturn(List.of(student, student1));

        //when -action or the behaviour that we are going to test
        List<Student> studentList = studentService.getAllStudents();

        //then- verify the output
        assertThat(studentList).isNotNull();
        assertThat(studentList.size()).isEqualTo(2);
    }

    //JUnit test for getAllStudents method for negative scenario
    @Test
    public void givenEmptyStudentList_whenGetAllStudents_thenReturnStudentsList() {

        //given - precondition
        Student student1 = Student.builder()
                .id(3L)
                .fullName("Tony Stark")
                .grade("Ironman")
                .rollNo("101")
                .department("Avenger")
                .build();
        given(studentRepository.findAll()).willReturn(Collections.emptyList());

        //when -action or the behaviour that we are going to test
        List<Student> studentList = studentService.getAllStudents();

        //then -verify the output
        assertThat(studentList.isEmpty());
        assertThat(studentList.size()).isEqualTo(0);
    }

    //JUnit test for getStudentById method
    @Test
    public void givenStudentId_whenGetStudentById_thenReturnStudentObject(){
        //given condition
        given(studentRepository.findById(1L)).willReturn(Optional.of(student));

        //when
        Student savedStudent = studentService.getStudentById(student.getId()).get();

        //then
        assertThat(savedStudent).isNotNull();
    }

    //JUnit test for updateStudent method
    @Test
    public void givenStudentObject_whenUpdateStudent_thenReturnUpdatedStudent(){
        //given -precondition
        given(studentRepository.save(student)).willReturn(student);
        student.setFullName("Tony Stark");
        student.setRollNo("101");

        //when - action or the behaviour that we are going to test
        Student updatedStudent = studentService.updateStudent(student);

        //then -verify the output
        assertThat(updatedStudent.getFullName()).isEqualTo("Tony Stark");
        assertThat(updatedStudent.getRollNo()).isEqualTo("101");
    }

    //JUnit test for deleteStudent method
    @Test
    public void givenStudentId_whenDeleteStudent_thenNothing(){
        //given -precondition
        long studentId = 1L;

        willDoNothing().given(studentRepository).deleteById(studentId);

        //when -action or the behaviour that we are going to test
        studentService.deleteStudent(studentId);

        //then -verify the output
        verify(studentRepository, times(1)).deleteById(studentId);
    }
}
