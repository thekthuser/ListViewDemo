package com.thekthuser.listviewdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "viewlistdemo.db";
    public static final int DATABASE_VERSION = 1;

    //public static final String TABLE_ENTRIES = "entries";
    public static final String TABLE_ENTRY_IMAGES = "entry_images";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_IMAGE_ID = "imageId";
    public static final String COLUMN_ENTRYID_ID = "entryid_id";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_HEIGHT = "height";


    public static final String ENTTRY_IMAGES_CREATE = "CREATE TABLE "
        + TABLE_ENTRY_IMAGES + "("
        + COLUMN_ID + " integer primary key autoincrement, "
        + COLUMN_IMAGE_ID + " integer, "
        + COLUMN_ENTRY_ID + " integer, "
        + COLUMN_URL + " text, "
        + COLUMN_HEIGHT + " integer);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ENTRY_IMAGES_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRY_IMAGES);
        onCreate(db);
    }

}
