package dev.sn.mystudent.controllers;

import dev.sn.mystudent.dtos.AbsenceDTO;
import dev.sn.mystudent.dtos.GradeDTO;
import dev.sn.mystudent.dtos.StudentDTO;
import dev.sn.mystudent.models.Teacher;
import dev.sn.mystudent.services.SchoolRecordService;
import dev.sn.mystudent.services.StudentService;
import dev.sn.mystudent.services.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class DashboardController {

    private StudentService studentService;
    private TeacherService teacherService;
    private SchoolRecordService schoolRecordService;

    @GetMapping("/student/dashboard")
    public String studentDashboard(Authentication authentication, Model model) {
        // Ottieni il nome dell'utente loggato
        String username = authentication.getName();
        StudentDTO studentDto = studentService.getStudentByUsername(username);
        model.addAttribute("studentDto", studentDto);

        return "student/dashboard";
    }

    @GetMapping("/teacher/dashboard")
    public String teacherDashboard(Authentication authentication, Model model) {
        // Ottieni l'username dell'utente loggato
        String username = authentication.getName();
        Teacher teacher = teacherService.getTeacherByUsername(username);
        List<StudentDTO> listStudents = teacherService.getStudentsByTeacherId(teacher.getId());


        model.addAttribute("teacher", teacher);
        model.addAttribute("students", listStudents);

        return "teacher/dashboard";
    }

    // POST per inserire voto
    @PostMapping("/teacher/grade")
    public String addGrade(@ModelAttribute GradeDTO gradeDto,
                           Authentication authentication,
                           RedirectAttributes redirectAttributes) {
        try {
            String username = authentication.getName();
            Teacher teacher = teacherService.getTeacherByUsername(username);

            // Crea il record con il voto
            schoolRecordService.createGradeRecord(
                    gradeDto.getStudentId(),
                    teacher.getId(),
                    gradeDto.getSubject(),
                    gradeDto.getGrade(),
                    gradeDto.getLessonDate(),
                    gradeDto.getNotes(),
                    gradeDto.getType()
            );

            redirectAttributes.addFlashAttribute("successMessage", "Voto inserito con successo!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Errore nell'inserimento del voto: " + e.getMessage());
        }

        return "redirect:/teacher/dashboard";
    }

    // POST per inserire assenza
    @PostMapping("/teacher/absence")
    public String addAbsence(@ModelAttribute AbsenceDTO absenceDto,
                             Authentication authentication,
                             RedirectAttributes redirectAttributes) {
        try {
            String username = authentication.getName();
            Teacher teacher = teacherService.getTeacherByUsername(username);

            // Crea il record con l'assenza
            schoolRecordService.createAbsenceRecord(
                    absenceDto.getStudentId(),
                    teacher.getId(),
                    absenceDto.getHoursAbsent(),
                    absenceDto.getLessonDate(),
                    absenceDto.getNotes()
            );

            redirectAttributes.addFlashAttribute("successMessage", "Assenza registrata con successo!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Errore nella registrazione dell'assenza: " + e.getMessage());
        }

        return "redirect:/teacher/dashboard";
    }

}
