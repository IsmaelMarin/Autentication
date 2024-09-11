package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Mascotas;
import com.example.demo.model.UserNo;
import com.example.demo.repository.MascotaRepository;
import com.example.demo.repository.UserNoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    private MascotaRepository mascotaRepository;
    @Autowired
    private UserNoRepository userNoRepository;


    @PostMapping
    public ResponseEntity<Mascotas> crearMacota(@Valid @RequestBody Mascotas mascotas) {
        //Mascotas nuevaMascota = mascotaRepository.save(mascotas);
        //return new ResponseEntity<>(nuevaMascota, HttpStatus.CREATED);

        //Cargamos el objeto User cuando hay relaciones
        UserNo userNo = userNoRepository.findById(mascotas.getUserNo().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + mascotas.getUserNo().getId()));

        //Asignamos el usuario a la mascota
        mascotas.setUserNo(userNo);

        Mascotas nuevaMascota = mascotaRepository.save(mascotas);

        return new ResponseEntity<>(nuevaMascota,HttpStatus.CREATED);
    }

    @GetMapping("/{mascotaId}")
    public ResponseEntity<Mascotas> obtenerMascotaId(@PathVariable Long mascotaId) {
        Mascotas mascotas = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con ID: " + mascotaId));
        return ResponseEntity.ok(mascotas);
    }

    @DeleteMapping("/eliminar/{mascotaId}")
    public ResponseEntity<String> eliminarMascotaId(@PathVariable Long mascotaId) {
        Mascotas mascotas = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrado con ID: " + mascotaId));
        mascotaRepository.deleteById(mascotaId);
        return new ResponseEntity<>("Mascota eliminada correctamente", HttpStatus.OK);
    }

    @PutMapping("/actualizar/{mascotaId}")
    public ResponseEntity<Mascotas> actualizarUsuarioId(@PathVariable Long mascotaId, @Valid @RequestBody Mascotas detallesMascota) {
        Mascotas mascotas = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrado con ID: " + mascotaId));

        // Actualizar los campos necesarios
        mascotas.setName(detallesMascota.getName());
        mascotas.setRaza(detallesMascota.getRaza());

        // Guardar los cambios
        Mascotas mascotaActualizado = mascotaRepository.save(mascotas);

        return ResponseEntity.ok(mascotaActualizado);
    }

}
