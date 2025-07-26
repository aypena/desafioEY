package com.desafioey.apiusuarios.data.mapper;

import com.desafioey.apiusuarios.data.entity.PhoneEntity;
import com.desafioey.apiusuarios.data.entity.UserEntity;
import com.desafioey.apiusuarios.domain.model.UserDto;
import com.desafioey.apiusuarios.domain.model.UserResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UseMapper {

    //Esta Clase Covierte la entrada de cliente  Uderdto a UserEntity para Guardar en DB

    public static UserEntity toEntity(UserDto userDto){
        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCreate(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastlogin(LocalDateTime.now());
        user.setActive(true);

        if (userDto.getPhones() != null){
            List<PhoneEntity> phones = userDto.getPhones().stream().map(
                phoneDto -> {
                    PhoneEntity phone = new PhoneEntity();
                    phone.setNumber(phoneDto.getNumber());
                    phone.setCityCode(phoneDto.getCitycode());
                    phone.setContryCode(phoneDto.getContrycode());
                    phone.setUser(user);
                    return phone;
                }
            ).collect(Collectors.toList());
            user.setPhones(phones);

        }

        return user;

    }
    //Esta Clase Covierte la salida de base de datos UserEntity a un objeto respuesta de cliente
    public  static UserResponseDto toResponse(UserEntity userEntity){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(userEntity.getId());
        userResponseDto.setCreate(userEntity.getCreate());
        userResponseDto.setModified(userEntity.getModified());
        userResponseDto.setLastLogin(userEntity.getLastlogin());
        userResponseDto.setItokend(userEntity.getToken());
        userResponseDto.setActive(userEntity.isActive());

        return userResponseDto;
    }
}
