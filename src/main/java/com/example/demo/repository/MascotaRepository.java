package com.example.demo.repository;

import com.example.demo.model.Mascotas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascotas,Long> {
}
