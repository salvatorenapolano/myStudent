package dev.sn.mystudent.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GradeDTO {
    private Integer studentId;
    private String subject;
    private Double grade;
    private LocalDate lessonDate;
    private String notes;
    private String type;
}
