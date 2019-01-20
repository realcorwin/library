package dik.library.model;

import javax.persistence.*;
import java.util.Objects;

@lombok.Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="firstname")
    private String firstName;

    @Column(name="secondname")
    private String secondName;

    public Author() {
    }

    public Author(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public Author(long id, String firstName, String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
    }

}
