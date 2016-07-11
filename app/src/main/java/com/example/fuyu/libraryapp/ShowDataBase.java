package com.example.fuyu.libraryapp;

/**
 * Created by fuyu on 2016/07/08.
 */
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.content.ContentValues;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class ShowDataBase extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_database);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getReadableDatabase();

        final EditText titleText = (EditText) findViewById(R.id.editTitle);


        Intent intent =getIntent();
        String data = intent.getStringExtra("num");

        Cursor c = db.query("book",new String[]{"title","num"},"num == ?",new String[]{data},null,null,null);

       boolean mov = c.moveToFirst();
        while(mov){
            TextView textView = new TextView(this);
            textView.setText(String.format("%s" ,c.getString(0)));
            mov = c.moveToNext();
           layout.addView(textView);
        }
        c.close();
        db.close();


    }
}
