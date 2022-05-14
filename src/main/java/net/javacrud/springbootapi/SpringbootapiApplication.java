package net.javacrud.springbootapi;

import net.javacrud.springbootapi.model.Student;
import net.javacrud.springbootapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootapiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootapiApplication.class, args);
	}

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {
		Student student = new Student();
		student.setFullName("Sujan Phuyal");
		student.setGrade("8th Semester");
		student.setRollNo("15731/074");
		student.setDepartment("BSc.CSIT");

		studentRepository.save(student);
	}
}