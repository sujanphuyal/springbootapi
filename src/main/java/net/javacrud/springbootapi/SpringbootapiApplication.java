package net.javacrud.springbootapi;

import net.javacrud.springbootapi.model.Student;
import net.javacrud.springbootapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//CommandLineRunner is an interface used to run a code block only once in application's lifetime- after application is initialized.

@SpringBootApplication
public class SpringbootapiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootapiApplication.class, args);
	}

	@Autowired	//Autowire repository interfaces
	private StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {
		Student student = new Student();
		student.setFullName("Sujan Phuyal");
		student.setGrade("8th Semester");
		student.setRollNo("15731/074");
		student.setDepartment("BSc.CSIT");
		studentRepository.save(student);

		Student student2 = new Student();
		student2.setFullName("Rijip Prasain");
		student2.setGrade("8th Semester");
		student2.setRollNo("15730/074");
		student2.setDepartment("BSc.CSIT");
		studentRepository.save(student);
	}
}
