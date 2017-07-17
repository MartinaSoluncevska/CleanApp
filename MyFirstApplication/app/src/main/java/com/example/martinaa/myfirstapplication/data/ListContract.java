package com.example.martinaa.myfirstapplication.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;


/**
 * Created by martinaa on 11/07/2017.
 */

public class ListContract {

    private ListContract(){}

    public static final String CONTENT_AUTHORITY = "com.example.martinaa.myfirstapplication";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_MOVIES = "movies";

    public static final class MovieEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_MOVIES);

        public static final String CONTENT_DIR_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;

        public final static String TABLE_NAME = "movies";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_MOVIE_NAME = "name";

        public final static String COLUMN_MOVIE_DESCRIPTION = "description";

        public final static String COLUMN_MOVIE_CHECK = "check-in";

        public final static int CHECKED = 0;
        public final static int UNCHECKED = 1;

        public static boolean isCheckingValid (int checking){

            if(checking == CHECKED || checking == UNCHECKED){
                return true;
            }
            return false;
        }    }
}
