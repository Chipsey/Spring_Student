package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/add")
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping("/delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/update/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestBody Student student) {
//        System.out.println("StudentId : " + studentId);
//        System.out.println("Student : " +student.getName());
        studentService.updateStudent(studentId, student.getName(), student.getEmail(), student.getDob());
    }
}
