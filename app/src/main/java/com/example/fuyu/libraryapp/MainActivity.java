package com.example.fuyu.libraryapp;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.ContentValues;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private MyOpenHelper helper;
    private SQLiteDatabase db;
    private EditText titleText;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button entryButton;
    private Button deleteButton;
    private RadioGroup rg;
    private RadioButton radioButton;
    private ContentValues insertValues;
    //public int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new MyOpenHelper(this);
        db = helper.getWritableDatabase();

        titleText = (EditText) findViewById(R.id.editTitle);

        button0 = (Button) findViewById(R.id.button0);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dbIntent = new Intent(MainActivity.this, ShowDataBase.class);
                dbIntent.putExtra("num", "0");
                startActivity(dbIntent);
            }
        });

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dbIntent = new Intent(MainActivity.this, ShowDataBase.class);
                dbIntent.putExtra("num", "1");
                startActivity(dbIntent);
            }
        });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dbIntent = new Intent(MainActivity.this, ShowDataBase.class);
                dbIntent.putExtra("num", "2");
                startActivity(dbIntent);
            }
        });


        entryButton = (Button) findViewById(R.id.insert);
        entryButton.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {
                // ラジオグループのオブジェクトを取得
                rg = (RadioGroup) findViewById(R.id.RadioGroup);
                // チェックされているラジオボタンの ID を取得
                int checkedId = rg.getCheckedRadioButtonId();
                // チェックされているラジオボタンオブジェクトを取得
                radioButton = (RadioButton) findViewById(checkedId);

                String text = radioButton.getText().toString();
                String num;
                if (checkedId != -1) {
                    if (text.equals("読みたい")) num = "0";
                    else if (text.equals("読んだ")) num = "1";
                    else num = "2";

                    String title = titleText.getText().toString();
                    insertValues = new ContentValues();
                    insertValues.put("title", title);
                    insertValues.put("num", num);
                    long id = db.insert("book", title, insertValues);
                }
            }
        });

        deleteButton = (Button) findViewById(R.id.delete);
        deleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleText.getText().toString();
                db.delete("book", "title=?", new String[]{title});
            }
        });


    }
}
