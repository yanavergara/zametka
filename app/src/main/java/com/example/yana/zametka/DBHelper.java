package com.example.yana.zametka;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.R.attr.version;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "notesDB";
    public static final String TABLE_NAME = "notes";

    public static final String KEY_ID = "_id";
    public static final String KEY_TEXT = "text_notes";


    //создание метода работы с бд
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // запрос на создание таблицы с полями
        db.execSQL("create table " + TABLE_NAME + " ("
                + KEY_ID + " integer primary key, "
                + KEY_TEXT +  " text " + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //запрос на удаление таблицы, если таблица существует
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);

    }

}
