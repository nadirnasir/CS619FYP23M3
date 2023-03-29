package com.example.cs619fypm3mc180406254;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbAgent  extends SQLiteOpenHelper  {
    private SQLiteDatabase dbInstanace;
    public static String DB_NAME = "CS619FYPM3";
    public static String DB_TABLE = "People";
    public static String DB_TABLE_C1 = "CNIC";
    public static String DB_TABLE_C2 = "UserID";
    public static String DB_TABLE_C3 = "UserRole";
    public static String DB_TABLE_C4 = "FirstName";
    public static String DB_TABLE_C5 = "LastName";
    public static String DB_TABLE_C6 = "ResidentID";
    public static String DB_TABLE_C7 = "Address";
    public static String DB_TABLE_C8 = "PhoneNo";

    public dbAgent(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, 1);
         dbInstanace = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL
                ("CREATE TABLE " + DB_TABLE + "(CNIC INT PRIMARY KEY,UserID TEXT, " +
                        "UserRole TEXT, " +
                        "FirstName TEXT, " +
                        "LastName TEXT, " +
                        "ResidentID TEXT, " +
                        "Address TEXT, " +
                        "PhoneNo INT" +
                        ")"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + DB_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String cnic, String email, String role, String fname, String lname, String rid, String address, String phone) {
        ContentValues values = new ContentValues();
        values.put(DB_TABLE_C1, cnic);
        values.put(DB_TABLE_C2, email);
        values.put(DB_TABLE_C3, role);
        values.put(DB_TABLE_C4, fname);
        values.put(DB_TABLE_C5, lname);
        values.put(DB_TABLE_C6, rid);
        values.put(DB_TABLE_C7, address);
        values.put(DB_TABLE_C8, phone);

        long res = dbInstanace.insert(DB_TABLE, null, values);

        if ( res == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(){
        return dbInstanace.rawQuery("SELECT * FROM " + DB_TABLE, null);
    }
}
