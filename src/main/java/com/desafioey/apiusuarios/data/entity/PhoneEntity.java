package com.desafioey.apiusuarios.data.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "phones")
@Getter
@AllArgsConstructor
@ToString
@Builder
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String cityCode;
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
