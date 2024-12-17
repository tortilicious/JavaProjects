package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;

    @JsonProperty("nombre_usuario")  // Para garantizar que el JSON tiene el nombre "nombre_usuario"
    private String nombreUsuario;

    @JsonProperty("correo_usuario")  // Para garantizar que el JSON tiene el nombre "correo_usuario"
    private String correoUsuario;

    @JsonProperty("password")  // Para garantizar que el JSON tiene el nombre "password"
    private String passUsuario;
}
