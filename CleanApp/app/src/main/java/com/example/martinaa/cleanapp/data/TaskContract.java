package com.example.martinaa.cleanapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by martinaa on 19/07/2017.
 */

public final class TaskContract {

    private TaskContract(){}

    //The name of the entire content provider
    public static final String CONTENT_AUTHORITY="com.example.martinaa.cleanapp";
    //the base of all URIs the app will use
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    //possible path for possible URI's
    public static final String PATH_TASKS = "cleanapp";


    public static final class Tasks implements BaseColumns {

        //the content URI to access the task data in the provider
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TASKS);
        //MIME type for all list
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TASKS;
        //MIME type for a single task
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TASKS;

        public final static String TABLE_NAME =  "tasks";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_TASK_NAME = "name";
        public final static String COLUMN_TASK_TIME_TO_DO = "time";
        public final static String COLUMN_TASK_TIME_LEFT = "timeleft";


    }
}
