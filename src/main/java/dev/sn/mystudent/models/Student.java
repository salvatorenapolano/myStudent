package dev.sn.mystudent.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Person {

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SchoolRecord> records = new ArrayList<>();

    @Column(name = "class_name")  // ← Aggiungi questo campo
    private String className;  // es. "3A"

    // Metodo helper per gestire la relazione bidirezionale
    public void addRecord(SchoolRecord record) {
        records.add(record);
        record.setStudent(this);
    }

    public void removeRecord(SchoolRecord record) {
        records.remove(record);
        record.setStudent(null);
    }
}
