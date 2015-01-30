package com.thekthuser.listviewdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.content.ContentValues;
import android.database.Cursor;

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
    }

    public void addEntryImage(EntryImage image) {
        ContentValues cValues = new ContentValues();
        cValues.put(DatabaseHelper.COLUMN_IMAGE_ID, image.imageId);
        cValues.put(DatabaseHelper.COLUMN_ENTRYID_ID, image.entryid_id);
        cValues.put(DatabaseHelper.COLUMN_URL, image.url);
        cValues.put(DatabaseHelper.COLUMN_HEIGHT, image.height);
        long rowId;
    }

    //public boolean isEntrySaved(

}
