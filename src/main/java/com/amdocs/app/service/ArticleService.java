package com.amdocs.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.amdocs.app.model.Article;

/**
 * The Class ArticleService.
 */
@Service
public class ArticleService {

    /** The all questions. */
    private static List<Article> listOfArticles = new ArrayList<Article>();

    /**
     * Gets the all.
     *
     * @return the all
     */
    public List<Article> getAll() {
        return listOfArticles;
    }

    /**
     * Gets the question by id.
     *
     * @param id the id
     * @return the question by id
     */
    public Article getById( int id ) {
        Article article = null;
        Optional<Article> optionalArticle = listOfArticles.stream().filter( a -> a.getId() == id ).findAny();
        article = optionalArticle.isPresent() ? optionalArticle.get() : null;
        return article;
    }

    /**
     * Adds the.
     *
     * @param article the article
     */
    public void add( Article article ) {
        listOfArticles.add( article );
    }

    /**
     * Clear.
     */
    public void clear() {
        listOfArticles.clear();
    }
}
