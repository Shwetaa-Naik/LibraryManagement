package org.shweta.LibraryManagement.services;

import org.shweta.LibraryManagement.dtoRequests.StudentCreateRequest;
import org.shweta.LibraryManagement.enums.OperatorType;
import org.shweta.LibraryManagement.enums.StudentFilterType;
import org.shweta.LibraryManagement.modals.Student;
import org.shweta.LibraryManagement.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


    public List<Student> getStudents(StudentFilterType filterby, OperatorType operator, String value) {
        switch (operator){
            case EQUALS:
                switch (filterby){
                    case CONTACT_NUMBER:
                        List<Student> studentList=studentRepository.findByStudentPhoneNumber(value);
                    return studentList;
                }

            default:return new ArrayList<>();
        }

    }
}
