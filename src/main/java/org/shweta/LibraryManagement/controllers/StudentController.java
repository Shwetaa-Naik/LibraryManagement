package org.shweta.LibraryManagement.controllers;


import lombok.Getter;
import org.shweta.LibraryManagement.dtoRequests.StudentCreateRequest;
import org.shweta.LibraryManagement.enums.OperatorType;
import org.shweta.LibraryManagement.enums.StudentFilterType;
import org.shweta.LibraryManagement.modals.Student;
import org.shweta.LibraryManagement.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public Student addStudent(@RequestBody StudentCreateRequest studentCreateRequest){
       return studentService.addStudent(studentCreateRequest);
    }

    @GetMapping("/getStudents")
    public List<Student> getStudents(@RequestParam("studentFilterBy") StudentFilterType filterby,
                                     @RequestParam("operand")OperatorType operator,
                                     @RequestParam("value") String value){
        return  studentService.getStudents(filterby,operator,value);
    }
}
