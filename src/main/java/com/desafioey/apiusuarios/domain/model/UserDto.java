package com.desafioey.apiusuarios.domain.model;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    @NotBlank(message = "El nombre de usuario no puede estar en blanco")
    private String name;

    @NotBlank(message = "El email de usuario no puede estar en blanco")
    @Email(message = "El Correo debe ser Validado")
    private String email;

    @NotBlank(message = "La contraseña de usuario no puede estar en blanco")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número")
    private String password;

    private List<PhoneDto> phones;
}
