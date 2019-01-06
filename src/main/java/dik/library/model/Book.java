package dik.library.model;

public class Book {

    private long id;
    private String name;
    private String description;

    private long id_author;
    private Author author;

    private long id_genre;
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

    public long getId_author() {
        return id_author;
    }

    public void setId_author(long id_author) {
        this.id_author = id_author;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public long getId_genre() {
        return id_genre;
    }

    public void setId_genre(long id_genre) {
        this.id_genre = id_genre;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
