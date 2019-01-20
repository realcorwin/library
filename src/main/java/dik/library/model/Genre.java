package dik.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="genrename")
    private String genreName;

    public Genre() {
    }

    public Genre(long id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public Genre(String genreName) {
        this.genreName = genreName;
    }

}
