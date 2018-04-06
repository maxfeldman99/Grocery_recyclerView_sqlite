package com.example.maxfeldman.grocery_recyclerview_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.maxfeldman.grocery_recyclerview_sqlite.GroceryContract.*; // to be able to delete the type in the on create string

/**
 * Created by MAX FELDMAN on 06/04/2018.
 */

public class GroceryDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "grocerylist.db";
    public static final int DATABASE_VERSION = 1;

    public GroceryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE " +
                GroceryEntry.TABLE_NAME + " ( " +
                GroceryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  // we got id from the interface of out implementation , will add +1 every time we build new row
                GroceryEntry.COLUMN_NAME + " TEXT NOT NULL, " +  // it have to contain a value
                GroceryEntry.COLUMN_AMOUNT + " INTEGER NOT NULL, " + // it have to contain a value
                GroceryEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" + // every time we create a new row it will create a new timestamp at the current time of adding
                ");";
        sqLiteDatabase.execSQL(SQL_CREATE_GROCERYLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // if we want to add a new column to the db , we will have to increment out db_version an append it here
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GroceryEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
