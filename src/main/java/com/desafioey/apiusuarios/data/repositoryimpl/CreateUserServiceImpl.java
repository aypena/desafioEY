package com.desafioey.apiusuarios.data.repositoryimpl;

import com.desafioey.apiusuarios.data.entity.UserEntity;
import com.desafioey.apiusuarios.data.mapper.UserMapper;
import com.desafioey.apiusuarios.domain.model.UserDto;
import com.desafioey.apiusuarios.domain.model.UserResponseDto;
import com.desafioey.apiusuarios.domain.repository.UserRepository;
import com.desafioey.apiusuarios.domain.usecase.CreateUserUseCase;
import com.desafioey.apiusuarios.presentation.exception.UserAlreadyExistsException;
import com.desafioey.apiusuarios.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateUserServiceImpl implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createUser(UserDto userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("El correo ya registrado");
        }

        // Mapea UserDto a UserEntity con UseMapper
        UserEntity userEntity = UserMapper.toEntity(userRequest);
        // cifrado de password
        String encryptedPassword = passwordEncoder.encode(userRequest.getPassword());
        userEntity.setPassword(encryptedPassword);

        // Establece fechas y UUID (puedes confiar en UseMapper o actualizar aquí)
        LocalDateTime now = LocalDateTime.now();
        UUID userId = UUID.randomUUID();
        userEntity.setId(UUID.randomUUID()); // Si en UseMapper ya lo pones, puedes quitar esta línea
        userEntity.setCreate(now);
        userEntity.setModified(now);
        userEntity.setLastlogin(now);
        userEntity.setActive(true);


        // Genera el token JWT usando el email
        String token = jwtUtil.generateToken(userId,userEntity.getEmail());
        userEntity.setToken(token);

        // Guarda usuario
        userRepository.save(userEntity);

        // Mapea UserEntity a UserResponseDto para la respuesta
        return UserMapper.toResponse(userEntity);
    }
}
