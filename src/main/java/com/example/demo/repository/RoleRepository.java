
package com.example.demo.repository;

import com.example.demo.model.acceso.Role;
import com.example.demo.model.acceso.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(RoleName name);  //Hicistes el cambio del argumento por esto String name
}
