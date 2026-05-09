package org.example.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotBlank(message = "Логін не може бути порожнім")
    private String username;

    @Email(message = "Невірний формат email")
    @NotBlank(message = "Email не може бути порожнім")
    private String email;

    @Size(min = 6, message = "Пароль мінімум 6 символів")
    private String password;

    @NotBlank(message = "Підтвердіть пароль")
    private String confirmPassword;
}
