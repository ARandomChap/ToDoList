package com.example.c3451748.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by c3451748 on 04/10/2017.
 * https://www.youtube.com/watch?v=RXtj4TxMmW0
 * This is a new line in to test the pull function on github!!!
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="EDMTDEV";
    private static final int DB_VER = 1;
    public static final String DB_TABLE="Task";
    public static final String DB_COLUMN="TaskName";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = String.format("CREATE TABLE %s {ID INTEGER PRIMARY KEY AUTOINCREMENT, %s  TEXT NOT NULL", DB_TABLE, DB_COLUMN);
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = String.format("DELETE TABLE IF EXISTS &s", DB_TABLE);
        db.execSQL(query);
        onCreate(db);

    }

    public void insertNewTask(String task) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COLUMN, task);
        db.insertWithOnConflict(DB_TABLE,null,values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();

    }

    public void deleteTask(String task){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(DB_TABLE,DB_COLUMN + " = ?", new String [] {task} );
        db.close();

    }
}
