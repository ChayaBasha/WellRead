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
    private ArrayList<ReadingItem> readingItems;

//    private File readingItemFolder = new File("readingItems");


    public void writeFile() throws readingItemException {
        try {

            FileOutputStream fos = context.openFileOutput(readingItemFolder, Context.MODE_PRIVATE);
//                readingItemFolder.mkdirs();

            ObjectOutputStream output = new ObjectOutputStream(fos);
//                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(
//                        readingItemFolder.toPath().resolve((readingItem.id + ".readingItem.out")).toFile()
//                ));

            output.writeObject(readingItems);
//                output.flush();
            output.close();
        } catch (IOException e) {
            throw new readingItemException("cannot save reading item");
        }
    }


    public void createReadingItem(ReadingItem readingItem) throws readingItemException {
        readingItems.add(readingItem);
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
            }
        } catch (IOException e) {
            throw new readingItemException("I/O problems; can't read reading Items" + context.getFileStreamPath(readingItemFolder));
        } catch (ClassNotFoundException e) {
            throw new readingItemException(("Class not found exception"));
        }
        return readingItems;
    }
//        List<ReadingItem> readingItems = new ArrayList<ReadingItem>();
//        if (this.readingItemFolder.isDirectory()) {
//            for (File file : this.readingItemFolder.listFiles()) {
//                if (file.isFile()) {
//                    try {
//                        ObjectInputStream readReadingItems = new ObjectInputStream(new FileInputStream(file));
//                        Object fileContents = readReadingItems.readObject();
//                        readReadingItems.close();
//                        if (fileContents instanceof ReadingItem) {
//                            readingItems.add((ReadingItem) fileContents);
//                        } else
//                            throw new readingItemException(
//                                    "file contents are not a reading Item " + file.getAbsolutePath());
//                    } catch (IOException e) {
//                        throw new readingItemException("IO problems " + file.getAbsolutePath());
//                    } catch (ClassNotFoundException e) {
//                        throw new readingItemException("class not found");
//                    }
//                } else
//                    throw new readingItemException("not a file :-( " + file.getAbsolutePath());
//            }
//        } else
//            throw new readingItemException(
//                    "can't find directory Reading Items Folder " + this.readingItemFolder.getAbsolutePath());
//        return readingItems;
//    }


    @Override
    public ReadingItem getReadingItemById(String readingItemId) throws readingItemException {

//        String ReadingItemId = readingItem1.id;

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

//        if (readingItem != null) {
//            ReadingItem existingReadingItem = this.getReadingItemById(readingItem.id);
//            context.deleteFile(existingReadingItem);
////            File existingReadingItem = context.getFileStreamPath()readingItemFolder.toPath().resolve(readingItem.id + ".readingItem.out")
//                    .toFile();
//            if (existingReadingItem.exists()) {
//                existingReadingItem.delete();
//            } else
//                throw new readingItemException(
//                        "could not delete " + readingItem.id);
//
//        } else
//            throw new readingItemException(" null input");

    }
}

