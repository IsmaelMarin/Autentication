package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.clinicas;
import com.example.demo.repository.ClinicasRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinicas")
public class clinicasController {

    @Autowired
    private ClinicasRepository clinicasRepository;

    // Crear una clínica
    @PostMapping
    public ResponseEntity<clinicas> crearClinica(@Valid @RequestBody clinicas nuevaClinica) {
        clinicas clinicaGuardada = clinicasRepository.save(nuevaClinica);
        return new ResponseEntity<>(clinicaGuardada, HttpStatus.CREATED);
    }

    // Obtener una clínica por ID
    @GetMapping("/{id}")
    public ResponseEntity<clinicas> obtenerClinicaPorId(@PathVariable Long id) {
        clinicas clinica = clinicasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clínica no encontrada con ID: " + id));
        return ResponseEntity.ok(clinica);
    }

    // Obtener todas las clínicas
    @GetMapping
    public List<clinicas> obtenerTodasLasClinicas() {
        return clinicasRepository.findAll();
    }

    // Actualizar una clínica
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<clinicas> actualizarClinica(@PathVariable Long id, @Valid @RequestBody clinicas detallesClinica) {
        clinicas clinica = clinicasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clínica no encontrada con ID: " + id));

        clinica.setNombre(detallesClinica.getNombre());
        clinica.setDireccion(detallesClinica.getDireccion());
        clinica.setTelefono(detallesClinica.getTelefono());
        clinica.setHorario_apertura(detallesClinica.getHorario_apertura());
        clinica.setHorario_cierre(detallesClinica.getHorario_cierre());

        clinicas clinicaActualizada = clinicasRepository.save(clinica);
        return ResponseEntity.ok(clinicaActualizada);
    }

    // Eliminar una clínica
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarClinica(@PathVariable Long id) {
        clinicas clinica = clinicasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clínica no encontrada con ID: " + id));
        clinicasRepository.delete(clinica);
        return new ResponseEntity<>("Clínica eliminada correctamente", HttpStatus.OK);
    }
}