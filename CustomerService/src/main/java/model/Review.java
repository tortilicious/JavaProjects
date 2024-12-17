package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "reviews")
public class Review {

    @Id
    private String id;

    @DBRef
    private Usuario usuario;

    private String titulo;
    private int rating;
    private String comentario;
}
