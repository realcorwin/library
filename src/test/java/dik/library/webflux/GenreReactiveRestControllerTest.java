package dik.library.webflux;

import dik.library.model.Genre;
import dik.library.reactiverepo.GenreReactiveRepository;
import dik.library.webfux.GenreReactiveRestController;
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
@WebFluxTest(GenreReactiveRestController.class)
public class GenreReactiveRestControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private GenreReactiveRepository genreReactiveRepository;

    private final String baseUrl = "/reactive/genre/";
    private Genre genre;
    private List<Genre> genres;

    @Before
    public void init() {
        genre = new Genre("Фантастика");
        genre.setId("1");
        genres = Collections.singletonList(genre);
    }

    @Test
    public void genreTest() throws Exception {
        when(genreReactiveRepository.findById("1")).thenReturn(Mono.just(genre));
        webTestClient.get().uri(baseUrl + "1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Genre.class)
                .contains(genre);
        verify(genreReactiveRepository).findById("1");
    }

    @Test
    public void genresTest() throws Exception {
        when(genreReactiveRepository.findAll()).thenReturn(Flux.fromStream(genres.stream()));
        webTestClient.get().uri(baseUrl)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Genre.class)
                .contains(genre);
        verify(genreReactiveRepository).findAll();
    }

    @Test
    public void createGenreTest() throws Exception {
        Genre newGenre = new Genre("Детектив");
        when(genreReactiveRepository.save(newGenre)).thenReturn(Mono.just(newGenre));
        webTestClient.post().uri(baseUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(newGenre), Genre.class)
                .exchange()
                .expectStatus().isOk();
        verify(genreReactiveRepository).save(newGenre);
    }

    @Test
    public void editGenreTest() throws Exception {
        Genre newGenre = new Genre("1", "Триллер");
        when(genreReactiveRepository.save(newGenre)).thenReturn(Mono.just(newGenre));
        webTestClient.put().uri(baseUrl + "1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(newGenre), Genre.class)
                .exchange()
                .expectStatus().isOk();
        verify(genreReactiveRepository).save(newGenre);
    }

    @Test
    public void deleteGenreTest() throws Exception {
        when(genreReactiveRepository.deleteById("1")).thenReturn(Mono.empty());
        webTestClient.delete().uri(baseUrl + "1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isNoContent();
        verify(genreReactiveRepository).deleteById("1");
    }
}
