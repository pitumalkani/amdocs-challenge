package com.amdocs.app;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.amdocs.app.Application;
import com.amdocs.app.model.Article;
import com.amdocs.app.service.ArticleService;
import com.amdocs.app.util.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith ( SpringRunner.class )
@SpringBootTest ( webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {Application.class} )
@ComponentScan ( basePackages = {"com.amdocs.app"} )
@AutoConfigureMockMvc
public class ApplicationTest {
    private static List<Article> articles = new ArrayList<Article>();
    private static ArticleService service = new ArticleService();
    @Autowired
    private MockMvc mockMvc;

    @BeforeClass
    public static void populateArticles() {
        articles.add( new Article( "10 things that you thought were unhealthy" ) );
        articles.add( new Article( "You won't sleep until you read this" ) );
        articles.add( new Article( "I ran out of catchy titles" ) );
    }

    @Before
    public void clearDB() {
        this.service.clear();
    }

    public void addArticles() {
        for ( Article article : articles ) {
            this.service.add( article );
        }
    }

    @Test
    public void shouldRetrieveNothingFromEmptyDatabase() throws Exception {
        this.mockMvc.perform( get( "/articles" ) ).andExpect( content().contentType( TestUtil.APPLICATION_JSON_UTF8 ) ).andExpect( jsonPath( "$", hasSize( 0 ) ) );
    }

    @Test
    public void shouldRetrievePostedArticles() throws Exception {
        addArticles();
        this.mockMvc.perform( get( "/articles" ) ).andExpect( content().contentType( TestUtil.APPLICATION_JSON_UTF8 ) ).andExpect( jsonPath( "$", hasSize( articles.size() ) ) );
    }

    @Test
    public void shouldAllowUsToFindArticles() throws Exception {
        addArticles();
        Article article = this.service.getAll().get( 0 );
        this.mockMvc.perform( get( "/articles/" + article.getId() ) ).andExpect( jsonPath( "id", is( article.getId() ) ) ).andExpect( status().isOk() );
    }

    @Test
    public void shouldHandleGetNotFound() throws Exception {
        System.out.println( "<PROP::hidden>true" );
        this.mockMvc.perform( get( "/articles/10" ) ).andExpect( status().isNotFound() );
    }

    public static String asJsonString( final Object obj ) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString( obj );
            return jsonContent;
        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }
}
