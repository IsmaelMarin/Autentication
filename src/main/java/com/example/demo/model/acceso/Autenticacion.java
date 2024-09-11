package com.example.demo.model.acceso;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "autenticacion")
public class Autenticacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name ="user_id", nullable = false)
    private User user;

    @Getter
    @Setter
    @Column(name = "access_token", nullable = false, unique = true)
    private String accessToken;

    @Getter
    @Setter
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_creacion;

    @Getter
    @Setter
    @Column(name = "fecha_expiracion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_expiracion;

    public Autenticacion() {
    }

    /*
    public Autenticacion(Long usuarioId, String accessToken, Date fechaCreacion, Date fechaExpiracion) {
        this.user = usuarioId;
        this.accessToken = accessToken;
        this.fecha_creacion = fechaCreacion;
        this.fecha_expiracion = fechaExpiracion;
    }

     */
    //Corregido
    public Autenticacion(User user, String accessToken, Date fechaCreacion, Date fechaExpiracion) {
        this.user = user;
        this.accessToken = accessToken;
        this.fecha_creacion = fechaCreacion;
        this.fecha_expiracion = fechaExpiracion;
    }



}
