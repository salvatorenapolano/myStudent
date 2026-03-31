package dev.sn.mystudent.services;

import dev.sn.mystudent.dtos.StudentDTO;
import dev.sn.mystudent.models.Student;
import dev.sn.mystudent.models.Teacher;
import dev.sn.mystudent.repositories.SchoolRecordRepository;
import dev.sn.mystudent.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final SchoolRecordRepository schoolRecordRepository;

    public Teacher getTeacherByUsername(String username) {
        return teacherRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Teacher not found: " + username));
    }

    @Transactional
    public List<StudentDTO> getStudentsByTeacherId(Integer teacherId) {
        String classOfTeacher = teacherRepository.findClassNameByTeacherId(teacherId);
        List<Student> students = teacherRepository.findStudentsByClassName(classOfTeacher);
        List<StudentDTO> studentDTOList = new ArrayList<>();

            for(Student student : students) {
            StudentDTO studentDto = new StudentDTO();
            studentDto.setStudent(student);
            studentDto.setAverageGrade(schoolRecordRepository.findAverageGradeByStudentId(student.getId()));
            studentDto.setTotalAbsences(schoolRecordRepository.findTotalHoursAbsencesByStudentId(student.getId()));
            studentDto.setLastGrade(schoolRecordRepository.findLastGradeByStudentId(student.getId()));
            studentDto.setGrades(schoolRecordRepository.findGradeByStudentId(student.getId()));
            studentDTOList.add(studentDto);
        }
        return studentDTOList;
    }

}
