package org.shweta.LibraryManagement.controllers;


import org.shweta.LibraryManagement.dtoRequests.StudentCreateRequest;
import org.shweta.LibraryManagement.modals.Student;
import org.shweta.LibraryManagement.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public Student addStudent(@RequestBody StudentCreateRequest studentCreateRequest){
       return studentService.addStudent(studentCreateRequest);
    }

}
