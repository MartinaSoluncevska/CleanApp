package com.example.martinaa.myfirstapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.martinaa.myfirstapplication.data.ListContract.MovieEntry;


/**
 * Created by martinaa on 11/07/2017.
 */

public class MovieDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = MovieDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME= "movies.db";
    private static int DATABASE_VERSION = 1;

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a string that contains the SQL statement to create movies table
        String SQL_CREATE_MOVIES_TABLE = "CREATE TABLE" + MovieEntry.TABLE_NAME
                + MovieEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT"
                + MovieEntry.COLUMN_MOVIE_NAME + "TEXT NOT NULL"
                + MovieEntry.COLUMN_MOVIE_DESCRIPTION + "TEXT" ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
