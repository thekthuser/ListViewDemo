package com.thekthuser.listviewdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "viewlistdemo.db";
    public static final int DATABASE_VERSION = 4;

    public static final String TABLE_ENTRIES = "entries";
    public static final String TABLE_ENTRY_IMAGES = "entry_images";

    public static final String COLUMN_ID = "_id";

    public static final String COLUMN_ENTRY_ID = "entryId";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SUMMARY = "summary";
    public static final String COLUMN_PRICE_AMOUNT = "price_amount";
    public static final String COLUMN_PRICE_CURRENCY = "price_currency";
    public static final String COLUMN_CONTENT_TYPE_TERM = "content_type_term";
    public static final String COLUMN_CONTENT_TYPE_LABEL = "content_type_label";
    public static final String COLUMN_RIGHTS = "rights";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_LINK_REL = "link_rel";
    public static final String COLUMN_LINK_TYPE = "link_type";
    public static final String COLUMN_LINK_HREF = "link_href";
    public static final String COLUMN_ID_LABEL = "id_label";
    public static final String COLUMN_ID_ID = "id_id";
    public static final String COLUMN_ID_BUNDLEID = "id_bundleId";
    public static final String COLUMN_ARTIST_LABEL = "artist_label";
    public static final String COLUMN_ARTIST_HREF = "artist_href";
    public static final String COLUMN_CATEGORY_ID = "category_id";
    public static final String COLUMN_CATEGORY_TERM = "category_term";
    public static final String COLUMN_CATEGORY_SCHEME = "category_scheme";
    public static final String COLUMN_CATEGORY_LABEL = "category_label";
    public static final String COLUMN_RELEASE_DATE = "release_date";
    public static final String COLUMN_RELEASE_DATE_HUMAN = "release_date_human";

    public static final String COLUMN_IMAGE_ID = "imageId";
    public static final String COLUMN_ENTRYID_ID = "entryid_id";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_HEIGHT = "height";


    public static final String ENTRIES_CREATE = "CREATE TABLE "
        + TABLE_ENTRIES + "("
        + COLUMN_ID + " integer primary key autoincrement, "
        + COLUMN_ENTRY_ID + " integer, "
        + COLUMN_NAME + " text, "
        + COLUMN_SUMMARY + " text, "
        + COLUMN_PRICE_AMOUNT + " text, "
        + COLUMN_PRICE_CURRENCY + " text, "
        + COLUMN_CONTENT_TYPE_TERM + " text, "
        + COLUMN_CONTENT_TYPE_LABEL + " text, "
        + COLUMN_RIGHTS + " text, "
        + COLUMN_TITLE + " text, "
        + COLUMN_LINK_REL + " text, "
        + COLUMN_LINK_TYPE + " text, "
        + COLUMN_LINK_HREF + " text, "
        + COLUMN_ID_LABEL + " text, "
        + COLUMN_ID_ID + " integer, "
        + COLUMN_ID_BUNDLEID + " text, "
        + COLUMN_ARTIST_LABEL + " text, "
        + COLUMN_ARTIST_HREF + " text, "
        + COLUMN_CATEGORY_ID + " text, "
        + COLUMN_CATEGORY_TERM + " text, "
        + COLUMN_CATEGORY_SCHEME + " text, "
        + COLUMN_CATEGORY_LABEL + " text, "
        + COLUMN_RELEASE_DATE + " text, "
        + COLUMN_RELEASE_DATE_HUMAN + " text)";

    public static final String ENTRY_IMAGES_CREATE = "CREATE TABLE "
        + TABLE_ENTRY_IMAGES + "("
        + COLUMN_ID + " integer primary key autoincrement, "
        + COLUMN_IMAGE_ID + " integer, "
        + COLUMN_ENTRYID_ID + " integer, "
        + COLUMN_URL + " text, "
        + COLUMN_HEIGHT + " integer);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        db.execSQL(ENTRIES_CREATE);
        db.execSQL(ENTRY_IMAGES_CREATE);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRY_IMAGES);
        db.setTransactionSuccessful();
        db.endTransaction();
        onCreate(db);
    }

}
