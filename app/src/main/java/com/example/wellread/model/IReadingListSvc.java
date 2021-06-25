package com.example.wellread.model;

import com.example.wellread.reading.ReadingItem;
import com.example.wellread.reading.Status;

import java.util.List;


public interface IReadingListSvc extends IService{

    String NAME = "IReadingSvc";

    void createReadingItem(ReadingItem readingItem) throws readingItemException;
    List<ReadingItem> getAllReadingItems() throws readingItemException;
    ReadingItem getReadingItemById(String readingItemId) throws readingItemException;
    void updateReadingItem(String id, String title,
                                  String author, String recommender,
                                  Integer year, Status status) throws readingItemException;
    void deleteReadingItem(ReadingItem readingItem) throws readingItemException;
}
