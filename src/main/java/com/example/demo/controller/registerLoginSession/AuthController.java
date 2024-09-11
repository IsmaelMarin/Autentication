package com.example.demo.controller.registerLoginSession;


import com.example.demo.model.acceso.Role;
import com.example.demo.model.acceso.RoleName;
import com.example.demo.model.acceso.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;


    }



    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        // El proceso de login se maneja en el filtro JwtAuthenticationFilter
        return credentials;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, Object> payload) {
        String username = (String) payload.get("username");
        String password = (String) payload.get("password");

        // Crear un conjunto de RoleName (el enum)
        Set<RoleName> roleNames = new HashSet<>();

        for (String roleName : (Iterable<String>) payload.get("roles")) {
            try {
                // Convertir el String a RoleName (enum)
                roleNames.add(RoleName.valueOf(roleName));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Error: Rol no válido " + roleName);
            }
        }

        // Verificar si el nombre de usuario ya está en uso
        if (userService.existsByUsername(username)) {
            return ResponseEntity.badRequest().body("Error: El nombre de usuario ya está en uso.");
        }

        // Encriptar la contraseña
        User user = new User(username, passwordEncoder.encode(password));

        // Guardar el usuario con los roles (ahora pasas un Set<RoleName>)
        userService.save(user, roleNames);

        return ResponseEntity.ok("Usuario registrado exitosamente");
    }


}
