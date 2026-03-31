package dev.sn.mystudent.repositories;

import dev.sn.mystudent.models.Student;
import dev.sn.mystudent.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    boolean existsByFiscalCode(String fiscalCode);

    @Query("SELECT t FROM Teacher t WHERE t.user.username = :username")
    Optional<Teacher> findByUsername(@Param("username") String username);

    @Query("SELECT s FROM Student s WHERE s.className = :className")
    List<Student> findStudentsByClassName(@Param("className") String className);

    @Query("SELECT t.className FROM Teacher t WHERE t.id = :teacherId")
    String findClassNameByTeacherId(@Param("teacherId") Integer teacherId);

}
