package dik.library.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dik.library.model.Author;
import dik.library.model.Book;
import dik.library.model.Genre;
import dik.library.security.UserService;
import dik.library.service.AuthorService;
import dik.library.service.BookService;
import dik.library.service.GenreService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookRestController.class)
@WithMockUser(roles = "USER")
public class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorServiceMock;

    @MockBean
    private GenreService genreServiceMock;

    @MockBean
    private BookService bookServiceMock;

    @MockBean
    private UserService userService;

    private final String baseUrl = "/rest/book/";
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
        when(bookServiceMock.getById("1")).thenReturn(book);
        mockMvc.perform(get(baseUrl + "{id}", book.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(book.getId()))
                .andExpect(jsonPath("$.name").value(book.getName()))
                .andExpect(jsonPath("$.description").value(book.getDescription()))
                .andExpect(jsonPath("$.author.id").value(book.getAuthor().getId()))
                .andExpect(jsonPath("$.author.firstName").value(book.getAuthor().getFirstName()))
                .andExpect(jsonPath("$.author.secondName").value(book.getAuthor().getSecondName()))
                .andExpect(jsonPath("$.genre.id").value(book.getGenre().getId()))
                .andExpect(jsonPath("$.genre.genreName").value(book.getGenre().getGenreName()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        verify(bookServiceMock).getById("1");
    }

    @Test
    public void booksTest() throws Exception {
        when(bookServiceMock.getAllBook()).thenReturn(books);
        mockMvc.perform(get(baseUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(book.getId()))
                .andExpect(jsonPath("$[0].name").value(book.getName()))
                .andExpect(jsonPath("$[0].description").value(book.getDescription()))
                .andExpect(jsonPath("$[0].author.id").value(book.getAuthor().getId()))
                .andExpect(jsonPath("$[0].author.firstName").value(book.getAuthor().getFirstName()))
                .andExpect(jsonPath("$[0].author.secondName").value(book.getAuthor().getSecondName()))
                .andExpect(jsonPath("$[0].genre.id").value(book.getGenre().getId()))
                .andExpect(jsonPath("$[0].genre.genreName").value(book.getGenre().getGenreName()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        verify(bookServiceMock).getAllBook();
    }

    @Test
    public void createBookTest() throws Exception {
        Book newBook = new Book("2", "Гиперболоид инженера Гарина", "Лучи смерти", author, genre);
        String bookJson = new ObjectMapper().writeValueAsString(newBook);
        mockMvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON).content(bookJson))
                .andExpect(status().isOk());
    }

    @Test
    public void editBookTest() throws Exception {
        Book newBook = new Book("1", "Золотой ключик", "Приключения Буратино", author, genre);
        String bookJson = new ObjectMapper().writeValueAsString(newBook);
        mockMvc.perform(put(baseUrl + "{id}", book.getId()).contentType(MediaType.APPLICATION_JSON).content(bookJson))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void deleteBookTest() throws Exception {
        mockMvc.perform(delete(baseUrl + "{id}", book.getId()))
                .andExpect(status().isNoContent());
    }
}
