package dev.sn.mystudent.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "school_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String className;

    private String type;

    private Double grade; // usa Double invece di double per permettere null

    @Column(nullable = false)
    private LocalDate lessonDate;

    private String subject;

    @Column(name = "hours_absent", nullable = false)
    private Integer hoursAbsent; // più chiaro di hourAbsence

    private String notes; // campo opzionale per annotazioni

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY) // spesso un record ha UN insegnante principale
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
