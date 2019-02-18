package dik.library.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dik.library.model.Genre;
import dik.library.service.GenreService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GenreRestController.class)
public class GenreRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreService genreServiceMock;

    private final String baseUrl = "/rest/genre/";
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
        when(genreServiceMock.getById("1")).thenReturn(genre);
        mockMvc.perform(get(baseUrl + "{id}", genre.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(genre.getId()))
                .andExpect(jsonPath("$.genreName", is(genre.getGenreName())))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        verify(genreServiceMock).getById("1");
    }

    @Test
    public void genresTest() throws Exception {
        when(genreServiceMock.getAllGenre()).thenReturn(genres);
        mockMvc.perform(get(baseUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(genre.getId()))
                .andExpect(jsonPath("$[0].genreName", is(genre.getGenreName())))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        verify(genreServiceMock).getAllGenre();
    }

    @Test
    public void createGenreTest() throws Exception {
        Genre newGenre = new Genre("2", "Детектив");
        String genreJson = new ObjectMapper().writeValueAsString(newGenre);
        mockMvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON).content(genreJson))
                .andExpect(status().isOk());
    }

    @Test
    public void editGenreTest() throws Exception {
        Genre newGenre = new Genre("1", "Триллер");
        String genreJson = new ObjectMapper().writeValueAsString(newGenre);
        mockMvc.perform(put(baseUrl + "{id}", genre.getId())
                .contentType(MediaType.APPLICATION_JSON).content(genreJson))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteGenreTest() throws Exception {
        mockMvc.perform(delete(baseUrl + "{id}", genre.getId()))
                .andExpect(status().isNoContent());
    }
}
