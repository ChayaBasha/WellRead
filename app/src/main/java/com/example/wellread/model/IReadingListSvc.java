package com.example.wellread.model;

import com.example.wellread.reading.ReadingContent;

import java.util.List;
import java.util.UUID;

public interface IReadingListSvc extends IService{

    public final String NAME = "IReadingSvc";

    public void createReadingItem(ReadingContent.ReadingItem readingItem) throws readingItemException;
    public List<ReadingContent.ReadingItem> getAllReadingItems() throws readingItemException;
    public ReadingContent.ReadingItem getReadingItemById(UUID readingItemId) throws readingItemException;
    public void updateReadingItem(ReadingContent.ReadingItem readingItem) throws readingItemException;
    public void deleteReadingItem(ReadingContent.ReadingItem readingItem) throws readingItemException;
}
