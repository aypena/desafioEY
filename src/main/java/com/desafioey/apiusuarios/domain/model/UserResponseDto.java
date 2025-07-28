package com.desafioey.apiusuarios.domain.model;


import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@ToString
@Builder
public class UserResponseDto {
    private UUID id;
    private LocalDateTime create;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;

}
