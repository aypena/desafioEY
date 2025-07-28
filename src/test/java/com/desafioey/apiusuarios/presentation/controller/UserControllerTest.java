package com.desafioey.apiusuarios.presentation.controller;

import com.desafioey.apiusuarios.config.NoSecurityConfig;
import com.desafioey.apiusuarios.domain.model.UserDto;
import com.desafioey.apiusuarios.domain.model.UserResponseDto;
import com.desafioey.apiusuarios.domain.usecase.CreateUserUseCase;
import com.desafioey.apiusuarios.presentation.exception.UserAlreadyExistsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Import(NoSecurityConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateUserUseCase createUserUseCase = mock(CreateUserUseCase.class);

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testRegisterUserSuccess() throws Exception {
        // JSON válido que se enviará en la petición
        String userJson = """
        {
            "name": "Alexis",
            "email": "alexis@example.com",
            "password": "Password123",
            "phones": []
        }
        """;

        // Creamos un objeto UserResponseDto simulado para la respuesta
        UserResponseDto responseDto = UserResponseDto.builder()
                .id(UUID.fromString("134d1251-f65a-44fe-8cd7-4041657865b8"))
                .create(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token("token1234567890")
                .isActive(true)
                .build();

        // Mockeamos el comportamiento del caso de uso
        when(createUserUseCase.createUser(any(UserDto.class))).thenReturn(responseDto);

        // Ejecutamos la petición POST y validamos la respuesta
        mockMvc.perform(post("/api/v1/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("134d1251-f65a-44fe-8cd7-4041657865b8"))
                .andExpect(jsonPath("$.token").value("token1234567890"))
                .andExpect(jsonPath("$.active").value(true));
    }



    @Test
    void testRegisterUser_EmailAlreadyExists() throws Exception {
        String validUserJson = """
        {
          "name": "Alexis Pena",
          "email": "alexis@example.com",
          "password": "Password123",
          "phones": []
        }
        """;

        // Mockea que el caso de uso lanza la excepción cuando el correo ya existe
        when(createUserUseCase.createUser(any(UserDto.class)))
                .thenThrow(new UserAlreadyExistsException("El correo ya registrado"));

        mockMvc.perform(post("/api/v1/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validUserJson))
                .andExpect(status().isConflict())  // 400 Bad Request si usas GlobalExceptionHandler
                .andExpect(jsonPath("$.mensaje").value("El correo ya registrado"));
    }

}



