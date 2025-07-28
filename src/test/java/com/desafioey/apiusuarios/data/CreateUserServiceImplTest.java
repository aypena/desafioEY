package com.desafioey.apiusuarios.data;

import com.desafioey.apiusuarios.data.entity.UserEntity;
import com.desafioey.apiusuarios.data.mapper.UserMapper;
import com.desafioey.apiusuarios.data.repositoryimpl.CreateUserServiceImpl;
import com.desafioey.apiusuarios.domain.model.UserDto;
import com.desafioey.apiusuarios.domain.model.UserResponseDto;
import com.desafioey.apiusuarios.domain.repository.UserRepository;
import com.desafioey.apiusuarios.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateUserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateUserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUserSuccessfully() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setName("Alexis");
        userDto.setEmail("alexis@alp.cl");
        userDto.setPassword("ValidPassword123");

        // Simula que no existe un usuario con ese correo
        when(userRepository.findByEmail("alexis@alp.cl")).thenReturn(Optional.empty());

        // Simula codificación de contraseña
        when(passwordEncoder.encode("ValidPassword123")).thenReturn("encryptedPassword");

        // Simula generación de token JWT
        UUID mockId = UUID.fromString("134d1251-f65a-44fe-8cd7-4041657865b8");
        String mockToken = "mocked.jwt.token";
        when(jwtUtil.generateToken(any(UUID.class), eq("alexis@alp.cl"))).thenReturn(mockToken);

        // Simula el guardado del usuario
        UserEntity userEntity = UserMapper.toEntity(userDto);
        userEntity.setId(mockId);
        userEntity.setPassword("encryptedPassword");
        userEntity.setToken(mockToken);
        userEntity.setCreate(LocalDateTime.now());
        userEntity.setModified(LocalDateTime.now());
        userEntity.setLastlogin(LocalDateTime.now());
        userEntity.setActive(true);

        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        // Act
        UserResponseDto response = userService.createUser(userDto);

        // Assert
        assertNotNull(response);
        assertEquals(mockId, UUID.fromString("134d1251-f65a-44fe-8cd7-4041657865b8"));
        assertEquals(mockToken, response.getToken());
        assertTrue(response.isActive());
        assertNotNull(response.getCreate());
        assertNotNull(response.getModified());
        assertNotNull(response.getLastLogin());

        // Verifica que los métodos se llamaron correctamente
        verify(userRepository).findByEmail("alexis@alp.cl");
        verify(passwordEncoder).encode("ValidPassword123");
        verify(jwtUtil).generateToken(any(UUID.class), eq("alexis@alp.cl"));
        verify(userRepository).save(any(UserEntity.class));
    }
}
