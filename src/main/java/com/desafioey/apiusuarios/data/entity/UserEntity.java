package com.desafioey.apiusuarios.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name= "users")
@Getter
@Setter@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime create;
    private LocalDateTime modified;
    private LocalDateTime lastlogin;
    private String token;
    private boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<PhoneEntity> phones;
}
