package com.example.wellread.reading;

import java.io.Serializable;
import java.util.UUID;

/**
 * A reading item representing a work that was recommended to the user.
 */
public class ReadingItem implements Serializable {
    private static final long serialVersionUID = 1L;
    public String id;
    public String title;
    public String author;
    public String recommender;
    public Integer year;
    public Status status;

    /**
     * This is the constructor for the Reading Item
     */
    public ReadingItem(String title,
                       String author,
                       String recommender,
                       Integer year,
                       Status status) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.recommender = recommender;
        this.year = year;
        this.status = status;
    }
    public ReadingItem (String id,
                        String title,
                        String author,
                        String recommender,
                        Integer year,
                        Status status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.recommender = recommender;
        this.year = year;
        this.status = status;
    }


}
