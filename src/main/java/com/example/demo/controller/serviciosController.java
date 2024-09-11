package com.example.demo.controller;


import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.servicios;
import com.example.demo.repository.ServiciosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class serviciosController {

    @Autowired
    private ServiciosRepository serviciosRepository;

    // Crear un servicio
    @PostMapping
    public ResponseEntity<servicios> crearServicio(@Valid @RequestBody servicios nuevoServicio) {
        servicios servicioGuardado = serviciosRepository.save(nuevoServicio);
        return new ResponseEntity<>(servicioGuardado, HttpStatus.CREATED);
    }

    // Obtener un servicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<servicios> obtenerServicioPorId(@PathVariable Long id) {
        servicios servicio = serviciosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado con ID: " + id));
        return ResponseEntity.ok(servicio);
    }

    // Obtener todos los servicios
    @GetMapping
    public List<servicios> obtenerTodosLosServicios() {
        return serviciosRepository.findAll();
    }

    // Actualizar un servicio
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<servicios> actualizarServicio(@PathVariable Long id, @Valid @RequestBody servicios detallesServicio) {
        servicios servicio = serviciosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado con ID: " + id));

        servicio.setNombre(detallesServicio.getNombre());
        servicio.setDescripcion(detallesServicio.getDescripcion());
        servicio.setPrecio(detallesServicio.getPrecio());

        servicios servicioActualizado = serviciosRepository.save(servicio);
        return ResponseEntity.ok(servicioActualizado);
    }

    // Eliminar un servicio
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarServicio(@PathVariable Long id) {
        servicios servicio = serviciosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado con ID: " + id));
        serviciosRepository.delete(servicio);
        return new ResponseEntity<>("Servicio eliminado correctamente", HttpStatus.OK);
    }
}