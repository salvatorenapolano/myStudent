package dev.sn.mystudent.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "teachers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends Person {

    @Column(name = "subject")
    private String teacherSubject;

    @Column(name = "class_names")  // es. "3A,4B,5C"
    private String className;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<SchoolRecord> records = new ArrayList<>();

    // Metodo helper per gestire la relazione bidirezionale
    public void addRecord(SchoolRecord record) {
        records.add(record);
        record.setTeacher(this);
    }

    public void removeRecord(SchoolRecord record) {
        records.remove(record);
        record.setTeacher(null);
    }

    // Helper per ottenere lista classi
    public List<String> getClassList() {
        if (className == null || className.isEmpty()) {
            return List.of();
        }
        return Arrays.asList(className.split(","));
    }

    // Helper per settare lista classi
    public void setClassList(List<String> classes) {
        this.className = String.join(",", classes);
    }

}
