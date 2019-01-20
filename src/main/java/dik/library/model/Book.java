package dik.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;

    @OneToOne(targetEntity = Author.class)
    @JoinColumn(name = "id_author")
    private Author author;

    @OneToOne(targetEntity = Genre.class)
    @JoinColumn(name = "id_genre")
    private Genre genre;

    public Book() {
    }

    public Book(String name, String description, Author author, Genre genre) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.genre = genre;
    }

    public Book(long id, String name, String description, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.genre = genre;
    }

}
