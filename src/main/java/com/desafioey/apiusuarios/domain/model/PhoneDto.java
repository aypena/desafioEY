package com.desafioey.apiusuarios.domain.model;

import jakarta.validation.Valid;
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
    @Pattern(regexp = "\\d{9}", message = "Recuerde que este campo es numerico y debe contener 9 dígitos")
    private String number;

    @NotBlank(message = "El código de ciudad es obligatorio")
    @Pattern(regexp = "\\d{1}+", message = "Recuerde que este campo es numerico y debe contener 1 dígito")
    private String cityCode;

    @NotBlank(message = "El código de país es obligatorio")
    @Pattern(regexp = "\\d{2}+", message = "Recuerde que este campo es numerico y debe contener  2 dígitos")
    private String countryCode;
}
