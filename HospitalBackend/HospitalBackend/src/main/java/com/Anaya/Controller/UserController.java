package com.Anaya.Controller;

import com.Anaya.DTO.UserRequest;
import com.Anaya.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService authService;

    @Autowired
    public UserController(UserService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequest request) {
        boolean isAuthenticated = authService.validateUser(request.getUsername(), request.getPassword());
        if (isAuthenticated) {
            // Si la autenticación es exitosa, retornar un mensaje de éxito simple
            return ResponseEntity.ok("Login exitoso");
        } else {
            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
        }
    }
}