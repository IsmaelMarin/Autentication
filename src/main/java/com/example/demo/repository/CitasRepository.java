package com.example.demo.repository;

import com.example.demo.model.citas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitasRepository extends JpaRepository<citas, Long> {
}
