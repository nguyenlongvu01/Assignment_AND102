package com.vunlph30245.assignment.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public  DbHelper(Context context){
       super(context,"Assignment.db",null,2);
    };

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Users = "CREATE TABLE USERS(tendangnhap TEXT PRIMARY KEY,matkhau TEXT, hoten TEXT)";
        db.execSQL(Users);
        String Products = "CREATE TABLE PRODUCTS(masp INTEGER PRIMARY KEY AUTOINCREMENT, tensp TEXT, gia INTEGER, soluong INTEGER)";
        db.execSQL(Products);

        String User = "INSERT INTO USERS VALUES('admin','12345','admin')";
        db.execSQL(User);

        db.execSQL("INSERT INTO PRODUCTS VALUES(1, 'Banh Quy', 1000, 100)");
        db.execSQL("INSERT INTO PRODUCTS VALUES(2, 'Snack', 5000, 200)");
        db.execSQL("INSERT INTO PRODUCTS VALUES(3, 'Tra Sua', 15000, 50)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i  != i1) {
            db.execSQL("DROP TABLE IF EXISTS USERS");
            db.execSQL("DROP TABLE IF EXISTS PRODUCTS");
            onCreate(db);
        }
    }
}
