package com.example.wellread.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.wellread.reading.ReadingItem;
import com.example.wellread.reading.Status;

import java.util.ArrayList;
import java.util.List;

public class ReadingItemSQLiteSvcImpl extends SQLiteOpenHelper implements IReadingListSvc {

    private static final String DATABASE_NAME = "readingItems.db";
    private static final int DATABASE_VERSION = 1;
    private ArrayList<ReadingItem> readingItems = (ArrayList<ReadingItem>) getAllReadingItems();

    public ReadingItemSQLiteSvcImpl(Context context) throws readingItemException {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private String createReadingItemTable =
            "create table readingItem (id text primary key," +
                    "title text not null, author text, recommender text , year int, status text)";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createReadingItemTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS readingItem");
        onCreate(sqLiteDatabase);
    }

    @Override
    public void createReadingItem(ReadingItem readingItem) throws readingItemException {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id", readingItem.id);
        values.put("title", readingItem.title);
        values.put("author", readingItem.author);
        values.put("recommender", readingItem.recommender);
        values.put("year", readingItem.year);
        values.put("status", readingItem.status.name());

        long rowIdOfInsertedRecord = sqLiteDatabase.insert("readingItem", null, values);
        sqLiteDatabase.close();

        if (rowIdOfInsertedRecord == -1) {
            throw new readingItemException("Could not insert reading item into database");
        }

    }

    @Override
    public List<ReadingItem> getAllReadingItems() throws readingItemException {
        List<ReadingItem> readingItems = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        try {
            Cursor cursor = sqLiteDatabase.query("readingItem",
                    new String[]{
                            "id", "title", "author", "recommender", "year", "status"},
                    null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ReadingItem readingItem = getReadingItem(cursor);
                readingItems.add(readingItem);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {
            throw new readingItemException("Can't get reading items" + e.toString());
        }
        return readingItems;
    }

    private ReadingItem getReadingItem(Cursor cursor) {
        ReadingItem readingItem = new ReadingItem(
                cursor.getString(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("title")),
                cursor.getString(cursor.getColumnIndex("author")),
                cursor.getString(cursor.getColumnIndex("recommender")),
                cursor.getInt(cursor.getColumnIndex("year")),
                Status.valueOf(cursor.getString(cursor.getColumnIndex("status")))
        );
        return readingItem;
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
    public void updateReadingItem(String id, String title, String author, String recommender, Integer year, Status status) throws readingItemException {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("author", author);
        values.put("recommender", recommender);
        values.put("year", year);
        values.put("status", status.name());

        int numberOfRowsUpdated = sqLiteDatabase.update("readingItem", values, "id = ?",
                new String[]{id});
        sqLiteDatabase.close();

        if (numberOfRowsUpdated < 1) {
            throw new readingItemException("Could not update reading item");
        }
    }

    @Override
    public void deleteReadingItem(ReadingItem readingItem) throws readingItemException {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        int rowsDeleted = sqLiteDatabase.delete("readingItem", "id = ?", new String[]
                {readingItem.id});
        sqLiteDatabase.close();

        if (rowsDeleted == 0) {
            throw new readingItemException("could not delete reading Item ");
        }
    }
}
