package dev.sn.mystudent.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // STUDENT, TEACHER, ADMIN

    @CreationTimestamp // ← Hibernate setta automaticamente alla creazione
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    private LocalDate birthDate;

    @Column(nullable = false)
    private boolean enabled = true;

}
