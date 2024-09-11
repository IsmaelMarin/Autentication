package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "usersNo")
public class UserNo {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @Getter
    @Setter
    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;

    @Getter
    @Setter
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //@JsonIgnoreProperties({"userNo"}) //Ignoramos el estudiante de la lista de mascotas
    @OneToMany(mappedBy = "userNo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mascotas> mascotasList;


    @Getter
    @Setter
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //@JsonIgnoreProperties({"userNo"}) //Ignoramos el estudiante de la lista de mascotas
    @OneToMany(mappedBy = "userNo2", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<citas> citasUserList;
}

/*
@Entity
@Table(name = "usersNo")
public class UserNo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String direccion;

    @OneToMany(mappedBy = "userNo")
    private List<Mascotas> mascotasList;


}

 */
