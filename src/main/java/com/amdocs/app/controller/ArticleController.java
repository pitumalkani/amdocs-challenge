package com.amdocs.app.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.app.model.Article;
import com.amdocs.app.service.ArticleService;

@RestController
@RequestMapping ( value = "/articles" )
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * Gets the all.
     *
     * @return the all
     */
    @RequestMapping ( method = RequestMethod.GET, produces = "application/json" )
    public List<Article> getAll() {
        return articleService.getAll();
    }

    /**
     * Gets the all.
     *
     * @return the all
     */
    @RequestMapping ( value = "/{id}", method = RequestMethod.GET, produces = "application/json" )
    public ResponseEntity<Article> findById( @PathVariable ( "id" ) int id ) {
        Article article = articleService.getById( id );
        if ( Objects.isNull( article ) ) {
            return new ResponseEntity<Article>( HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity<Article>( article, HttpStatus.OK );
    }

}
