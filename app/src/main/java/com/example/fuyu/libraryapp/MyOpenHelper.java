package com.example.fuyu.libraryapp;

/**
 * Created by fuyu on 2016/07/08.
 */
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper{

    public MyOpenHelper(Context context){
        super(context,"BookDB", null, 1 );

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table book(" + " title text not null," + "num text" + ");");
     //   db.execSQL("insert into book(title,num) values ('あああ,1');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
}
