package exception;

import com.elsynergy.nigerianpostcodes.Application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApiExceptionHandlerTest
{
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context)
            .build();
    }

    @Test
    public void whenNoHandlerFoundException_thenNotFound() throws Exception
    {
        this.mvc.perform(
                get("/postcodes/ruralsss/imo")
            )
                .andExpect(status().isNotFound())
                .andDo(print());
                //.andExpect(jsonPath("$.message", is("No handler found for GET /postcodes/ruralsss/imo")));
    }

    @Test
    public void whenHttpRequestMethodNotSupported_thenMethodNotAllowed() throws Exception {
        this.mvc.perform(
                post("/postcodes/rural/imo")
            )
            .andExpect(status().isMethodNotAllowed())
            .andDo(print())
            .andExpect(jsonPath("$.message", is("Request method 'POST' not supported")));

    }

}
