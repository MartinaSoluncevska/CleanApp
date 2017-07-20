package com.example.martinaa.cleanapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.example.martinaa.cleanapp.data.TaskContract.*;
import static com.example.martinaa.cleanapp.data.TaskHelper.LOG_TAG;

/**
 * Created by martinaa on 19/07/2017.
 */

public class TaskProvider extends ContentProvider {

    //URI matcher code for the content URI for tasks and single task as well
    private static final int TASKS = 100;
    private static final int TASK_ID = 101;

    //to match a content URI to a corresponding code
    private static final UriMatcher my_matcher = new UriMatcher(UriMatcher.NO_MATCH);

    //run the first time anything is called from this class
    static{
        my_matcher.addURI(CONTENT_AUTHORITY, PATH_TASKS, TASKS);
        my_matcher.addURI(CONTENT_AUTHORITY, PATH_TASKS + "/#", TASK_ID);
    }

    private TaskHelper my_helper;

    @Override
    public boolean onCreate() {
        my_helper = new TaskHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = my_helper.getWritableDatabase();
        //to hold the result of the query
        Cursor task_cursor;

        //if the URI matcher can match the URI to a specific code
        int match = my_matcher.match(uri);
        switch(match){
            case TASKS:
                //query the table directly
                task_cursor = db.query(Tasks.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case TASK_ID:
                //extract the ID from the URI
                selection = Tasks._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                task_cursor = db.query(Tasks.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        task_cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return task_cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = my_matcher.match(uri);
        switch(match){
            case TASKS:
                return Tasks.CONTENT_LIST_TYPE;
            case TASK_ID:
                return Tasks.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + "with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = my_matcher.match(uri);
        switch(match){
            case TASKS:
                return insertTask(uri, values);
            default:
                throw new IllegalArgumentException("Insertion not supported!");
        }
    }

    private Uri insertTask(Uri uri, ContentValues contentValues){
        String name = contentValues.getAsString(Tasks.COLUMN_TASK_NAME);
        if(name==null){
            throw new IllegalArgumentException("Name is required");
        }

        String time = contentValues.getAsString(Tasks.COLUMN_TASK_TIME_TO_DO);
        if(time == null){
            throw new IllegalArgumentException("Time to do this task is required");
        }

        SQLiteDatabase db = my_helper.getWritableDatabase();

        //Insert the new task
        long id = db.insert(Tasks.TABLE_NAME, null, contentValues);
        if(id==-1){
            Log.e(LOG_TAG, "Failed");
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }


    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

}
