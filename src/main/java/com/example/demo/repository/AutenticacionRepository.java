package com.example.demo.repository;

import com.example.demo.model.acceso.Autenticacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutenticacionRepository extends JpaRepository<Autenticacion,Long> {
    Optional<Autenticacion> findByAccessToken(String accessToken);
}
