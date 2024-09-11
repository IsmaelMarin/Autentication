package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.citas;
import com.example.demo.repository.CitasRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class citasController {

    @Autowired
    private CitasRepository citasRepository;

    // Crear una cita
    @PostMapping
    public ResponseEntity<citas> crearCita(@Valid @RequestBody citas nuevaCita) {
        citas citaGuardada = citasRepository.save(nuevaCita);
        return new ResponseEntity<>(citaGuardada, HttpStatus.CREATED);
    }

    // Obtener una cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<citas> obtenerCitaPorId(@PathVariable Long id) {
        citas cita = citasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada con ID: " + id));
        return ResponseEntity.ok(cita);
    }

    // Obtener todas las citas
    @GetMapping
    public List<citas> obtenerTodasLasCitas() {
        return citasRepository.findAll();
    }

    // Actualizar una cita
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<citas> actualizarCita(@PathVariable Long id, @Valid @RequestBody citas detallesCita) {
        citas cita = citasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada con ID: " + id));

        cita.setFechaHora(detallesCita.getFechaHora());
        cita.setMascotas(detallesCita.getMascotas());
        cita.setClinicas(detallesCita.getClinicas());
        cita.setUserNo2(detallesCita.getUserNo2());

        citas citaActualizada = citasRepository.save(cita);
        return ResponseEntity.ok(citaActualizada);
    }

    // Eliminar una cita
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCita(@PathVariable Long id) {
        citas cita = citasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada con ID: " + id));
        citasRepository.delete(cita);
        return new ResponseEntity<>("Cita eliminada correctamente", HttpStatus.OK);
    }
}