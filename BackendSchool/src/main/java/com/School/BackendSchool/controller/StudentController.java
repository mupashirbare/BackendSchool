package com.School.BackendSchool.controller;

import com.School.BackendSchool.exception.ResourceNotFoundException;
import com.School.BackendSchool.model.Student;
import com.School.BackendSchool.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudent(){
        return this.studentRepository.findAll();
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long studentId)
            throws ResourceNotFoundException {
        Student student= studentRepository.findById(studentId)
                .orElseThrow(()->new ResourceNotFoundException("Student for this id"+ studentId));
        return ResponseEntity.ok().body(student);
    }

    @PostMapping("/new")
    public Student saveStudent(@RequestBody Student student){
        return this.studentRepository.save(student);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Long studentId,
                                                 @Validated @RequestBody Student studentDetail) throws ResourceNotFoundException{
        Student student= studentRepository.findById(studentId)
                .orElseThrow(()->new ResourceNotFoundException("Student for this id"+ studentId));
        student.setFullName(studentDetail.getFullName());
        student.setAge(studentDetail.getAge());
        student.setPhone(studentDetail.getPhone());
        student.setDateOfBirth(studentDetail.getDateOfBirth());
        student.setGender(studentDetail.getGender());
        student.setPassword(studentDetail.getPassword());

        return ResponseEntity.ok(this.studentRepository.save(student));
    }
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Long studentId) throws ResourceNotFoundException {
        Student student= studentRepository.findById(studentId)
                .orElseThrow(()->new ResourceNotFoundException("Student for this id"+ studentId));
        this.studentRepository.delete(student);

        Map<String, Boolean> response =new HashMap<>();
        response.put("delete", Boolean.TRUE);

        return response;
    }
}
