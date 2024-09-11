package com.example.demo.controller;


import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UserNo;
import com.example.demo.repository.UserNoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/usersNo")
public class UserNoController {

    @Autowired
    private UserNoRepository userNoRepository;

    @PostMapping
    public ResponseEntity<UserNo> crearUserNo(@Valid @RequestBody UserNo userNo) {
        UserNo nuevoUsuario = userNoRepository.save(userNo);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<UserNo> obtenerUsuarioId(@PathVariable Long usuarioId) {
        UserNo userNo = userNoRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + usuarioId));
        return ResponseEntity.ok(userNo);
    }

    @DeleteMapping("/eliminar/{usuarioId}")
    public ResponseEntity<String> eliminarUsuarioId(@PathVariable Long usuarioId) {
        UserNo userNo = userNoRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + usuarioId));
        userNoRepository.deleteById(usuarioId);
        return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.OK);
    }

    @PutMapping("/actualizar/{usuarioId}")
    public ResponseEntity<UserNo> actualizarUsuarioId(@PathVariable Long usuarioId, @Valid @RequestBody UserNo detallesUsuarioNo) {
        UserNo userNo = userNoRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + usuarioId));

        // Actualizar los campos necesarios
        userNo.setName(detallesUsuarioNo.getName());
        userNo.setDireccion(detallesUsuarioNo.getDireccion());

        // Guardar los cambios
        UserNo usuarioActualizado = userNoRepository.save(userNo);

        return ResponseEntity.ok(usuarioActualizado);
    }
}


/*
@RestController
@RequestMapping("/api/usersNo")
public class UserNoController {

    @Autowired
    private UserNoRepository userNoRepository;

    @PostMapping
    public UserNo crearUserNo(@RequestBody UserNo userNo){
        return userNoRepository.save(userNo);
    }
    @GetMapping("/{usuarioId}")
    public UserNo obtenerUsuarioId(@PathVariable Long usuarioId){
        UserNo userNo = userNoRepository.findById(usuarioId)
                .orElseThrow( () -> new RuntimeException("Estudiante no encontrado con ID: " + usuarioId));

        return userNo;
    }

    @DeleteMapping("/eliminar/{usuarioId}")
    public ResponseEntity<?> eliminarUsuarioId(@PathVariable Long usuarioId){
        UserNo userNo = userNoRepository.findById(usuarioId)
                .orElseThrow( () -> new RuntimeException("Estudiante no encontrado con ID: " + usuarioId));

        if(userNo != null){
            userNoRepository.deleteById(usuarioId);
            return new ResponseEntity<>("Usuario eliminado correctamente",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No se puede eliminar el usuario", HttpStatus.CONFLICT);
        }

    }

    @PutMapping("/actualizar/{usuarioId}")
    public ResponseEntity<UserNo> actualizarUsuarioId(@PathVariable Long usuarioId, @RequestBody UserNo detallesUsuarioNo) {
        UserNo userNo = userNoRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));

        // Actualizar los campos necesarios
        userNo.setName(detallesUsuarioNo.getName());
        userNo.setDireccion(detallesUsuarioNo.getDireccion());
        // Agrega más campos que necesites actualizar

        // Guardar los cambios
        UserNo usuarioActualizado = userNoRepository.save(userNo);

        return ResponseEntity.ok(usuarioActualizado);
    }


}


 */