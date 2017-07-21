package com.example.martinaa.cleanapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import static java.security.AccessController.getContext;

/**
 * Created by martinaa on 19/07/2017.
 */

//Manages db creation and version management
public class TaskHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 1;

    public static final class Tasks implements BaseColumns {
        public final static String TABLE_NAME =  "tasks";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_TASK_NAME = "name";
        public final static String COLUMN_TASK_TIME_TO_DO = "time";
        public final static String COLUMN_TASK_TIME_LEFT = "timeleft";
    }

    public TaskHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a String that contains the SQL statement to create the table
        String SQL_CREATE_TASK_TABLE = "CREATE TABLE IF NOT EXISTS " + Tasks.TABLE_NAME + " ( "
                + Tasks._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + Tasks.COLUMN_TASK_NAME + " TEXT NOT NULL , "
                + Tasks.COLUMN_TASK_TIME_TO_DO + " TEXT NOT NULL , "
                + Tasks.COLUMN_TASK_TIME_LEFT + " TEXT );";

        db.execSQL(SQL_CREATE_TASK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //nothing to do, since it is version 1
    }
}
