package dik.library.web;

import dik.library.controller.GenreController;
import dik.library.model.Genre;
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
@WebMvcTest(GenreController.class)
public class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreService genreServiceMock;

    @Test
    public void createGenreTest() throws Exception {
        mockMvc.perform(post("/createGenre"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/genres"));
    }

    @Test
    public void deleteGenreTest() throws Exception {
        mockMvc.perform(post("/deleteGenre"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/genres"));
    }

    @Test
    public void editGenreTest() throws Exception {
        mockMvc.perform(post("/editGenre"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/genres"));
    }

    @Test
    public void genresTest() throws Exception {
        mockMvc.perform(get("/genres"))
                .andExpect(status().isOk())
                .andExpect(view().name("genres"))
                .andExpect(model().attributeExists("genres"));
        verify(genreServiceMock).getAllGenre();
    }

    @Test
    public void genreTest() throws Exception {
        when(genreServiceMock.getById("1")).thenReturn(new Genre());
        mockMvc.perform(get("/genre?id={id}", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("genre"))
                .andExpect(model().attributeExists("genre"));
        verify(genreServiceMock).getById("1");
    }
}
