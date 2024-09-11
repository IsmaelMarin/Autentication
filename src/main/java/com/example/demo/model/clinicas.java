package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name ="clinicas")
public class clinicas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String nombre;

    @Getter
    @Setter
    private String direccion;

    @Getter
    @Setter
    private String telefono;

    @Getter
    @Setter
    private LocalTime horario_apertura;

    @Getter
    @Setter
    private LocalTime horario_cierre;

    @Getter
    @Setter
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "clinicas", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<citas> citasClinicaList;



}
