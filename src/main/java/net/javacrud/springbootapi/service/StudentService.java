package net.javacrud.springbootapi.service;

import net.javacrud.springbootapi.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student saveStudent(Student student);
    List<Student> getAllStudents();
    Optional<Student> getStudentById(long id);
    Student updateStudent(Student updatedStudent);
    void deleteStudent(long id);

}
