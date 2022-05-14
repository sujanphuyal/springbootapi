package net.javacrud.springbootapi.repository;

import net.javacrud.springbootapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    //all crud database methods are included here
}
