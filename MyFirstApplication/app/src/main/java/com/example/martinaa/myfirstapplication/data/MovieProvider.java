package com.example.martinaa.myfirstapplication.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Movie;
import android.net.Uri;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.martinaa.myfirstapplication.data.ListContract.MovieEntry;

/**
 * Created by martinaa on 11/07/2017.
 */

public class MovieProvider extends ContentProvider{

    public static final String LOG_TAG = MovieProvider.class.getSimpleName();

    public static final int MOVIES = 100;
    public static final int MOVIE_ID = 101;

    private static final UriMatcher myMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static{
        myMatcher.addURI(ListContract.CONTENT_AUTHORITY, ListContract.PATH_MOVIES, MOVIES);
        myMatcher.addURI(ListContract.CONTENT_AUTHORITY, ListContract.PATH_MOVIES, MOVIE_ID);
    }

    private MovieDbHelper helper;

    @Override
    public boolean onCreate() {
        helper = new MovieDbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor;

        int match = myMatcher.match(uri);
        switch(match){
            case MOVIES:
                cursor = db.query(MovieEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case MOVIE_ID:
                selection = MovieEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                cursor = db.query(MovieEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = myMatcher.match(uri);
        switch(match){
            case MOVIES:
                return MovieEntry.CONTENT_DIR_TYPE;
            case MOVIE_ID:
                return MovieEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + "with match" + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentvalues) {
        final int match = myMatcher.match(uri);
        switch(match){
            case MOVIES:
                return insertMovie(uri, contentvalues);
            default:
                throw new IllegalArgumentException("Not supported for " + uri);
        }
    }

    private Uri insertMovie(Uri uri, ContentValues values){
        String movie = values.getAsString(MovieEntry.COLUMN_MOVIE_NAME);
        if(movie == null){
            throw new IllegalArgumentException("Movie requires a name");
        }

        String description = values.getAsString(MovieEntry.COLUMN_MOVIE_DESCRIPTION);
        if(description == null){
            throw new IllegalArgumentException("Movie requires a description");
        }

        Integer checking = values.getAsInteger(MovieEntry.COLUMN_MOVIE_CHECK);
        if(checking == null || !MovieEntry.isCheckingValid(checking)){
            throw new IllegalArgumentException("Movie is not checked valid");
        }

        SQLiteDatabase db = helper.getWritableDatabase();
        long id = db.insert(MovieEntry.TABLE_NAME, null, values);
        if(id==-1){
            Log.e(LOG_TAG, "Failed to insert" + uri);
            return null;
        }
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int rowsdeleted = 0;

        final int match = myMatcher.match(uri);
        switch(match){
            case MOVIES:
                rowsdeleted = db.delete(MovieEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case MOVIE_ID:
                selection = MovieEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                rowsdeleted = db.delete(MovieEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Failed" + uri);
        }

        if(rowsdeleted !=0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsdeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentvalues, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = myMatcher.match(uri);
        switch (match){
            case MOVIES:
                return UpdatePet(uri, contentvalues, selection, selectionArgs);
            case MOVIE_ID:
                selection = MovieEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return UpdatePet(uri, contentvalues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Failed to update for " + uri);
        }
    }

    private int UpdatePet (Uri uri, ContentValues values, String selection, String[] selectionArgs){
        if(values.containsKey(MovieEntry.COLUMN_MOVIE_NAME)){
            String name = values.getAsString(MovieEntry.COLUMN_MOVIE_NAME);
            if(name == null){
                throw new IllegalArgumentException("Failed");
            }
        }

        if(values.containsKey(MovieEntry.COLUMN_MOVIE_DESCRIPTION)){
            String description = values.getAsString(MovieEntry.COLUMN_MOVIE_DESCRIPTION);
            if(description == null){
                throw new IllegalArgumentException("Failed");
            }
        }

        if(values.containsKey(MovieEntry.COLUMN_MOVIE_CHECK)){
            Integer checking = values.getAsInteger(MovieEntry.COLUMN_MOVIE_CHECK);
            if(checking==null || !MovieEntry.isCheckingValid(checking)){
                throw new IllegalArgumentException("Failed");
            }
        }

        SQLiteDatabase db = helper.getWritableDatabase();
        int rowsUpdated = db.update(MovieEntry.TABLE_NAME, values, selection, selectionArgs);

        if(rowsUpdated !=0){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }
}
