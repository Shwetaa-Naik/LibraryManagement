package org.shweta.LibraryManagement.dtoRequests;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.shweta.LibraryManagement.enums.StudentType;
import org.shweta.LibraryManagement.modals.Student;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateRequest {


    private String studentName;

    private String address;

    private String phoneNumber;

    private String email;

    private StudentType status;

    private String password;

    private String authority;

    public Student toStudent() {
       return Student.builder().
               studentName(this.studentName).
               address(this.address).
               phoneNumber(this.phoneNumber).
               email(this.email).
               password(this.password).
               authority(this.authority).
               status(StudentType.ACTIVE).
               build();
    }
}
