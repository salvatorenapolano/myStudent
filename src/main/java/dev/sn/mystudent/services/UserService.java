package dev.sn.mystudent.services;

import dev.sn.mystudent.dtos.RegisterDTO;
import dev.sn.mystudent.models.Student;
import dev.sn.mystudent.models.Teacher;
import dev.sn.mystudent.models.User;
import dev.sn.mystudent.repositories.StudentRepository;
import dev.sn.mystudent.repositories.TeacherRepository;
import dev.sn.mystudent.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;


    /**
     * Verifica se l'username esiste già
     */
    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    /**
     * Verifica se il codice fiscale esiste già
     */

    public boolean fiscalCodeExists(String fiscalCode) {
        return studentRepository.existsByFiscalCode(fiscalCode)
                || teacherRepository.existsByFiscalCode(fiscalCode);
    }
    /**
     * Registra un nuovo utente (Student o Teacher)
     */
    // @Transactional gestisce le transazioni del database.
    // Garantisce che tutte le operazioni dentro il metodo vengano eseguite come un'unica operazione atomica.
    // Atomico: tutto o niente (o tutte le operazioni hanno successo, o nessuna viene applicata).
    @Transactional
    public void register(RegisterDTO registerDto) {

        // Creazione User
        User user = User.builder()
                .username(registerDto.getUsername())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .role(registerDto.getRole())
                .birthDate(registerDto.getDateOfBirth())
                .enabled(true)
                .build();

        // Salvataggio User
        user = userRepository.save(user);

        // Crea Student o Teacher in base al ruolo
        switch (registerDto.getRole()) {
            case STUDENT -> createStudent(registerDto, user);
            case TEACHER -> createTeacher(registerDto, user);
            case ADMIN -> {
            }
        }
    }

    /**
     * Creazione profilo Student
     */

    private void createStudent(RegisterDTO dto, User user) {

        Student student = new Student();
        student.setFirstName(dto.getName());
        student.setLastName(dto.getLast());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setFiscalCode(dto.getFiscalCode());
        student.setClassName(dto.getClassName());
        student.setUser(user);

        studentRepository.save(student);
    }

    /**
     * Crea profilo Teacher
     */
    private void createTeacher(RegisterDTO dto, User user) {

        Teacher teacher = new Teacher();
        teacher.setFirstName(dto.getName());
        teacher.setLastName(dto.getLast());
        teacher.setDateOfBirth(dto.getDateOfBirth());
        teacher.setFiscalCode(dto.getFiscalCode());
        teacher.setTeacherSubject(dto.getSubject());
        teacher.setClassName(dto.getClassName());
        teacher.setUser(user);

        teacherRepository.save(teacher);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
    }




}
