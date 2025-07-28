package com.desafioey.apiusuarios.presentation.controller;

import com.desafioey.apiusuarios.domain.model.UserDto;
import com.desafioey.apiusuarios.domain.model.UserResponseDto;
import com.desafioey.apiusuarios.domain.usecase.CreateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
        UserResponseDto createdUser = createUserUseCase.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
