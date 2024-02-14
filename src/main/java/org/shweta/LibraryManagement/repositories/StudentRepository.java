package org.shweta.LibraryManagement.repositories;

import org.shweta.LibraryManagement.modals.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findByPhoneNumber(String phoneNumber);
}
