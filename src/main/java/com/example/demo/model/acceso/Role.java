
package com.example.demo.model.acceso;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*  //Cambiastes esta parte
    @Getter
    @Setter
    private String name;

     */

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false, unique = true)
    private RoleName name;  // Cambiamos el tipo a 'RoleName' (enum)

    @Getter
    @Setter
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role(){

    }

    /*
    public Role(String name){
        this.name=name;
    }

     */




    @Override
    public String getAuthority() {
        return "";
    }
}


