package com.thekthuser.listviewdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.content.ContentValues;
import android.database.Cursor;

import android.util.Log;

public class ItemAdapter {

    private Context context;
    private SQLiteDatabase db;
    private SQLiteDatabase dbr;
    private DatabaseHelper dbHelper;

    public ItemAdapter(Context context) {
        this.context = context;
    }

    public ItemAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        dbr = dbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void addEntry(Entry entry) {
        ContentValues cValues = new ContentValues();
        cValues.put(DatabaseHelper.COLUMN_ENTRY_ID, entry.entryId);
        cValues.put(DatabaseHelper.COLUMN_NAME, entry.name);
        cValues.put(DatabaseHelper.COLUMN_SUMMARY, entry.summary);
        cValues.put(DatabaseHelper.COLUMN_PRICE_AMOUNT, entry.price_amount);
        cValues.put(DatabaseHelper.COLUMN_PRICE_CURRENCY, entry.price_currency);
        cValues.put(DatabaseHelper.COLUMN_CONTENT_TYPE_TERM, entry.content_type_term);
        cValues.put(DatabaseHelper.COLUMN_CONTENT_TYPE_LABEL, entry.content_type_label);
        cValues.put(DatabaseHelper.COLUMN_RIGHTS, entry.rights);
        cValues.put(DatabaseHelper.COLUMN_TITLE, entry.title);
        cValues.put(DatabaseHelper.COLUMN_LINK_REL, entry.link_rel);
        cValues.put(DatabaseHelper.COLUMN_LINK_TYPE, entry.link_type);
        cValues.put(DatabaseHelper.COLUMN_LINK_HREF, entry.link_href);
        cValues.put(DatabaseHelper.COLUMN_ID_LABEL, entry.id_label);
        cValues.put(DatabaseHelper.COLUMN_ID_ID, entry.id_id);
        cValues.put(DatabaseHelper.COLUMN_ID_BUNDLEID, entry.id_bundleId);
        cValues.put(DatabaseHelper.COLUMN_ARTIST_LABEL, entry.artist_label);
        cValues.put(DatabaseHelper.COLUMN_ARTIST_HREF, entry.artist_href);
        cValues.put(DatabaseHelper.COLUMN_CATEGORY_ID, entry.category_id);
        cValues.put(DatabaseHelper.COLUMN_CATEGORY_TERM, entry.category_term);
        cValues.put(DatabaseHelper.COLUMN_CATEGORY_SCHEME, entry.category_scheme);
        cValues.put(DatabaseHelper.COLUMN_CATEGORY_LABEL, entry.category_label);
        cValues.put(DatabaseHelper.COLUMN_RELEASE_DATE, entry.release_date);
        cValues.put(DatabaseHelper.COLUMN_RELEASE_DATE_HUMAN, entry.release_date_human);

        db.beginTransaction();
        db.insert(DatabaseHelper.TABLE_ENTRIES, null, cValues);
        db.setTransactionSuccessful();
        db.endTransaction();

        for (int i = 0; i < entry.images.length; i++) {
            addEntryImage(entry.images[i]);
        }
    }

    public void addEntryImage(EntryImage image) {
        ContentValues cValues = new ContentValues();
        cValues.put(DatabaseHelper.COLUMN_IMAGE_ID, image.imageId);
        cValues.put(DatabaseHelper.COLUMN_ENTRYID_ID, image.entryid_id);
        cValues.put(DatabaseHelper.COLUMN_URL, image.url);
        cValues.put(DatabaseHelper.COLUMN_HEIGHT, image.height);

        db.beginTransaction();
        db.insert(DatabaseHelper.TABLE_ENTRY_IMAGES, null, cValues);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void removeEntry(Entry entry) {
        String selection = DatabaseHelper.COLUMN_ID_ID + " = ?";
        String[] selectionArgs = {
            Integer.toString(entry.id_id)
        };

        db.beginTransaction();
        db.delete(DatabaseHelper.TABLE_ENTRIES, selection, selectionArgs);
        db.setTransactionSuccessful();
        db.endTransaction();

        removeEntryImages(Integer.toString(entry.images[0].entryid_id));
    }

    public void removeEntryImages(String entryid_id) {
        String selection = DatabaseHelper.COLUMN_ENTRYID_ID + " = ?";
        String[] selectionArgs = {
            entryid_id
        };

        db.beginTransaction();
        db.delete(DatabaseHelper.TABLE_ENTRY_IMAGES, selection, selectionArgs);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public Entry[] getEntries() {
        String[] projection = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_ENTRY_ID,
            DatabaseHelper.COLUMN_NAME,
            DatabaseHelper.COLUMN_SUMMARY,
            DatabaseHelper.COLUMN_PRICE_AMOUNT,
            DatabaseHelper.COLUMN_PRICE_CURRENCY,
            DatabaseHelper.COLUMN_CONTENT_TYPE_TERM,
            DatabaseHelper.COLUMN_CONTENT_TYPE_LABEL,
            DatabaseHelper.COLUMN_RIGHTS,
            DatabaseHelper.COLUMN_TITLE,
            DatabaseHelper.COLUMN_LINK_REL,
            DatabaseHelper.COLUMN_LINK_TYPE,
            DatabaseHelper.COLUMN_LINK_HREF,
            DatabaseHelper.COLUMN_ID_LABEL,
            DatabaseHelper.COLUMN_ID_ID,
            DatabaseHelper.COLUMN_ID_BUNDLEID,
            DatabaseHelper.COLUMN_ARTIST_LABEL,
            DatabaseHelper.COLUMN_ARTIST_HREF,
            DatabaseHelper.COLUMN_CATEGORY_ID,
            DatabaseHelper.COLUMN_CATEGORY_TERM,
            DatabaseHelper.COLUMN_CATEGORY_SCHEME,
            DatabaseHelper.COLUMN_CATEGORY_LABEL,
            DatabaseHelper.COLUMN_RELEASE_DATE,
            DatabaseHelper.COLUMN_RELEASE_DATE_HUMAN
        };

        String order = DatabaseHelper.COLUMN_ID + " ASC";

        dbr.beginTransaction();
        Cursor cursor = dbr.query(
            DatabaseHelper.TABLE_ENTRIES,
            projection,
            null,
            null,
            null,
            null,
            order
        );
        dbr.setTransactionSuccessful();
        dbr.endTransaction();

        Entry[] entries = new Entry[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            String id_id = cursor.getString(14);
            EntryImage[] entryImages = getEntryImages(id_id);
            Entry entry = new Entry(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), 
                    cursor.getString(3), cursor.getString(4), cursor.getString(5), 
                    cursor.getString(6), cursor.getString(7), cursor.getString(8), 
                    cursor.getString(9), cursor.getString(10), cursor.getString(11), 
                    cursor.getString(12), cursor.getString(13), cursor.getInt(14), 
                    cursor.getString(15), cursor.getString(16), cursor.getString(17), 
                    cursor.getString(18), cursor.getString(19), cursor.getString(20), 
                    cursor.getString(21), cursor.getString(22), cursor.getString(23), entryImages);
            entries[i] = entry;
            cursor.moveToNext();
        }
        return entries;
    }

    public EntryImage[] getEntryImages(String entryid_id) {
        String[] projection = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_IMAGE_ID,
            DatabaseHelper.COLUMN_ENTRYID_ID,
            DatabaseHelper.COLUMN_URL,
            DatabaseHelper.COLUMN_HEIGHT
        };
        String selection = DatabaseHelper.COLUMN_ENTRYID_ID + " = ?";
        String[] selectionArgs = {
            entryid_id
        };
        String order = DatabaseHelper.COLUMN_IMAGE_ID + " ASC";

        dbr.beginTransaction();
        Cursor cursor = dbr.query(
            DatabaseHelper.TABLE_ENTRY_IMAGES,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            order
        );
        dbr.setTransactionSuccessful();
        dbr.endTransaction();

        EntryImage[]  entryImages = new EntryImage[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            EntryImage entryImage = new EntryImage(cursor.getInt(0), cursor.getInt(1), 
                cursor.getInt(2), cursor.getString(3), cursor.getInt(4));
            entryImages[i] = entryImage;
            cursor.moveToNext();
        }
        return entryImages;
    }

    public boolean isEntrySaved(int id_id) {
        String projection[] = {
            DatabaseHelper.COLUMN_ID_ID
        };
        String selection = DatabaseHelper.COLUMN_ID_ID + " = ?";
        String sId = Integer.toString(id_id);
        String[] selectionArgs = {
            sId
        };

        dbr.beginTransaction();
        Cursor cursor = dbr.query(
            DatabaseHelper.TABLE_ENTRIES,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        );
        dbr.setTransactionSuccessful();
        dbr.endTransaction();

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
