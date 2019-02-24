package dik.library.webflux;

import com.fasterxml.jackson.databind.ObjectMapper;
import dik.library.model.Author;
import dik.library.reactiverepo.AuthorReactiveRepository;
import dik.library.rest.AuthorRestController;
import dik.library.service.AuthorService;
import dik.library.webfux.AuthorReactiveRestController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        Author newAuthor = new Author("2","Лев", "Толстой");
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>(2);
        formData.add("firstName", newAuthor.getFirstName());
        formData.add("secondName", newAuthor.getSecondName());
        //when(authorReactiveRepository.save(newAuthor)).thenReturn(Mono.just(newAuthor));
        webTestClient.post().uri(baseUrl)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromFormData(formData))
                //.body(Mono.just(newAuthor), Author.class)
                .exchange()
                .expectStatus().isOk()
                ;
        /*
        String authorJson = new ObjectMapper().writeValueAsString(newAuthor);
        mockMvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON).content(authorJson))
                .andExpect(status().isOk());*/
    }
/*
    @Test
    public void editAuthorTest() throws Exception {
        Author newAuthor = new Author("1", "Антон", "Чехов");
        String authorJson = new ObjectMapper().writeValueAsString(newAuthor);
        mockMvc.perform(put(baseUrl + "{id}", author.getId())
                .contentType(MediaType.APPLICATION_JSON).content(authorJson))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAuthorTest() throws Exception {
        mockMvc.perform(delete(baseUrl + "{id}", author.getId()))
                .andExpect(status().isNoContent());
    }*/
}
