package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "mascotas")
public class Mascotas {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String raza;


    @Getter
    @Setter
    @JsonIgnoreProperties({"mascotasList"})  //Ignoramos la lista de mascotas por estudiante
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private UserNo userNo;


    @Getter
    @Setter
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //@JsonIgnoreProperties({"userNo"})
    @OneToMany(mappedBy = "mascotas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<citas> citasMascotaList;

    @Getter
    @Setter
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "mascotas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<historial_clinico> historialClinicoMascotaList;

}
