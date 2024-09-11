package com.example.demo.controller;


import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Mascotas;
import com.example.demo.model.historial_clinico;
import com.example.demo.repository.HistorialClinicoRepository;
import com.example.demo.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.historial_clinico;
import com.example.demo.repository.HistorialClinicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial_clinico")
public class historial_clinicoController {

    @Autowired
    private HistorialClinicoRepository historialClinicoRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @PostMapping
    public ResponseEntity<historial_clinico> crearHistorialClinico(@RequestBody historial_clinico historial) {

        Mascotas mascota = mascotaRepository.findById(historial.getMascotas().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con ID: " + historial.getMascotas().getId()));

        // Asociar la mascota con el historial clínico
        historial.setMascotas(mascota);

        // Guardar el historial clínico en la base de datos
        historial_clinico nuevoHistorial = historialClinicoRepository.save(historial);
        return new ResponseEntity<>(nuevoHistorial, HttpStatus.CREATED);
    }
    /*
    // Crear un nuevo historial clínico
    @PostMapping
    public ResponseEntity<historial_clinico> crearHistorialClinico(@RequestBody historial_clinico historial) {
        historial_clinico nuevoHistorial = historialClinicoRepository.save(historial);
        return new ResponseEntity<>(nuevoHistorial, HttpStatus.CREATED);
    }

     */

    // Obtener historial clínico por ID
    @GetMapping("/{id}")
    public ResponseEntity<historial_clinico> obtenerHistorialClinicoPorId(@PathVariable Long id) {
        historial_clinico historial = historialClinicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Historial clínico no encontrado con ID: " + id));
        return ResponseEntity.ok(historial);
    }

    // Obtener todos los historiales clínicos
    @GetMapping
    public ResponseEntity<List<historial_clinico>> obtenerTodosLosHistorialesClinicos() {
        List<historial_clinico> historiales = historialClinicoRepository.findAll();
        return ResponseEntity.ok(historiales);
    }

    // Actualizar un historial clínico por ID
    @PutMapping("/{id}")
    public ResponseEntity<historial_clinico> actualizarHistorialClinico(@PathVariable Long id, @RequestBody historial_clinico detallesHistorial) {
        historial_clinico historial = historialClinicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Historial clínico no encontrado con ID: " + id));

        // Actualizar los campos necesarios
        historial.setDescripcion(detallesHistorial.getDescripcion());

        historial_clinico historialActualizado = historialClinicoRepository.save(historial);
        return ResponseEntity.ok(historialActualizado);
    }

    // Eliminar un historial clínico por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHistorialClinico(@PathVariable Long id) {
        historial_clinico historial = historialClinicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Historial clínico no encontrado con ID: " + id));

        historialClinicoRepository.delete(historial);
        return new ResponseEntity<>("Historial clínico eliminado correctamente", HttpStatus.OK);
    }
}