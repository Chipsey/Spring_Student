package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()) {
            throw new IllegalStateException("Email is Already Taken!");
        }
        int generatedAge = Period.between(student.getDob(), LocalDate.now()).getYears();
        student.setAge(generatedAge);
        System.out.println("Age :" + student.getAge());
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        Boolean exists = studentRepository.existsById(studentId);
        if(!exists) {
            throw new IllegalStateException("Student with id: " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String studentName, String studentEmail, LocalDate dob) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id: " + studentId + " does not exist"));

        if(studentName != null &&
                studentName.length() > 0 &&
                !Objects.equals(student.getName(), studentName)) {
            student.setName(studentName);
        }

        if(dob != null &&
                !Objects.equals(student.getDob(), dob)) {
            student.setDob(dob);
        }

        if(studentEmail != null && studentEmail.length() > 0 && !Objects.equals(student.getEmail(), studentEmail)){
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail(studentEmail);

            if(studentOptional.isPresent()) {
                throw new IllegalArgumentException("Email is already in use!");
            }
            student.setEmail(studentEmail);
        }
    }
}
