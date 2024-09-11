package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servicios")
public class servicios {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Getter
    @Setter
    private String nombre;

    @Getter
    @Setter
    private String descripcion;

    @Column(precision = 10, scale = 3)
    @Getter
    @Setter
    private BigDecimal precio;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "servicios")
    private List<citas> citasServicioList = new ArrayList<>();



}
