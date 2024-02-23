package org.shweta.LibraryManagement.controllers;

import org.shweta.LibraryManagement.dtoRequests.StudentCreateRequest;
import org.shweta.LibraryManagement.modals.Student;
import org.shweta.LibraryManagement.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/create")
    public Student createAdmin(@RequestBody StudentCreateRequest studentCreateRequest){
        return adminService.createAdmin(studentCreateRequest);
    }


}

