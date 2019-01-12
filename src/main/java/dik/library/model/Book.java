package dik.library.model;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;

    @MapsId
    @OneToOne(targetEntity = Author.class)
    @JoinColumn(name = "id_author")
    private Author author;

    @MapsId
    @OneToOne(targetEntity = Genre.class)
    @JoinColumn(name = "id_genre")
    private Genre genre;

    public Book(long id, String name, String description, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
