package com.example.yana.zametka;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    EditText pasText;
    DBHelperActivation dbHelper;
    SQLiteDatabase db;
    Cursor passCursor;
    Button btn_done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        btn_done = (Button) findViewById(R.id.btn_done);

        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelperActivation(getApplicationContext());
        // создаем базу данных
        db = dbHelper.getWritableDatabase();
    }
    String[] columns = null;
    String having;
    public void onMyButtonClick(View view) {
        String pas = pasText.getText().toString();

        having = "password = " + pas;
        columns = new String[] { pas };

        passCursor = db.query(DBHelperActivation.TABLE_NAME, columns, null, null, null, having, null);
        if (passCursor != null) {
            if (passCursor.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : passCursor.getColumnNames()) {
                        str = str.concat(cn + " = "
                                + passCursor.getString(passCursor.getColumnIndex(cn)) + "; ");
                    }
                    Intent intent = new Intent(this, Activity3.class);
                    startActivity(intent);

                } while (passCursor.moveToNext());
            }
            passCursor.close();
        }else {
            Toast.makeText(this, "Неверный пароль", Toast.LENGTH_LONG).show();
        }


//        passCursor = db.rawQuery("select * from " + DBHelper.TABLE_NAME + " where " +
//                DBHelper.KEY_ID + "=?", null);
//        passCursor.moveToFirst();
//        pasText.setText(passCursor.getString(1));
//        passCursor.close();

//        if (pas != str) {
//            Toast.makeText(this, "Неверный пароль", Toast.LENGTH_LONG).show();
////                    btn_done.setVisibility(View.GONE);
//        } else {
//            Intent intent = new Intent(this, Activity3.class);
//            startActivity(intent);
//        }

    }
}
    //]

//            passCursor = db.rawQuery("select * from " + DBHelperActivation.TABLE_NAME + " where " +
//                    DBHelperActivation.KEY_PASS + "=?", new String[]{String.valueOf(passCursor.getCount())});
//            passCursor.moveToFirst();
//            pasText.setText(passCursor.getString(1));
//            passCursor.close();
//        }
//
//        if (pasText == passCursor) {
//            Intent intent = new Intent(this, Activity3.class);
//            startActivity(intent);
//        } else {
//            Toast.makeText(this, "Неверный пароль", Toast.LENGTH_LONG).show();
//        }






