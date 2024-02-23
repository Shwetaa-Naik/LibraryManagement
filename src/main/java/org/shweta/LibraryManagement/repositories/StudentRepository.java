package org.shweta.LibraryManagement.repositories;

import org.shweta.LibraryManagement.modals.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findByPhoneNumber(String phoneNumber);

   // List<Student> findByStudentPhoneNumber(String phoneNumber);

    @Query(value = "select s from Student s where phoneNumber =:phoneNumber")
    List<Student> findByStudentPhoneNumber(String phoneNumber);

    Student findByEmail(String email);

}
