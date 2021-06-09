package com.example.wellread.reading;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Class for Reading Items that are recommended to the user
 * Ideally this would be created by the User
 * This is just an example for now
 */
public class ReadingContent implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * An array of sample reading items.
     */
    public static final List<ReadingItem> ITEMS = new ArrayList<ReadingItem>();


    /**
     * A map of sample Reading items, by ID.
     */
    public static final Map<String, ReadingItem> ITEM_MAP = new HashMap<String, ReadingItem>();

    //    private static final int COUNT = 25;
    private static ArrayList<ReadingItem> SAMPLEDATA = new ArrayList<ReadingItem>(Arrays.asList(


            new ReadingItem(
                    "Bleakhouse",
                    "Charles Dickens",
                    "Prof Thomas",
                    1852,
                    Status.TO_READ),



            new ReadingItem(
                    "The Hitch Hiker's Guide to the Galaxy",
                    "Douglas Adams",
                    "R. Blumenthal",
                    1978,
                    Status.READ),

            new ReadingItem(
                    "Computers and Society",
                    "Lisa Kaczmarczyk",
                    "M. Goldweber",
                    2012, Status.TO_READ)));

    static {
        // Add some sample items.
        for (int i = 0; i < SAMPLEDATA.size(); i++) {
            addItem(SAMPLEDATA.get(i));
        }
    }

    private static void addItem(ReadingItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }


    /**
     * A reading item representing a work that was recommended to the user.
     */
    public static class ReadingItem {
        public UUID id;
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
            this.id = UUID.randomUUID();
            this.title = title;
            this.author = author;
            this.recommender = recommender;
            this.year = year;
            this.status = status;
        }


    }

}
