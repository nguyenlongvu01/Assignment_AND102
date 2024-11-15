package com.vunlph30245.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vunlph30245.assignment.database.DbHelper;

public class UsersDAO {
    private DbHelper dbHelper;

    public UsersDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public boolean checkLogin(String username, String password) {
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;

        try {
            sqLiteDatabase = dbHelper.getReadableDatabase();
            cursor = sqLiteDatabase.rawQuery(
                    "SELECT * FROM USERS WHERE tendangnhap = ? AND matkhau = ?",
                    new String[]{username, password}
            );


            if (cursor != null && cursor.moveToFirst()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.e("UsersDAO", "Database error in checkLogin", e);
            return false;
        } finally {
            // Đóng cursor và database để tránh memory leak
            if (cursor != null) {
                cursor.close();
            }
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
                sqLiteDatabase.close();
            }
        }

    }
    public boolean Register(String username, String password, String hoten){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("tendangnhap",username);
        contentValues.put("matkhau",password);
        contentValues.put("hoten",hoten);

        long check = sqLiteDatabase.insert("USERS",null,contentValues);
        if (check != -1){
            return true;
        }
        return false;

    }
    //forgot password
    public String forgotPassword(String email){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT matkhau FROM USERS WHERE tendangnhap = ?",new String[]{email});
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }else {
        return "";
    }

    }
}
