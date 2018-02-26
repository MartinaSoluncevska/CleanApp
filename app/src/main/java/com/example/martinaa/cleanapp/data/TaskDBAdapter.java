package com.example.martinaa.cleanapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.example.martinaa.cleanapp.data.TaskHelper.Tasks;
/**
 * Created by martinaa on 21/07/2017.
 */

public class TaskDBAdapter {
    Context c;
    SQLiteDatabase db;
    TaskHelper helper;

    public TaskDBAdapter(Context c){
        this.c = c;
        helper = new TaskHelper(c);
    }

    public TaskDBAdapter open(){
        try{
            db = helper.getWritableDatabase();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return this;
    }

    public void close(){
        try{
            helper.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public long addData(String name, String timetodo, String timeleft) {
        if (TextUtils.isEmpty(name)) {
            return 0;
        }

        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(TaskHelper.Tasks.COLUMN_TASK_NAME, name);
            contentValues.put(TaskHelper.Tasks.COLUMN_TASK_TIME_TO_DO, timetodo);
            contentValues.put(TaskHelper.Tasks.COLUMN_TASK_TIME_LEFT, timeleft);

            return db.insert(TaskHelper.Tasks.TABLE_NAME, Tasks._ID, contentValues);
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public Cursor getListContents() {
        String[] tasks = {Tasks._ID, Tasks.COLUMN_TASK_NAME, Tasks.COLUMN_TASK_TIME_TO_DO, Tasks.COLUMN_TASK_TIME_LEFT};
        return db.query(Tasks.TABLE_NAME, tasks, null, null, null, null, null);
    }
}
