package com.example.yana.zametka;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelperActivation dbHelper;
    SQLiteDatabase db;
    EditText pasText;
    Button btn_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_done = (Button) findViewById(R.id.btn_done);

        dbHelper = new DBHelperActivation(getApplicationContext());
        // создаем базу данных
        db = dbHelper.getWritableDatabase();
     }

    public void goToActivity2(View view){
//        ContentValues values = new ContentValues();
//        String password = pasText.getText().toString();
//        values.put(DBHelperActivation.KEY_PASS, password);
//        db.insert(DBHelperActivation.TABLE_NAME, null, values);
        Intent intent = new Intent(this, Activity3.class);
//        intent.putExtra("pass", passText.getText().toString());
        startActivity(intent);
    }

}
