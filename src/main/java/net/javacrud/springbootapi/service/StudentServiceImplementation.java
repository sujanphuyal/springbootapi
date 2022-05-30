package net.javacrud.springbootapi.service;

import net.javacrud.springbootapi.exception.ResourceNotFoundException;
import net.javacrud.springbootapi.model.Student;
import net.javacrud.springbootapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImplementation implements StudentService{
    private StudentRepository studentRepository;

    public StudentServiceImplementation(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {

        Optional<Student> createStudent = studentRepository.findByrollNo(student.getRollNo());
        if(createStudent.isPresent()){
            throw new ResourceNotFoundException("Student already exist with given Roll No:" + student.getRollNo());
        }
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student updateStudent(Student updatedStudent) {
        return studentRepository.save(updatedStudent);
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }
}
