package com.example.tlucontact;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private SQLiteDatabase db;

    public DatabaseManager(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM department ORDER BY id ASC", null); // Sắp xếp tăng dần
        if (cursor.moveToFirst()) {
            do {
                departments.add(new Department(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return departments;
    }

    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM staff ORDER BY id ASC", null); // Sắp xếp tăng dần
        if (cursor.moveToFirst()) {
            do {
                staffList.add(new Staff(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getInt(6)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return staffList;
    }

}

