package dik.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String comment;

    @ManyToOne(targetEntity = Book.class)
    @JoinColumn(name = "id_book")
    private Book book;

    public Comment() {
    }

    public Comment(String comment, Book book) {
        this.comment = comment;
        this.book = book;
    }

}
