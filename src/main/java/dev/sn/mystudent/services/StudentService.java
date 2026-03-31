package dev.sn.mystudent.services;

import dev.sn.mystudent.dtos.StudentDTO;
import dev.sn.mystudent.models.Student;
import dev.sn.mystudent.repositories.SchoolRecordRepository;
import dev.sn.mystudent.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;
    private SchoolRecordRepository schoolRecordRepository;

    @Transactional
    public StudentDTO getStudentByUsername(String username) {

        StudentDTO studentDto = new StudentDTO();
        Student student = studentRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Student not found: " + username));
        studentDto.setStudent(student);
        studentDto.setAverageGrade(schoolRecordRepository.findAverageGradeByStudentId(student.getId()));
        studentDto.setTotalAbsences(schoolRecordRepository.findTotalHoursAbsencesByStudentId(student.getId()));
        studentDto.setLastGrade(schoolRecordRepository.findLastGradeByStudentId(student.getId()));
        studentDto.setRecords(schoolRecordRepository.findRecordsByStudentId(student.getId()));

        return studentDto;
    }
}
