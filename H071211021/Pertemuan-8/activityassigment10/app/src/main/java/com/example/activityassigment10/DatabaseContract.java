package com.example.activityassigment10;


import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_NAME = "note";

    public static final class NoteColumns implements BaseColumns {

        public static String TITLE = "title";
        public static String DESCRIPTION = "description";
        public static String CREATED_AT = "created_at";
        public static String IS_EDITED = "is_edited";
    }
}
