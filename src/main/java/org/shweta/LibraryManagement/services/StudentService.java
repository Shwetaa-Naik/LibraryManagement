package org.shweta.LibraryManagement.services;

import org.shweta.LibraryManagement.dtoRequests.StudentCreateRequest;
import org.shweta.LibraryManagement.modals.Student;
import org.shweta.LibraryManagement.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public Student addStudent(StudentCreateRequest studentCreateRequest) {
        //check if student already present, if yes then return
        //if not then add new entry row
        Student studentInDB=studentRepository.findByPhoneNumber(studentCreateRequest.getPhoneNumber());
        if(studentInDB == null){
            studentInDB = studentRepository.save(studentCreateRequest.toStudent());
        }
        return studentInDB;
    }
}
