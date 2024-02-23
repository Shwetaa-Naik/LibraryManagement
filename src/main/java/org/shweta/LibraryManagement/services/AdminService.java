package org.shweta.LibraryManagement.services;

import org.shweta.LibraryManagement.dtoRequests.StudentCreateRequest;
import org.shweta.LibraryManagement.modals.Student;
import org.shweta.LibraryManagement.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Value("${admin.authority}")
    private String adminAuthority;
    @Autowired
    StudentRepository studentRepository;

    public Student createAdmin(StudentCreateRequest studentCreateRequest){

        Student studentInDB=studentRepository.findByPhoneNumber(studentCreateRequest.getPhoneNumber());

        if(studentInDB == null){
            studentCreateRequest.setAuthority(adminAuthority);
            studentInDB = studentRepository.save(studentCreateRequest.toStudent());
        }
        return studentInDB;
    }
}
