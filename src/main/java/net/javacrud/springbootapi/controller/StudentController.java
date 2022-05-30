package net.javacrud.springbootapi.controller;

import net.javacrud.springbootapi.model.Student;
import net.javacrud.springbootapi.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")   //for handling requests
@RestController //allows class to handle request made by client by providing restful services
@RequestMapping("/api/v1/students")
public class StudentController {


    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    //build create student REST API
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    //build get student by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //build update student REST API
    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id,
                                                 @RequestBody Student student) {
        return studentService.getStudentById(id)
                .map(savedStudent -> {

                    savedStudent.setFullName(student.getFullName());
                    savedStudent.setGrade(student.getGrade());
                    savedStudent.setRollNo(student.getRollNo());
                    savedStudent.setDepartment(student.getDepartment());


                    Student updatedStudent = studentService.updateStudent(savedStudent);
                    return new ResponseEntity<>(updatedStudent, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //build delete student REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") long id) {
        studentService.deleteStudent(id);

        return new ResponseEntity<String>("Student deleted successfully!.", HttpStatus.OK);
    }
}
