package com.example.demo.model.acceso;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Setter
    private String password;

    // Getters y Setters adicionales
    // Relación de muchos a muchos con roles
    @Setter
    @Getter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


    // Si tienes roles o permisos, puedes agregar una lista de authorities
    // @ManyToMany(fetch = FetchType.EAGER)
    // private List<Role> authorities;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //Utilizo Loombok para los métodos getter y setter
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Si tienes roles o permisos, devuélvelos aquí
        return roles;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    /*
    @OneToMany(mappedBy = "user")
    private List<Mascotas> mascotasList;

     */


}

