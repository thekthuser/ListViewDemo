package com.thekthuser.listviewdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        db.execSQL(ENTRY_IMAGES_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRY_IMAGES);
        onCreate(db);
    }

}
