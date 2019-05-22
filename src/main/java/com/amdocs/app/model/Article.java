package com.amdocs.app.model;

import lombok.Data;

/**
 * Instantiates a new articles.
 */
@Data
public class Article {

    private static int count = 0;
    
    public Article( String title ) {
        this.title = title;
        this.id = ++count;
    }

    /** The title. */
    private String title;

    /** The body. */
    private String body;

    /** The id. */
    private int id;
}
