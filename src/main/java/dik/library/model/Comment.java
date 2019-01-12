package dik.library.model;

import javax.persistence.*;

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
}
