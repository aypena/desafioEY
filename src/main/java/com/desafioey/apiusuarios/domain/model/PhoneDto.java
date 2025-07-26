package com.desafioey.apiusuarios.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PhoneDto {
    @NotBlank(message = "El número de teléfono es obligatorio")
    @Pattern(regexp = "\\d{9}", message = "El número debe tener entre 6 y 15 dígitos")
    private String number;

    @NotBlank(message = "El código de ciudad es obligatorio")
    @Pattern(regexp = "\\d{1,2}+", message = "El código de ciudad debe contener solo números")
    private String citycode;

    @NotBlank(message = "El código de país es obligatorio")
    @Pattern(regexp = "\\d{1,2}+", message = "El código de país debe contener solo números")
    private String contrycode;
}
