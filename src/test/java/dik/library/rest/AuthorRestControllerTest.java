package dik.library.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dik.library.model.Author;
import dik.library.repository.UserRepository;
import dik.library.security.SecurityConfiguration;
import dik.library.security.UserService;
import dik.library.service.AuthorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@WebMvcTest(AuthorRestController.class)
@WithMockUser(username = "user")
public class AuthorRestControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorServiceMock;

    @MockBean
    private UserService userService;

    private final String baseUrl = "/rest/author/";
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
        when(authorServiceMock.getById("1")).thenReturn(author);
        mockMvc.perform(get(baseUrl + "{id}", author.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(author.getId()))
                .andExpect(jsonPath("$.firstName", is(author.getFirstName())))
                .andExpect(jsonPath("$.secondName", is(author.getSecondName())))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        verify(authorServiceMock).getById("1");
    }

    @Test
    public void authorsTest() throws Exception {
        when(authorServiceMock.getAllAuthor()).thenReturn(authors);
        mockMvc.perform(get(baseUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(author.getId()))
                .andExpect(jsonPath("$[0].firstName", is(author.getFirstName())))
                .andExpect(jsonPath("$[0].secondName", is(author.getSecondName())))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        verify(authorServiceMock).getAllAuthor();
    }

    @Test
    public void createAuthorTest() throws Exception {
        Author newAuthor = new Author("2", "Лев", "Толстой");
        String authorJson = new ObjectMapper().writeValueAsString(newAuthor);
        mockMvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON).content(authorJson))
                .andExpect(status().isOk());
    }

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
    }
}
