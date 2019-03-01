package dik.library.webflux;

import dik.library.model.Author;
import dik.library.model.Book;
import dik.library.model.Genre;
import dik.library.reactiverepo.BookReactiveRepository;
import dik.library.webfux.BookReactiveRestController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(BookReactiveRestController.class)
public class BookReactiveRestControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private BookReactiveRepository bookReactiveRepository;

    private final String baseUrl = "/reactive/book/";
    private Author author;
    private Genre genre;
    private Book book;
    private List<Book> books;

    @Before
    public void init() {
        author = new Author("Алексей", "Толстой");
        author.setId("1");
        genre = new Genre("Фантастика");
        genre.setId("1");
        book = new Book("Аэлита","Земляне на Марсе", author, genre);
        book.setId("1");
        books = Collections.singletonList(book);
    }

    @Test
    public void bookTest() throws Exception {
        when(bookReactiveRepository.findById("1")).thenReturn(Mono.just(book));
        webTestClient.get().uri(baseUrl + "1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Book.class)
                .contains(book);
        verify(bookReactiveRepository).findById("1");
    }

    @Test
    public void booksTest() throws Exception {
        when(bookReactiveRepository.findAll()).thenReturn(Flux.fromStream(books.stream()));
        webTestClient.get().uri(baseUrl)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Book.class)
                .contains(book);
        verify(bookReactiveRepository).findAll();
    }

    @Test
    public void createBookTest() throws Exception {
        Book newBook = new Book("Гиперболоид инженера Гарина", "Лучи смерти", author, genre);
        when(bookReactiveRepository.save(newBook)).thenReturn(Mono.just(newBook));
        webTestClient.post().uri(baseUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(newBook), Book.class)
                .exchange()
                .expectStatus().isOk();
        verify(bookReactiveRepository).save(newBook);
    }

    @Test
    public void editBookTest() throws Exception {
        Book newBook = new Book("1", "Золотой ключик", "Приключения Буратино", author, genre);
        when(bookReactiveRepository.save(newBook)).thenReturn(Mono.just(newBook));
        webTestClient.put().uri(baseUrl + "1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(newBook), Book.class)
                .exchange()
                .expectStatus().isOk();
        verify(bookReactiveRepository).save(newBook);
    }

    @Test
    public void deleteBookTest() throws Exception {
        when(bookReactiveRepository.deleteByIdWithComments("1")).thenReturn(Mono.empty());
        webTestClient.delete().uri(baseUrl + "1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isNoContent();
        verify(bookReactiveRepository).deleteByIdWithComments("1");
    }
}
