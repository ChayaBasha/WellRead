package com.example.wellread.model;

import com.example.wellread.reading.ReadingContent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReadingItemSvcImpl implements IReadingListSvc {

    private File readingItemFolder = new File("readingItems");

    @Override
    public void createReadingItem(ReadingContent.ReadingItem readingItem) throws readingItemException {
        if (readingItem != null) {
            try {
                readingItemFolder.mkdirs();
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(
                        readingItemFolder.toPath().resolve((readingItem.id + ".readingItem.out")).toFile()
                ));

                output.writeObject(readingItem);
                output.flush();
                output.close();
            } catch (IOException e) {
                throw new readingItemException("cannot save reading item");
            }
        } else
            throw new readingItemException("cannot save empty reading item");
    }

    @Override
    public List<ReadingContent.ReadingItem> getAllReadingItems() throws readingItemException {
        List<ReadingContent.ReadingItem> readingItems = new ArrayList<ReadingContent.ReadingItem>();
        if (this.readingItemFolder.isDirectory()) {
            for (File file : this.readingItemFolder.listFiles()) {
                if (file.isFile()) {
                    try {
                        ObjectInputStream readItineraries = new ObjectInputStream(new FileInputStream(file));
                        Object fileContents = readItineraries.readObject();
                        readItineraries.close();
                        if (fileContents instanceof ReadingContent.ReadingItem) {
                            readingItems.add((ReadingContent.ReadingItem) fileContents);
                        } else
                            throw new readingItemException(
                                    "file contents are not an itinerary " + file.getAbsolutePath());
                    } catch (IOException e) {
                        throw new readingItemException("IO problems " + file.getAbsolutePath());
                    } catch (ClassNotFoundException e) {
                        throw new readingItemException("class not found");
                    }
                } else
                    throw new readingItemException("not a file :-( " + file.getAbsolutePath());
            }
        } else
            throw new readingItemException(
                    "can't find directory itineraryFolder " + this.readingItemFolder.getAbsolutePath());
        return readingItems;
    }


    @Override
    public ReadingContent.ReadingItem getReadingItemById(UUID readingItemId) throws readingItemException {
        ReadingContent.ReadingItem readingItem = null;
        List<ReadingContent.ReadingItem> readingItems = getAllReadingItems();

        for (int i = 0; i < readingItems.size(); i++) {

            if (readingItems.get(i).id.equals(readingItemId)) {
                readingItem = readingItems.get(i);
            }

        }

        if (readingItem == null) {
            throw new readingItemException("no reading item for user with Id " + readingItemId);
        } else
            return readingItem;
    }

    @Override
    public void updateReadingItem(ReadingContent.ReadingItem readingItem) throws readingItemException {
        ReadingContent.ReadingItem existingReadingItem = this.getReadingItemById(readingItem.id);
        if (existingReadingItem != null) {

            existingReadingItem.title = readingItem.title;
            existingReadingItem.author = readingItem.author;
            existingReadingItem.year = readingItem.year;
            existingReadingItem.recommender = readingItem.recommender;
            existingReadingItem.status = readingItem.status;

            this.createReadingItem(existingReadingItem);
        }
    }

    @Override
    public void deleteReadingItem(ReadingContent.ReadingItem readingItem) throws readingItemException {
        if (readingItem != null) {

            File existingReadingItem = readingItemFolder.toPath().resolve(readingItem.id + ".itinerary.out")
                    .toFile();
            if (existingReadingItem.exists()) {
                existingReadingItem.delete();
            } else
                throw new readingItemException(
                        "could not delete " + readingItem.id);

        } else
            throw new readingItemException(" null input");

    }
}

