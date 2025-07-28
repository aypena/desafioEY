package com.desafioey.apiusuarios.data.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name= "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    private String name;
    private String email;
    private String password;

    private LocalDateTime create;
    private LocalDateTime modified;
    private LocalDateTime lastlogin;
    private String token;

    private boolean active;

    @Version
    private Long version;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<PhoneEntity> phones;
}
