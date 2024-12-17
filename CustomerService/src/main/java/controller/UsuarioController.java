package controller;

import model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listaUsuarios();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable String id) {
        // Llamamos al servicio para buscar el usuario por su ID
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorId(id);

        // Verificamos si el usuario fue encontrado o no
        if (usuario.isPresent()) {
            // Si el usuario existe, devolvemos una respuesta con código 200 OK y el usuario como cuerpo
            return ResponseEntity.ok(usuario.get());
        } else {
            // Si el usuario no fue encontrado, devolvemos una respuesta con código 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    // Método para buscar un usuario por su correo
    @GetMapping("/usuarios/correo/{correoUsuario}")
    public ResponseEntity<Usuario> getUsuarioByCorreo(@PathVariable String correoUsuario) {
        // Llamamos al servicio para buscar el usuario por su correo
        Optional<Usuario> usuario = usuarioService.buscaUsuarioPorEmail(correoUsuario);

        // Si el usuario es encontrado, retornamos el usuario con HTTP 200 OK
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            // Si no se encuentra el usuario, retornamos HTTP 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario);
        return ResponseEntity
                .status(HttpStatus.CREATED) // Devuelve el código 201
                .body(usuarioGuardado);     // Devuelve el usuario creado
    }
}
