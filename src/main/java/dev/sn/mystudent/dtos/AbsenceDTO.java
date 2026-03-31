package dev.sn.mystudent.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AbsenceDTO {
    private Integer studentId;
    private LocalDate lessonDate;
    private Integer hoursAbsent;
    private String notes;
}