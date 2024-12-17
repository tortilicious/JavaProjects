package service;


import model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UsuarioInterface;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioInterface usuarioInterface;

    public List<Usuario> listaUsuarios() {
        return usuarioInterface.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorId(String id) {
        return usuarioInterface.findById(id);
    }

    public Optional<Usuario> buscaUsuarioPorEmail(String correoUsuario) {
        return usuarioInterface.findByCorreoUsuario(correoUsuario);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioInterface.save(usuario);
    }
}
