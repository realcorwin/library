package dik.library.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Book {

    @Id
    private String id;
    private String name;
    private String description;

    @DBRef
    private Author author;

    @DBRef
    private Genre genre;

    public Book() {
    }

    public Book(String name, String description, Author author, Genre genre) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.genre = genre;
    }

    public Book(String id, String name, String description, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.genre = genre;
    }

}
