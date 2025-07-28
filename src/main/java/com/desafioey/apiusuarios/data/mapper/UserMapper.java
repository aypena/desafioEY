package com.desafioey.apiusuarios.data.mapper;

import com.desafioey.apiusuarios.data.entity.PhoneEntity;
import com.desafioey.apiusuarios.data.entity.UserEntity;
import com.desafioey.apiusuarios.domain.model.UserDto;
import com.desafioey.apiusuarios.domain.model.UserResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserMapper {

    //Esta Clase Covierte la entrada de cliente  Uderdto a UserEntity para Guardar en DB

    public static UserEntity toEntity(UserDto userDto){
        UserEntity user =  UserEntity.builder()
                .id(UUID.randomUUID())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .create(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastlogin(LocalDateTime.now())
                .active(true).build();


        if (userDto.getPhones() != null){
            List<PhoneEntity> phones = userDto.getPhones().stream().map(
                phoneDto -> {
                    PhoneEntity phone = PhoneEntity.builder()
                            .number(phoneDto.getNumber())
                            .cityCode(phoneDto.getCityCode())
                            .countryCode(phoneDto.getCountryCode())
                            .user(user).build();
                    return phone;
                }
            ).collect(Collectors.toList());
            user.setPhones(phones);

        }
        return user;

    }
    //Esta Clase Covierte la salida de base de datos UserEntity a un objeto respuesta de cliente
    public  static UserResponseDto toResponse(UserEntity userEntity){
        return UserResponseDto.builder()
                .id(userEntity.getId())
                .create(userEntity.getCreate())
                .modified(userEntity.getModified())
                .lastLogin(userEntity.getLastlogin())
                .token(userEntity.getToken())
                .isActive(userEntity.isActive()).build();
    }
}
