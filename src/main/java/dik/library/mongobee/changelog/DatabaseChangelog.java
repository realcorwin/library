package dik.library.mongobee.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.DB;
import dik.library.model.*;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "init", author = "realcorwin")
    public void init(MongoTemplate mongoTemplate) {

        //mongoTemplate.getDb().drop();
        //db.dropDatabase();
        //db.getCollection("comment").drop();

        Book book = null;
        Author author = null;
        Genre genre = null;
        Comment comment = null;

        for(int bookCount = 0; bookCount < 5; bookCount++) {
            book = new Book();
            book.setName("name: " + bookCount);
            book.setDescription("description: " + bookCount);
            for (int valuesCount = 0; valuesCount < 3; valuesCount++) {
                author = new Author();
                author.setFirstName("firstName: " + bookCount + ":" + valuesCount);
                author.setSecondName("secondName: " + bookCount + ":" + valuesCount);
                author = mongoTemplate.save(author, "author");
                book.setAuthor(author);

                genre = new Genre();
                genre.setGenreName("genre: " + bookCount + ":" + valuesCount);
                genre = mongoTemplate.save(genre, "genre");
                book.setGenre(genre);
            }
            book = mongoTemplate.save(book, "book");
            for (int commentCount = 0; commentCount < 2; commentCount++) {
                comment = new Comment();
                comment.setComment("comment: " + bookCount + ":" + commentCount);
                comment.setBook(book);
                comment = mongoTemplate.save(comment, "comment");
            }
        }
    }

    @ChangeSet(order = "002", id = "addUser", author = "realcorwin")
    public void addUser(MongoTemplate mongoTemplate) {

        User user = null;

        user = new User("user", "password");
        user = mongoTemplate.save(user, "user");
    }
}
