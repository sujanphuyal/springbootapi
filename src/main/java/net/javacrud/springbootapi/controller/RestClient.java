package net.javacrud.springbootapi.controller;

import net.javacrud.springbootapi.model.Student;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    //declare end point urls
    private static final String GET_ALL_STUDENTS_API = "http://localhost:8080/api/v1/students";
    private static final String GET_STUDENT_BY_ID_API = "http://localhost:8080/api/v1/students/{id}";
    private static final String CREATE_STUDENT_API = "http://localhost:8080/api/v1/students";
    private static final String UPDATE_STUDENT_API = "http://localhost:8080/api/v1/students/{id}";
    private static final String DELETE_STUDENT_API = "http://localhost:8080/api/v1/students/{id}";

   //Spring RestTemplate to consume restful services
    static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {

        callGetAllStudentsAPI();
        callGetStudentByIdAPI();
        callCreateStudentAPI();
        callUpdateStudentAPI();
        callDeleteStudentAPI();

    }

    private static void callGetAllStudentsAPI(){
        HttpHeaders headers = new HttpHeaders();
        //client is expecting JSON in a response
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
       ResponseEntity<String> result = restTemplate.exchange(GET_ALL_STUDENTS_API,
                                                                HttpMethod.GET, entity, String.class);
        System.out.println(result);
    }

    private static void callGetStudentByIdAPI(){
        Map<String, Integer> param = new HashMap<>();
        param.put("id",1);
        Student student = restTemplate.getForObject(GET_STUDENT_BY_ID_API,
                                                    Student.class, param);

        System.out.println(student.getFullName());
        System.out.println(student.getRollNo());
        System.out.println(student.getGrade());
        System.out.println(student.getDepartment());
    }
    private static void callCreateStudentAPI(){
        Student student = new Student('?',"IS Khulal","6th Sem","15650/076","Public Admin");
        ResponseEntity<Student> student2 = restTemplate.postForEntity(CREATE_STUDENT_API,
                                                                    student,Student.class);
        System.out.println(student2.getBody());
    }

    private static void callUpdateStudentAPI(){
        Map<String, Integer> param = new HashMap<>();
        param.put("id",8);
        Student updateStudent = new Student('?',"Rijip Prasain","8th Sem","15760","CSIT");
        restTemplate.put(UPDATE_STUDENT_API, updateStudent, param);

    }

    private static void callDeleteStudentAPI(){
        Map<String, Integer> param = new HashMap<>();
        param.put("id",14);
        restTemplate.delete(DELETE_STUDENT_API, param);
    }

}
