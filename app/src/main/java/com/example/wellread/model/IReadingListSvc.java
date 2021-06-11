package com.example.wellread.model;

import com.example.wellread.reading.ReadingItem;

import java.util.List;
import java.util.UUID;

public interface IReadingListSvc extends IService{

    public final String NAME = "IReadingSvc";

    public void createReadingItem(ReadingItem readingItem) throws readingItemException;
    public List<ReadingItem> getAllReadingItems() throws readingItemException;
    public ReadingItem getReadingItemById(String readingItemId) throws readingItemException;
    public void updateReadingItem(ReadingItem readingItem) throws readingItemException;
    public void deleteReadingItem(ReadingItem readingItem) throws readingItemException;
}
