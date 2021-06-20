package com.example.wellread.model;

import android.content.Context;

import androidx.annotation.RequiresPermission;

import com.example.wellread.reading.ReadingItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class ReadingItemSvcImpl implements IReadingListSvc {

    private Context context = ServiceFactory.getInstance(null).context;
    private final String readingItemFolder = "readingItemFolder.sio";
    private ArrayList<ReadingItem> readingItems = (ArrayList<ReadingItem>) getAllReadingItems();

    public ReadingItemSvcImpl() throws readingItemException {
    }


    public void writeFile() throws readingItemException {
        try {
            System.out.println("write file was called");

            FileOutputStream fos = context.openFileOutput(readingItemFolder, Context.MODE_PRIVATE);

            ObjectOutputStream output = new ObjectOutputStream(fos);
//
            for (int i = 0; i < readingItems.size(); i++) {
                System.out.println(readingItems.get(i).title);
            }

            output.writeObject(readingItems);
            output.flush();
            output.close();
        } catch (IOException e) {
            throw new readingItemException("cannot save reading item");
        }
    }


    public void createReadingItem(ReadingItem readingItem) throws readingItemException {
        System.out.println(readingItem);
        readingItems.add(readingItem);
        System.out.println(readingItems);
        writeFile();
    }


    @Override
    public List<ReadingItem> getAllReadingItems() throws readingItemException {

        List<ReadingItem> readingItems = new ArrayList<>();
        try {
            File file = new File(context.getFilesDir(), readingItemFolder);
            if (file.exists()) {
                FileInputStream fis = context.openFileInput(readingItemFolder);
                ObjectInputStream readReadingItems = new ObjectInputStream(fis);
                Object fileContents = readReadingItems.readObject();
                readReadingItems.close();
                readingItems.addAll((Collection<? extends ReadingItem>) fileContents);
            } else System.out.println("I can't find the file");
        } catch (IOException e) {
            throw new readingItemException("I/O problems; can't read reading Items" + context.getFileStreamPath(readingItemFolder));
        } catch (ClassNotFoundException e) {
            throw new readingItemException(("Class not found exception"));
        }
        return readingItems;
    }


    @Override
    public ReadingItem getReadingItemById(String readingItemId) throws readingItemException {


        List<ReadingItem> readingItems = getAllReadingItems();
        ReadingItem readingItem = null;
        for (int i = 0; i < readingItems.size(); i++) {

            if (readingItems.get(i).id.equals(readingItemId)) {
                readingItem = readingItems.get(i);
            }

        }

        if (readingItem == null) {
            throw new readingItemException("no reading item with Id " + readingItemId);
        } else
            return readingItem;
    }

    @Override
    public void updateReadingItem(ReadingItem readingItem) throws readingItemException {
        ReadingItem existingReadingItem = this.getReadingItemById(readingItem.id);
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
    public void deleteReadingItem(ReadingItem readingItem) throws readingItemException {
        ReadingItem existingReadingItem = this.getReadingItemById(readingItem.id);

        for(int i = 0; i < readingItems.size(); i++){
            if (readingItems.get(i).id.equals(existingReadingItem.id)) {
                readingItems.remove(i);
                System.out.println("WE HAVE A HIT ON "+ existingReadingItem.id);

                break;
            }

//            System.out.println(readingItems.get(i).title);
        }
        writeFile();
    }
//
//        if (readingItem != null) {
//            ReadingItem existingReadingItem = this.getReadingItemById(readingItem.id);
//            context.deleteFile(existingReadingItem);
//            File existingReadingItem = context.getFileStreamPath()readingItemFolder.toPath().resolve(readingItem.id + ".readingItem.out")
//                    .toFile();
//            if (existingReadingItem.exists()) {
//                existingReadingItem.delete();
//            } else
//                throw new readingItemException(
//                        "could not delete " + readingItem.id);
//
//        } else
//            throw new readingItemException(" null input");
//
//    }
}

