package dik.library;

import dik.library.dao.author.AuthorDaoJpaImpl;
import dik.library.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.h2.tools.Console;

import java.sql.SQLException;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(LibraryApplication.class, args);

		//Console.main(args);

	}

}

