package dev.sn.mystudent.controllers;

import dev.sn.mystudent.dtos.RegisterDTO;
import dev.sn.mystudent.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {

    private UserService userService;

    @GetMapping
    public String showRegisterForm(Model model) {
        model.addAttribute("registerDto", new RegisterDTO());
        // serve per la visualizzazione del link del login se la registrazione è andata a buon fine (true)
        model.addAttribute("success", false);
        return "register";
    }

    @PostMapping
    public String register(
            Model model,
            @Valid @ModelAttribute RegisterDTO registerDto,
            BindingResult bindingResult) {

        // Validazione password match
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            bindingResult.addError(new FieldError(
                    "registerDto",
                    "confirmPassword",
                    "Le password non coincidono!"
            ));
        }

        // Validazione username esistente
        if (userService.usernameExists(registerDto.getUsername())) {
            bindingResult.addError(new FieldError(
                    "registerDto",
                    "username",
                    "Username già esistente!"
            ));
        }

        // Validazione fiscal code esistente
        if (userService.fiscalCodeExists(registerDto.getFiscalCode())) {
            bindingResult.addError(new FieldError(
                    "registerDto",
                    "fiscalCode",
                    "Codice fiscale già registrato!"
            ));
        }

        // Se ci sono errori, ritorna al form
        if (bindingResult.hasErrors()) {
            model.addAttribute("success", false);
            return "register";
        }


        try {
            userService.register(registerDto);
            model.addAttribute("success", true);
            model.addAttribute("registerDto", new RegisterDTO()); // Reset form
        } catch (Exception e) {
            model.addAttribute("success", false);
            model.addAttribute("errorMessage", "Errore durante la registrazione: " + e.getMessage());
            return "register";
        }
        return "register";
    }
}
