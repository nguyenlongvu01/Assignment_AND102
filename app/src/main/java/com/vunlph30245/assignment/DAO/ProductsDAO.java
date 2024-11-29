package com.vunlph30245.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vunlph30245.assignment.Model.Products;
import com.vunlph30245.assignment.database.DbHelper;

import java.util.ArrayList;

public class ProductsDAO {
    private DbHelper dbHelper;
    public ProductsDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Products> getAll() {
        SQLiteDatabase sqlitedatabase = dbHelper.getReadableDatabase();
        ArrayList<Products> list = new ArrayList<>();
        Cursor cursor = sqlitedatabase.rawQuery("SELECT * FROM PRODUCTS", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                list.add(new Products(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        return list;
    }
    public boolean themSP(Products products){
        SQLiteDatabase sqlitedatabase = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("tensp", products.getTensp());
        values.put("gia", products.getGia());
        values.put("soluong", products.getSoluong());
        values.put("img", products.getImg());

        long check = sqlitedatabase.insert("PRODUCTS", null, values);
        return check != -1;

    }

    public boolean insertSP(Products products){
        SQLiteDatabase sqlitedatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tensp", products.getTensp());
        values.put("gia", products.getGia());
        values.put("soluong", products.getSoluong());
        int check = sqlitedatabase.update("PRODUCTS", values, "masp = ?", new String[]{String.valueOf(products.getMasp())});
        return check != -1;
    }

    public boolean deleteSP(int masp){
        SQLiteDatabase sqlitedatabase = dbHelper.getWritableDatabase();
        int check = sqlitedatabase.delete("PRODUCTS", "masp = ?", new String[]{String.valueOf(masp)});
        return check != -1;

    }
}
