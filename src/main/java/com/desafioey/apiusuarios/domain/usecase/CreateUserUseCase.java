package com.desafioey.apiusuarios.domain.usecase;

import com.desafioey.apiusuarios.domain.model.UserDto;
import com.desafioey.apiusuarios.domain.model.UserResponseDto;

public interface CreateUserUseCase {
    UserResponseDto createUser(UserDto user);
}
