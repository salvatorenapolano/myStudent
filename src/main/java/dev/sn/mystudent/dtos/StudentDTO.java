package dev.sn.mystudent.dtos;

import dev.sn.mystudent.models.SchoolRecord;
import dev.sn.mystudent.models.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Student student;
    private Integer totalAbsences;
    private Double averageGrade;
    private Double lastGrade;
    private List<Double> grades;

    private List<SchoolRecord> records;

}
