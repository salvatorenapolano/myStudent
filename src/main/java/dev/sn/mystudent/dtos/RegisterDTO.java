package dev.sn.mystudent.dtos;

import dev.sn.mystudent.models.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterDTO {
    @NotEmpty(message = "Il nome è obbligatorio")
    private String name;

    @NotEmpty(message = "Il cognome è obbligatorio")
    private String last;

    @NotEmpty(message = "L'username è obbligatorio")
    private String username;

    @NotNull(message = "Il ruolo è obbligatorio")
    private Role role;

    @Size(min = 4, max = 40, message = "Password deve essere compresa tra 4 e 40 caratteri")
    @NotEmpty(message = "La password è obbligatoria")
    private String password;

    @NotEmpty(message = "Conferma password è obbligatoria")
    private String confirmPassword;

    @NotNull(message = "La data di nascita è obbligatoria")
    private LocalDate dateOfBirth;

    @Pattern(regexp = "^[A-Za-z0-9]{16}$", message = "Il codice fiscale deve essere di 16 caratteri alfanumerici")
    @NotEmpty(message = "Il codice fiscale è obbligatorio")
    private String fiscalCode;

    @NotEmpty
    private String className;

    private String subject;
}
