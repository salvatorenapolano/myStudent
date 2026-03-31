package dev.sn.mystudent.services;

import dev.sn.mystudent.models.SchoolRecord;
import dev.sn.mystudent.models.Student;
import dev.sn.mystudent.models.Teacher;
import dev.sn.mystudent.repositories.SchoolRecordRepository;
import dev.sn.mystudent.repositories.StudentRepository;
import dev.sn.mystudent.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class SchoolRecordService {

    private final SchoolRecordRepository schoolRecordRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public SchoolRecord createGradeRecord(Integer studentId,
                                          Integer teacherId,
                                          String subject,
                                          Double grade,
                                          LocalDate lessonDate,
                                          String notes,
                                          String type) {

        // ✅ Usa l'ID per trovare lo studente
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Studente ID " + studentId + " non trovato"));

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher ID " + teacherId + " non trovato"));

        // Crea e salva il record
        SchoolRecord record = new SchoolRecord();
        record.setStudent(student);      // ← Relazione con Student
        record.setTeacher(teacher);      // ← Relazione con Teacher
        record.setClassName(student.getClassName());
        record.setSubject(subject);
        record.setGrade(grade);
        record.setLessonDate(lessonDate);
        record.setType(type);
        record.setHoursAbsent(0);
        record.setNotes(notes);

        return schoolRecordRepository.save(record);
    }

    @Transactional
    public SchoolRecord createAbsenceRecord(Integer studentId,
                                            Integer teacherId,
                                            Integer hoursAbsent,
                                            LocalDate lessonDate,
                                            String notes) {
        // Trova student
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Studente non trovato"));

        // Trova teacher
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Insegnante non trovato"));

        // Crea il record
        SchoolRecord record = new SchoolRecord();
        record.setStudent(student);
        record.setTeacher(teacher);
        record.setClassName(student.getClassName());
        record.setSubject(null);
        record.setGrade(null); // Nessun voto
        record.setHoursAbsent(hoursAbsent);
        record.setLessonDate(lessonDate);
        record.setNotes(notes);

        return schoolRecordRepository.save(record);
    }

}
