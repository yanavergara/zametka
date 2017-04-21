package com.example.yana.zametka;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class Activity4 extends AppCompatActivity {
    //описываем переменные с кнопками и текстом
    EditText etText;
    ImageButton delButton;
    ImageButton btnSave;

    SQLiteDatabase db;
    Cursor notesCursor;
    long zametkaId=0;

    //описываем метод работы с бд
    DBHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        //находим по id компоненты активности4
        etText = (EditText) findViewById(R.id.editText4);

        delButton = (ImageButton) findViewById(R.id.delButton);
//        delButton.setOnClickListener(this);
        btnSave = (ImageButton) findViewById(R.id.btnSave);


        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelper(getApplicationContext());
        // создаем базу данных
        db = dbHelper.getWritableDatabase();


    //String zametkaId = getIntent().getStringExtra("id");
    Bundle extras = getIntent().getExtras();
    if (extras != null) {
        zametkaId = extras.getLong("id");
    }
    // если 0, то добавление

    if (zametkaId > 0) {
        // получаем элемент по id из бд
        notesCursor = db.rawQuery("select * from " + DBHelper.TABLE_NAME + " where " +
                DBHelper.KEY_ID + "=?", new String[]{String.valueOf(zametkaId)});
        notesCursor.moveToFirst();
        etText.setText(notesCursor.getString(1));
        notesCursor.close();
    } else {
        // скрываем кнопку удаления
        delButton.setVisibility(View.GONE);
//        save(View);
        }}

    public void save(View view) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.KEY_TEXT, etText.getText().toString());

        if (zametkaId > 0) {
            db.update(DBHelper.TABLE_NAME, cv, DBHelper.KEY_ID + "=" + String.valueOf(zametkaId), null);
        } else {
            db.insert(DBHelper.TABLE_NAME, null, cv);
        }
        //db.close();
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);;
    }

    public void delete(View view){
        db.delete(DBHelper.TABLE_NAME, "_id = ?", new String[]{String.valueOf(zametkaId)});
        //db.close();
        // переход к главной activity
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);;
    }

}
