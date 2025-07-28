package com.desafioey.apiusuarios.domain.model;



import jakarta.validation.Valid;
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
@Valid
public class UserDto {
    @NotBlank(message = "El nombre de usuario no puede estar en blanco")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "El nombre solo debe contener letras sin acentos ni caracteres especiales")
    private String name;

    @Pattern(
            regexp = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$",
            message = "El correo electrónico no es válido"
    )
    @NotBlank(message = "El correo es obligatorio")
    private String email;

    @NotBlank(message = "La contraseña de usuario no puede estar en blanco")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número")
    private String password;
    @Valid
    private List<PhoneDto> phones;

    public UserDto(String alex, String mail, String password123) {
    }
}
