package hello;

// import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.junit.Before;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;


@RunWith(SpringRunner.class)
@WebMvcTest(WebController.class)
public class HomePageTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthControllerAdvice aca;

    @MockBean
    private ClientRegistrationRepository crr;

    private OAuth2User principal;

    /**
     * Set up an OAuth mock user so that we can unit test protected endpoints
     */
    @Before
    public void setUpUser() {
        principal = OAuthUtils.createOAuth2User("Chris Gaucho", "cgaucho@example.com");
    }

    @Test
    public void getHomePage_ContentType() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }


    @Test
    public void getHomePage_BootstrapLoaded() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(xpath(BootstrapLiterals.bootstrapCSSXpath).exists());
        for (String s: BootstrapLiterals.bootstrapJSurls) {
            String jsXPath = String.format("//script[@src='%s']",s);
            mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
              .andExpect(status().isOk())
              .andExpect(xpath(jsXPath).exists());
        }
    }


    @Test
    public void getHomePage_hasCorrectTitle() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(xpath("//title").exists())
                .andExpect(xpath("//title").string("CS56 Spring Boot Practice App"));
    }


    @Test
    public void getHomePage_hasCorrectBrand() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div/nav/a").exists())
                .andExpect(xpath("/html/body/div/nav/a").string("lab07"));
    }

    @Test
    @WithMockUser
    public void getPage1_hasCorrectHeader() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/page1")
            .with(authentication(OAuthUtils.getOauthAuthenticationFor(principal)))
            .accept(MediaType.TEXT_HTML))
            .andExpect(status().isOk())
            .andExpect(xpath("/html/body/div/h1").exists())
            .andExpect(xpath("/html/body/div/h1").string("Earthquakes"));
    }

    @Test
    public void getNavigation_hasUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id='navbarTogglerDemo03']/ul[1]/li[3]/a").exists())
                .andExpect(xpath("//*[@id='navbarTogglerDemo03']/ul[1]/li[3]/a/@href").string("/users"));
    }

}

