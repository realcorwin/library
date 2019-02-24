package dik.library.webflux;

import dik.library.model.Author;
import dik.library.reactiverepo.AuthorReactiveRepository;
import dik.library.webfux.AuthorReactiveRestController;
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
@WebFluxTest(AuthorReactiveRestController.class)
public class AuthorReactiveRestControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AuthorReactiveRepository authorReactiveRepository;

    private final String baseUrl = "/reactive/author/";
    private Author author;
    private List<Author> authors;

    @Before
    public void init() {
        author = new Author("Александр", "Пушкин");
        author.setId("1");
        authors = Collections.singletonList(author);
    }

    @Test
    public void authorTest() throws Exception {
        when(authorReactiveRepository.findById("1")).thenReturn(Mono.just(author));
        webTestClient.get().uri(baseUrl + "1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Author.class)
                .contains(author);
        verify(authorReactiveRepository).findById("1");
    }

    @Test
    public void authorsTest() throws Exception {
        when(authorReactiveRepository.findAll()).thenReturn(Flux.fromStream(authors.stream()));
        webTestClient.get().uri(baseUrl)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Author.class)
                .contains(author);
        verify(authorReactiveRepository).findAll();
    }

    @Test
    public void createAuthorTest() throws Exception {
        Author newAuthor = new Author("Лев", "Толстой");
        //MultiValueMap<String, String> formData = new LinkedMultiValueMap<>(2);
        //formData.add("firstName", newAuthor.getFirstName());
        //formData.add("secondName", newAuthor.getSecondName());
        when(authorReactiveRepository.save(newAuthor)).thenReturn(Mono.just(newAuthor));
        webTestClient.post().uri(baseUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                //.body(BodyInserters.fromPublisher(Mono.just(newAuthor), Author.class))
                .body(Mono.just(newAuthor), Author.class)
                .exchange()
                .expectStatus().isOk();
        verify(authorReactiveRepository).save(newAuthor);
    }

    @Test
    public void editAuthorTest() throws Exception {
        Author newAuthor = new Author("1", "Антон", "Чехов");
        when(authorReactiveRepository.save(newAuthor)).thenReturn(Mono.just(newAuthor));
        webTestClient.put().uri(baseUrl + "1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(newAuthor), Author.class)
                .exchange()
                .expectStatus().isOk();
        verify(authorReactiveRepository).save(newAuthor);
    }

    @Test
    public void deleteAuthorTest() throws Exception {
        when(authorReactiveRepository.deleteById("1")).thenReturn(Mono.empty());
        webTestClient.delete().uri(baseUrl + "1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isNoContent();
        verify(authorReactiveRepository).deleteById("1");
    }
}
