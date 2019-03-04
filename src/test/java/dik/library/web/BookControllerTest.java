package dik.library.web;

import dik.library.controller.BookController;
import dik.library.model.Book;
import dik.library.security.UserService;
import dik.library.service.AuthorService;
import dik.library.service.BookService;
import dik.library.service.GenreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookServiceMock;

    @MockBean
    private AuthorService authorServiceMock;

    @MockBean
    private GenreService genreServiceMock;

    @MockBean
    private UserService userService;

    @Test
    public void createBookTest() throws Exception {
        mockMvc.perform(get("/createBook"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book", "genres", "authors"))
                .andExpect(view().name("createBook"));
    }

    @Test
    public void deleteBookTest() throws Exception {
        mockMvc.perform(post("/deleteBook?id={id}", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }

    @Test
    public void editBookTest() throws Exception {
        mockMvc.perform(post("/editBook"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }

    @Test
    public void saveBookTest() throws Exception {
        when(bookServiceMock.update(new Book())).thenReturn(new Book());
        mockMvc.perform(post("/saveBook"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book?id=null"));
        verify(bookServiceMock).update(new Book());
    }

    @Test
    public void booksTest() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attributeExists("books"));
        verify(bookServiceMock).getAllBook();
    }

    @Test
    public void bookTest() throws Exception {
        when(bookServiceMock.getById("1")).thenReturn(new Book());
        mockMvc.perform(get("/book?id={id}", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("book"))
                .andExpect(model().attributeExists("book", "genres", "authors"));
        verify(bookServiceMock).getById("1");
    }
}
