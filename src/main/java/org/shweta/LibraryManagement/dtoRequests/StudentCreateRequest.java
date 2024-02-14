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


    public Student toStudent() {
       return Student.builder().
               studentName(this.studentName).
                address(this.address).
               phoneNumber(this.phoneNumber).
               email(this.email).
               status(StudentType.ACTIVE).
                build();
    }
}
