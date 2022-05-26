package net.javacrud.springbootapi;

import net.javacrud.springbootapi.model.Student;
import net.javacrud.springbootapi.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    //JUnit Test for saveStudent
    @Test
    @Rollback(value = true)
    public void saveStudentTest(){
        Student student= new Student();
        student.setFullName("Devi Phuyal");
        student.setGrade("8th Sem");
        student.setRollNo("15729/074");
        student.setDepartment("BSc. CSIT");
        Student savedStudent = studentRepository.save(student);

        Assertions.assertThat(savedStudent).isNotNull();
        Assertions.assertThat(savedStudent.getId()).isGreaterThan(0);
    }

    //JUnit Test for getStudent
    @Test
    public void getStudentTest(){
        //retrieve employee by its Id and test the same with id
        Student student = studentRepository.findById(1L).get();
        Assertions.assertThat(student.getId()).isEqualTo(1L);
    }

    //JUnit Test for getListOfStudents
    @Test
    public void getListOfStudentsTest(){
        //retrieve list of students from db and test its size
        List<Student> students = studentRepository.findAll();
        Assertions.assertThat(students.size()).isGreaterThan(0);
    }

    //JUnit Test for updateStudent
    @Test
    @Rollback(value = true)
    public void updateStudentTest(){
        //update student operation
        Student student = studentRepository.findById(8L).get();
        student.setDepartment("CSIT");

        Student studentUpdated = studentRepository.save(student);

        Assertions.assertThat(studentUpdated.getDepartment()).isEqualTo("CSIT");
    }

    //JUnit Test for deleteStudent
    @Test
    @Rollback(value = true)
    public void deleteStudentTest(){
        Student student = studentRepository.findById(9L).get();
        studentRepository.delete(student);
        //or simply use
        //studentRepository.deleteById(10L);
    }

}

