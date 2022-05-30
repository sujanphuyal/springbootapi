package net.javacrud.springbootapi.repository;

import net.javacrud.springbootapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//all crud database methods are included here

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByrollNo(String rollNo);
}
