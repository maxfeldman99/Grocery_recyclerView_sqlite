package com.example.maxfeldman.grocery_recyclerview_sqlite;

import android.provider.BaseColumns;

/**
 * Created by MAX FELDMAN on 06/04/2018.
 */

public class GroceryContract {
    private GroceryContract(){}

    public static final class GroceryEntry implements BaseColumns{

        public static final String TABLE_NAME = "groceryList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
