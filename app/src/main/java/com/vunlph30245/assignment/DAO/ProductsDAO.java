package com.vunlph30245.assignment.DAO;

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
                list.add(new Products(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)));
            } while (cursor.moveToNext());
        }
        return list;
    }
}
