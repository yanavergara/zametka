package com.example.yana.zametka;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {
    ListView viewList;
    DBHelper dbHelper;
    SQLiteDatabase db;

    Cursor notesCursor;
    SimpleCursorAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        viewList = (ListView)findViewById(R.id.listView);
        viewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Activity4.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        dbHelper = new DBHelper(getApplicationContext());
        // создаем базу данных
    }
    @Override
    public void onResume() {
        super.onResume();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //получаем данные из бд в виде курсора
        notesCursor =  db.rawQuery("select * from "+ DBHelper.TABLE_NAME, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[] {DBHelper.KEY_TEXT};
        // создаем адаптер, передаем в него курсор
        notesAdapter = new SimpleCursorAdapter(this, R.layout.list_item,
                notesCursor, headers, new int[]{R.id.textView1}, 0);
        viewList.setAdapter(notesAdapter);
        //dbHelper.close();
//        notesCursor.close();
    }
    // по нажатию на кнопку запускаем Activity4 для добавления данных
    public void onActivity4(View view){
        Intent intent = new Intent(this, Activity4.class);
        startActivity(intent);
    }
    public void delete(View view){
        db.delete(DBHelper.TABLE_NAME, null, null);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение и курсор
        dbHelper.close();
        notesCursor.close();
    }

    public void onExit(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
