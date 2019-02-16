package dik.library.rest;

import dik.library.controller.AuthorController;
import dik.library.model.Author;
import dik.library.service.AuthorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class AuthorRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorServiceMock;

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
        mockMvc.perform(get("/rest/author/{id}", author.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(author.getId()))
                .andExpect(jsonPath("$.firstName", is(author.getFirstName())))
                .andExpect(jsonPath("$.secondName", is(author.getSecondName())))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        verify(authorServiceMock).getById("1");
    }
}
