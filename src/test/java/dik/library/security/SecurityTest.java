package dik.library.security;

import dik.library.model.User;
import dik.library.rest.AuthorRestController;
import dik.library.rest.BookRestController;
import dik.library.rest.GenreRestController;
import dik.library.service.AuthorService;
import dik.library.service.BookService;
import dik.library.service.GenreService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import javax.servlet.Filter;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest({AuthorRestController.class, GenreRestController.class, BookRestController.class})
public class SecurityTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mvc;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private BookService bookService;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).apply(springSecurity()).build();
    }

    @Test
    public void testLogin() throws Exception {
        User user = new User("1", "user", "password");
        when(userService.loadUserByUsername("wrong")).thenThrow(new UsernameNotFoundException(""));
        mvc.perform(formLogin("/login/process").userParameter("wrong").passwordParam("libraryPassword")
                .user("user").password("password")
        ).andExpect(redirectedUrl("/login?error"));
        when(userService.loadUserByUsername("user")).thenReturn(new MyUserDetails(user));
        mvc.perform(formLogin("/login/process").userParameter("libraryUsername").passwordParam("libraryPassword")
                .user("user").password("password")
        ).andExpect(redirectedUrl("/swagger-ui.html"));
    }
}
