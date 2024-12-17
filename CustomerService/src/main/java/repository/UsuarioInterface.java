package repository;

import model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioInterface extends MongoRepository<Usuario, String> {

    Optional<Usuario> findByCorreoUsuario(String correoUsuario);
}
