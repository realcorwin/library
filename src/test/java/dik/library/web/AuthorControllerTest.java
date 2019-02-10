package dik.library.web;

import dik.library.controller.AuthorController;
import dik.library.model.Author;
import dik.library.service.AuthorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorServiceMock;

    @Test
    public void createAuthorTest() throws Exception {
        mockMvc.perform(post("/createAuthor"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/authors"));
    }

    @Test
    public void deleteAuthorTest() throws Exception {
        mockMvc.perform(post("/deleteAuthor"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/authors"));
    }

    @Test
    public void editAuthorTest() throws Exception {
        mockMvc.perform(post("/editAuthor"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/authors"));
    }

    @Test
    public void authorsTest() throws Exception {
        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(view().name("authors"))
                .andExpect(model().attributeExists("authors"));
        verify(authorServiceMock).getAllAuthor();
    }

    @Test
    public void authorTest() throws Exception {
        when(authorServiceMock.getById("1")).thenReturn(new Author());
        mockMvc.perform(get("/author?id={id}", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("author"))
                .andExpect(model().attributeExists("author"));
        verify(authorServiceMock).getById("1");
    }
}
