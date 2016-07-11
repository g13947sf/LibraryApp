package com.example.fuyu.libraryapp;

/**
 * Created by fuyu on 2016/07/08.
 */

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowDataBase extends Activity {
    private LinearLayout layout;
    private MyOpenHelper helper;
    private SQLiteDatabase db;
    private Intent intent;
    private String data;
    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_database);

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        helper = new MyOpenHelper(this);
        db = helper.getReadableDatabase();

        intent = getIntent();
        data = intent.getStringExtra("num");

        c = db.query("book", new String[]{"title", "num"}, "num == ?", new String[]{data}, null, null, null);

        boolean mov = c.moveToFirst();
        while (mov) {
            TextView textView = new TextView(this);
            textView.setText(String.format("%s", c.getString(0)));
            textView.setTextSize(20.0f);
            mov = c.moveToNext();
            layout.addView(textView);
        }
        c.close();
        db.close();


    }
}
