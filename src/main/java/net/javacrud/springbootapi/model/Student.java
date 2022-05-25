package net.javacrud.springbootapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter //returns the value
@Setter //sets or updates the value
@NoArgsConstructor  //generated a default constructor with no parameters
@AllArgsConstructor //generates a constructor requiring an argument for every field in the annotated class
@Entity //specifies that the particular class is an entity
@Table(name="students") //db table to provide details
public class Student {

    @Id //to make given id as PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="full_name")
    private String fullName;

    @Column(name="grade")
    private String grade;

    @Column(name="roll_no")
    private String rollNo;

    @Column(name="department")
    private String department;

}
