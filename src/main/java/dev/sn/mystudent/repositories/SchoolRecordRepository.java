package dev.sn.mystudent.repositories;

import dev.sn.mystudent.models.SchoolRecord;
import dev.sn.mystudent.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRecordRepository extends JpaRepository<SchoolRecord, Integer> {

    // VOTI DELLO STUDENTE
    @Query("SELECT ROUND(AVG(r.grade), 2) FROM SchoolRecord r WHERE r.student.id = :studentId AND r.grade > 0")
    Double findAverageGradeByStudentId(@Param("studentId") Integer studentId);

    // LA SOMMA DELLE ORE DI ASSENZA DELLO STUDENTE
    @Query("SELECT SUM(r.hoursAbsent) FROM SchoolRecord r WHERE r.student.id = :studentId")
    Integer findTotalHoursAbsencesByStudentId(@Param("studentId") Integer studentId);

    // L'ULTIMO VOTO DELLO STUDENTE
    @Query("SELECT r.grade FROM SchoolRecord r WHERE r.student.id = :studentId AND r.grade IS NOT NULL ORDER BY r.lessonDate DESC LIMIT 1")
    Double findLastGradeByStudentId (@Param("studentId") Integer studentId);

    // IL REGISTRO DELLO STUDENTE
    @Query("SELECT r FROM SchoolRecord r WHERE r.student.id = :studentId AND r.grade IS NOT NULL")
    List<SchoolRecord> findRecordsByStudentId(Integer studentId);

    @Query("SELECT r.grade FROM SchoolRecord r WHERE r.student.id = :studentId AND r.grade IS NOT NULL")
    List<Double> findGradeByStudentId(Integer studentId);
    

}
